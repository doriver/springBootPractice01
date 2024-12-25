package com.example.demo.web.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.web.common.CommonFilterInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
/**
 * 디패servlet <-> 핸들러 어뎁터 <-> 핸들러(컨트롤러)
 * 핸들러 어뎁터 : 실제 핸들러를 실행, @Controller가 반환하는 정보를 ModelAndView로 변환해서 반환
 * 
 * 		첫번째 인터셉터
 *	첫번째 필터에서 request에 set해주었던 logId, clientIp를 가져다 씀
 *	
 *	로그 찍는것들
 *  logId, clientIp, requestURI, handler
 *  logId, modelAndView
 *  logId, clientIp, requestURI
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor { // HandlerInterceptor를 implements해주고, 스프링빈으로 등록해줘야함
	
	/*
	 * 		핸들러 어댑터 호출 전에 호출됨
	 * 호출될 핸들러의 정보 받을수 있음
	 * (호출정보)
	 */
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
		
		log.info("[firstInterceptor] Request in preHandle [{}][{}][{}][{}]", logId, clientIp, requestURI, handler);
		
		return true; // 다음 인터셉터나 컨트롤러가 호출됨
		// false인경우 나머지 인터셉터는 물론이고, 핸들러 어댑터도 호출되지 않고 요청 처리 끝냄  ,  응답 주는쪽으로 넘어감
	}

	/*
	 * 		핸들러 어댑터 호출 후에 호출됨
	 * @Controller가 반환하는 정보를 받을수 있다(ModelAndView에 담겨져 있음)
	 * (응답정보)
	 * 
	 * RestController로 반환되는 데이터는 일던 아래로는 못받음
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String logId = (String)request.getAttribute(CommonFilterInterceptor.LOG_ID);
		log.info("[firstInterceptor] modelAndView in postHandle [{}][{}]", logId, modelAndView);
	}

	/*
	 * 뷰가 렌더링 된 이후에 호출( 요청 처리 완료된 뒤 호출 )
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String requestURI = request.getRequestURI();
		String logId = (String)request.getAttribute(CommonFilterInterceptor.LOG_ID);
		String clientIp = (String)request.getAttribute(CommonFilterInterceptor.CLINET_IP);
		
		/*		종료로그
		 * 예외가 발생한 경우 postHandle가 호출되지 않기 때문에 afterCompletion에서
		 */
		log.info("[firstInterceptor] Response in afterCompletion [{}][{}][{}]", logId, clientIp, requestURI);
		if (ex != null) {
			log.error("[firstInterceptor] afterCompletion error!!", ex);
		}
	}

}
