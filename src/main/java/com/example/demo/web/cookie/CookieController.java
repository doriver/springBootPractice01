package com.example.demo.web.cookie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CookieController {

	@GetMapping("/cookie")
	public String createCookie(HttpServletResponse response) {
		
		/*
		 * 쿠키 생성
		 * 쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료시 모두 종료)
		 */
		Cookie idCookie = new Cookie("memberId", "1"); 
		// 쿠키 이름은 memberId 이고, 값은 1
		response.addCookie(idCookie);
		// 웹 브라우저는 종료 전까지 쿠키를 서버에 계속 보내줄 것이다.
		
		return "redirect:/cookieView";
	}
	
	@GetMapping("/cookieView")
	public String ckv( @CookieValue(name="memberId", required=false) Long memberId
			, Model model ) { // @CookieValue로 요청안에 있는 쿠키 조회함
		if ( memberId != null) {
			if (memberId == 1) {
//			model.addAttribute("member", "memberId :" + memberId);
				return "cookie/hasCookie";
			}			
		}
		
		return "cookie/noCookie";
	}
	
	@GetMapping("/empty")
	@ResponseBody
	public String aa(@CookieValue(name="memberId", required=false) Long memberId) {
		return "memberId :" + memberId;
	} // 쿠키 넣어준 상태에서, ajax로 요청했을때도 쿠키 들어있는거 확인함
	
	@GetMapping("/deleteCookie")
	@ResponseBody
	public String cookieOut(HttpServletResponse response) {
		/*
		 * 쿠키 삭제
		 */
		Cookie cookie = new Cookie("memberId", null);
		cookie.setMaxAge(0); // max-age 0이나 음수지정하면 쿠키삭제 됨
		response.addCookie(cookie);
		return "";
	}
}
