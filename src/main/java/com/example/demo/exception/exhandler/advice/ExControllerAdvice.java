package com.example.demo.exception.exhandler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.exception.UserException;
import com.example.demo.exception.api.ApiExceptionV2Controller;
import com.example.demo.exception.exhandler.ErrorResult;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(assignableTypes = ApiExceptionV2Controller.class) // 특정 클래스 지정
//@RestControllerAdvice
//@RestControllerAdvice("com.example.demo.exception.api") // 패키지 지정
public class ExControllerAdvice {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public Object illegalExHandler(IllegalArgumentException e, HttpServletRequest request) {
		log.error("[exceptionHandler] ex", e);
		
		String acceptHeader = request.getHeader("Accept");
		
		// Accept: text/html 요청에 대해 HTML 응답
        if (acceptHeader != null && acceptHeader.contains("text/html")) {
            ModelAndView mav = new ModelAndView("first");
            mav.addObject("message", e.getMessage());
            return mav;
        }
		
        // 그 외의 경우 JSON 응답 , @ResponseStatus(HttpStatus.BAD_REQUEST) 있어서 responseEntity안씀
        return new ErrorResult("BAD", e.getMessage());
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResult> userExHandler(UserException e) {
		log.error("[exceptionHandler] ex", e);
		ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
		return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ErrorResult exHandler(Exception e) {
		log.error("[exceptionHandler] ex", e); // 이게 필요하군
		return new ErrorResult("EX", "내부오류");
	}
}
