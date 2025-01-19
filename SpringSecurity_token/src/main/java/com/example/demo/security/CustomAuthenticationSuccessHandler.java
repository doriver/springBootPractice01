package com.example.demo.security;

import java.io.IOException;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	/* AuthenticationSuccessHandler
	 * 로그인 성공 후에 사용자 정보를 세션에 저장
	 * 
	 * Authentication에서 UserDetails을 얻어서 User정보를 HttpSession에 넣음
	 * HttpServletResponse에 sendRedirect() 해줘야 로그인성공후에 페이지 이동됨
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();

		
		HttpSession session = request.getSession();
		session.setAttribute("name", userDetail.getUsername());
		session.setAttribute("age", userDetail.getAge());

		response.sendRedirect("/suc");
		
	}

	
}
