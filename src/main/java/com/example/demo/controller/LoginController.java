package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.web.login.Member;
import com.example.demo.web.login.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@PostMapping("/login")
	@ResponseBody
	public String miniLogin(HttpServletRequest request) {
		
		Member member = new Member(); // ID,비번으로 조회한 회원정보
		
		/* 
		 * 로그인 성공 처리
		 */
		// 요청에 담긴 세션ID에 해당하는 세션이 있으면 그 세션 반환
		// 없으면 신규 세션 생성 , 새롭게 생성된 세션ID는 Set-Cookie를통해 전달됨
		HttpSession session = request.getSession(); // 사용자 마다 session과 ID값이 다르게 생김
		session.setAttribute(SessionConst.LOGIN_MEMBER, member);
		
		
		return "";
	}
	
	@GetMapping("/logout")
	@ResponseBody
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
		
		return "";
	}
}
