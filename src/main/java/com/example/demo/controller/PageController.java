package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.web.filter.LoginCheckFilter;
import com.example.demo.web.login.Member;
import com.example.demo.web.login.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PageController {

	@GetMapping("/")
	public String homeLogin(HttpServletRequest request, Model model) {
		log.info("컨트롤러쪽 / 메소드 들어옴");
		/* 
		 * 세션이 없으면 home
		 */
		HttpSession session = request.getSession(false); // 세션이 있으면 기존 세션을 반환, 없으면 null을 반환
		if (session == null) {
			return "home";
		}
		
		/* 
		 * 세션에 회원 데이터가 없으면 home
		 */
		Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
		
		
		
		/* 
		 * 세션이 유지되면 로그인으로 이동
		 */
		model.addAttribute("member", loginMember);
		return "loginHome";
	}
	
	@GetMapping("/plogin")
	public String loginPage() {
		return "login/loginForm";
	}
	
	@GetMapping("/afLogin")
	public String afrLogin() {
		return "specific";
	}
}
