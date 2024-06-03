package com.example.demo.web.filter;

import java.io.IOException;

import org.springframework.util.PatternMatchUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckFilter implements Filter {
	
	// 항상 허용, whitelist 제외한 나머지 경로는 인증체크로직 적용
	private static final String[] whitelist = {"/", "/login", "/logout", "/js/*"};
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		try {
			log.info("인증체크 필터 시작 {}",requestURI);
			
			if (isLoginCheckPath(requestURI)) {
				log.info("인증체크 로직 실행 {}",requestURI);
				HttpSession session = httpRequest.getSession(false);
				if (session == null) {
					log.info("미인증 사용자 요청 {}",requestURI);
					httpResponse.sendRedirect(requestURI);
				}
			}
		} catch(Exception e) {
			throw e; // 톰캣까지 예외를 보내줘야함
		} finally {
			log.info("인증체크 필터 종료 {}",requestURI);
		}
	}
	
	private boolean isLoginCheckPath(String requestURI) {
		return !PatternMatchUtils.simpleMatch(whitelist, requestURI); // 문자열 패턴 목록과 특정 문자열이 매칭되는지 확인
	}

}
