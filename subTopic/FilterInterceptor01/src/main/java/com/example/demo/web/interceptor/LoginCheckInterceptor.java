package com.example.demo.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
/**
 * 		인증체크 인터셉터
 * 
 * 인증은 컨트롤러 호출 전에만 호출되면 된다. 따라서 preHandle만 구현
 *
 * 서블릿 필터와 비교해서 코드가 간결, 인터셉터에서 인증 체크하는거 권장
 */

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		
		log.info("인증 체크 인터셉터 실행 {}", requestURI);
		
		HttpSession session = request.getSession(false);
		// 인증안됐으면 로그인페이지로 이동
		if (session == null) {
			log.info("미인증 사용자 요청");
			response.sendRedirect("/plogin");
			return false;
		}
		
		// 인증o > 핸들러로 
		return true;
	}

}
