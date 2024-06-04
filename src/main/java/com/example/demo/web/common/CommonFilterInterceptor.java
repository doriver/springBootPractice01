package com.example.demo.web.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonFilterInterceptor {
	
	public static final String CLINET_IP = "clientIp";
	public static final String LOG_ID = "logId"; // 요청당 마다 구분해줄 값
	
	/**		 getRemoteAddr()로, 클라이언트의 원 IP주소를 못가져오는 현상
	 *  Web Server에서 프록시나 로드 밸런서를 통해 WAS에 요청하면, 프록시나 로드 밸런서의 IP 주소만을 담고 있다.
	 * 
	 *  X-Forwarded-For( XFF ) 헤더는
	 *  HTTP 프록시나 로드 밸런서를 통해 웹 서버에 접속하는 클라이언트의 원 IP 주소를 식별하는 표준 헤더
	 *  프록시나 로드밸런스 등을 사용할 경우 Apache/Nginx에서 설정이 되어있다는 가정하에 클라이언트의 실제 접속 IP를 가져올 수 있다.
	 */
	/*
	 * ip로그찍고 반환함
	 */
	public static String getClientIP(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	    log.info("> X-FORWARDED-FOR : " + ip);

	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	        log.info("> Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	        log.info(">  WL-Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	        log.info("> HTTP_CLIENT_IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	        log.info("> HTTP_X_FORWARDED_FOR : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	        log.info("> getRemoteAddr : "+ip);
	    }
	    log.info("> Result : IP Address : "+ip);

	    return ip;
	}
	
}
