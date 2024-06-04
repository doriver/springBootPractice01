package com.example.demo.web.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.web.common.CommonFilterInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
	// HandlerInterceptor를 implements해주고, 스프링빈으로 등록해줘야함
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		String logId = (String)request.getAttribute(CommonFilterInterceptor.LOG_ID);
		String clientIp = (String)request.getAttribute(CommonFilterInterceptor.CLINET_IP);
		
		/*		핸들러 정보( 어떤 핸들러 매핑을 사용하는가에 따라 달라짐 )
		 * @Controller + @RequestMapping 을 활용한 핸들러 매핑을 사용하면, 핸들러 정보로 HandlerMethod 가 넘어온다
		 * /resources/static 와 같은 정적 리소스가 호출 되는 경우( @Controller 가 아니라 ) ResourceHttpRequestHandler 가 핸들러 정보로 넘어옴
		 */
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler; // 호출할 컨트롤러 메서드의 모든 정보가 포함되있다
		}
		
		log.info("Request in Interceptor preHandle [{}][{}][{}][{}]", logId, clientIp, requestURI, handler);
		
		return true; // 다음 인터셉터나 컨트롤러가 호출됨
		// false면 진행x
//		return HandlerInterceptor.super.preHandle(request, response, handler); // default값
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("modelAndView in Interceptor postHandle [{}]", modelAndView);
		
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView); // default값
	}

	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String requestURI = request.getRequestURI();
		String logId = (String)request.getAttribute(CommonFilterInterceptor.LOG_ID);
		String clientIp = (String)request.getAttribute(CommonFilterInterceptor.CLINET_IP);
		
		/*		종료로그
		 * 예외가 발생한 경우 postHandle가 호출되지 않기 때문에 afterCompletion에서
		 */
		log.info("Response in Interceptor afterCompletion [{}][{}][{}]", logId, clientIp, requestURI);
		if (ex != null) {
			log.error("Interceptor afterCompletion error!!", ex);
		}
//		HandlerInterceptor.super.afterCompletion(request, response, handler, ex); // default값
	}

}
