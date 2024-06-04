package com.example.demo.web.filter;

import java.io.IOException;
import java.util.UUID;

import com.example.demo.web.common.CommonFilterInterceptor;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
/**		첫번째 필터
 * 
 */
@Slf4j
public class LogFilter implements Filter {
	// 필터를 사용하려면 필터 인터페이스를 구현해야 한다.
	
	/*
	 * 필터 초기화 메서드, 서블릿 컨테이너가 생성될 때 호출된다.
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("log filter init");
	}
	
	/*
	 * 요청이 올 때 마다 해당 메서드가 호출됨. 여기에 필터의 로직을 구현
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException { // ServletRequest는 HTTP 요청이 아닌 경우까지 고려해서 만든 인터페이스이다. HTTP를 사용하려면 다운케스팅 하면 된다.
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String requestURI = httpRequest.getRequestURI();
		
		String logId = UUID.randomUUID().toString(); // 요청당 임의의 uuid를 생성
		String ip = CommonFilterInterceptor.getClientIP(httpRequest);
		request.setAttribute(CommonFilterInterceptor.LOG_ID, logId);
		request.setAttribute(CommonFilterInterceptor.CLINET_IP, ip);
		
		try {
			log.info("Request in firstFilter [{}][{}][{}]", logId, ip, requestURI);
			chain.doFilter(request, response); // LoginCheckFilter 호출( 다음 필터가 있으면 필터를 호출하고, 필터가 없으면 서블릿을 호출한다. 만약 이로직을 호출하지 않으면 다음 단계로 진행되지 않는다. )  
		} catch(Exception e) {
			throw e; // 톰캣까지 예외를 보내줘야함
		} finally { // 요청에대한 처리가 끝난후, 응답전에 실행됨( chain.doFilter(request, response); 다음이기 때문에 )
			log.info("Response in firstFilter [{}][{}][{}]", logId, ip, requestURI); // 2번째 필터( LoginCheckFilter ) finally까지 수행되고나서 수행됨
		}
		
	}
	
	/*
	 * 필터 종료 메서드, 서블릿 컨테이너가 종료될 때 호출된다.
	 */
	@Override
	public void destroy() {
		log.info("log filter destroy");
	}

}
