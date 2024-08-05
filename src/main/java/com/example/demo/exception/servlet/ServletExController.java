package com.example.demo.exception.servlet;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ServletExController {

	@GetMapping("/error-ex")
	public void errorEx() {
		throw new RuntimeException("예외 발생");
	}
	
	/*
	 * 		HttpServletResponse 가 제공하는 메서드 sendError
	 * 
	 *  예외가 발생하는 것은 아니지만, 서블릿 컨테이너에게 오류가 발생했다는 점을 전달할 수 있다.
	 *  response.sendError(HTTP 상태 코드, 오류 메시지)
	 */
	
	@GetMapping("/error-404")
	public void error404(HttpServletResponse response) throws IOException {
		response.sendError(404, "404오류!");
	}

	@GetMapping("/error-500")
	public void error500(HttpServletResponse response) throws IOException {
		response.sendError(500);
	}
	
}
