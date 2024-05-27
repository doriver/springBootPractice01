package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping("/")
	public String homeLogin(HttpServletRequest request, Model model) {
		
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
		
		
		
		
		/* 
		 * 세션이 유지되면 로그인으로 이동
		 */
		
		return "loginHome";
	}
}
