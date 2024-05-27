package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.web.login.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@PostMapping("/login")
	public String miniLogin(HttpServletRequest request) {
		
		/* 
		 * 로그인 성공 처리
		 */
		HttpSession session = request.getSession();
		session.setAttribute(SessionConst.LOGIN_MEMBER, session);
		
		
		return "redirect:/";
	}
}
