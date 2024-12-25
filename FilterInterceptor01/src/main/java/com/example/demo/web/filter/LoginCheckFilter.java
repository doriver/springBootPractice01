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
	
	// whitelist는 항상허용( 다음 필터나 디페servlet ), whitelist 제외한 나머지 경로는 인증체크로직 적용( 인증안된경우는, 응답쪽으로 돌려주는 로직 )
	private static final String[] whitelist = {"/", "/login","/plogin", "/logout", "/js/*","/*.ico", "/data"};
	
	
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
					
					// 로그인페이지로 redirect , /plogin에 requestURI 보내서, 로그인쪽에서 "/login?redirectURL=" + requestURI 이런식으로 요청하게 하면 로그인후 바로 requestURI로 보낼수도 있음
					httpResponse.sendRedirect("/plogin");
					
					return; // 디페servlet쪽으로 안가고, 응답쪽으로감
				}
			}
			//  다음 필터가 있으면 필터를 호출하고, 필터가 없으면 서블릿을 호출한다. 만약 이로직을 호출하지 않으면 다음 단계로 진행되지 않는다.
			chain.doFilter(request, response); // DispatcherServlet 호출( 다음 필터 없으니까 )
		} catch(Exception e) {
			throw e; // 톰캣까지 예외를 보내줘야함
		} finally {
			log.info("인증체크 필터 종료 {}",requestURI); // DispatcherServlet 호출된 경우, 컨틀롤러쪽 메서드 호출되고 수행됨
		}
	}
	
	/*
	 * whitelist 인경우 인증체크x
	 */
	private boolean isLoginCheckPath(String requestURI) {
		return !PatternMatchUtils.simpleMatch(whitelist, requestURI); // 문자열 패턴 목록(whitelist)에 특정 문자열(requestURI)이 있는지 확인
	}

}
