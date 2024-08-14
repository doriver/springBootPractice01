package com.example.demo.exception.resolver;

import java.io.IOException;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver{

	// Exception ex 이 넘어왔어, 이를 보고 정상적인 ModelAndView로 반환
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
				
		try {
			if (ex instanceof IllegalArgumentException) {
				log.info("IllegalArgumentException resolver to 400");
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
				return new ModelAndView();
			}
		} catch (IOException e) {
			log.error("resolver ex", e);
		}
		
		return null;
		// null 을 반환하면, 다음 ExceptionResolver를 찾아서 실행한다. 만약 처리할 수 있는 ExceptionResolver 가 없으면 예외 처리가 안되고, 기존에 발생한 예외를 서블릿 밖으로 던진다.
		
	}

}
