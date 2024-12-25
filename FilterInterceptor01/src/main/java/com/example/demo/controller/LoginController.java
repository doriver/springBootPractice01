package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.web.filter.LoginCheckFilter;
import com.example.demo.web.login.Member;
import com.example.demo.web.login.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
/**		로그인 체크리스트
 * 1.로그인 안된상태에서 접근했을때, 필터에서 로그인 페이지로 보내는거 o
 * 2.로그인 했을때 /로 가는거( loginHome ) o
 * 3.로그인 된상태에선 1에서 접근 페이지 접근 된는거 o 
 */
@Controller
@Slf4j
public class LoginController {
	
	/*		로그인	
	 * 
	 * 로그인 실패시 login/loginForm 로
	 * 로그인 성공후 redirect 처리
	 */
	@PostMapping("/login")
	public String miniLogin(HttpServletRequest request) {
		/*
		 * 로그인 실패처리
		 */
		
		/*
		 * BindingResult bindingResult 로 유효성 검사 하는거 있응ㅁ
		 */
//		if (bindingResult.hasErrors()) { // 뭐냐 이거
//			return "login/loginForm";
//		}
		
		Member loginMember = new Member(); // ID,비번으로 조회한 회원정보
		log.info("login? {}", loginMember);
		
		if (loginMember == null) {
//			bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
			return "login/loginForm";
		}
		
		/* 
		 * 로그인 성공 처리
		 */
		// 요청 쿠키에 담긴 세션ID에 해당하는 세션이 있으면 그 세션 반환
		// 없으면 신규 세션 생성 , 새롭게 생성된 세션ID는 Set-Cookie를통해 전달됨
		HttpSession session = request.getSession(); // 사용자 마다 session과 ID값이 다르게 생김
		//세션에 로그인 회원 정보 보관
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		
		//이런식으로 보낼수도 "redirect:" + redirectURL
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String miniLogout(HttpServletRequest request) {
		/*
		 * 세션 삭제
		 */
		HttpSession session = request.getSession(false);
		// 요청에 담긴 세션ID에 해당하는 세션이 있으면 그 세션 반환
		// 없으면 생성X, null반환
		if (session != null) {
			session.invalidate();
		}
		
		return "redirect:/";
	}
}
