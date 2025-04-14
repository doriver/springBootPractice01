package com.example.demo.exception.exhandler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.exhandler.ErrorResult;

import lombok.extern.slf4j.Slf4j;

//@RestControllerAdvice
@Slf4j
public class ValidExceptionHandler {

//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ErrorResult exHandler(Exception e) {
//		log.error("[exceptionHandler] ex", e); // 이게 필요하군
		return new ErrorResult("EX", "내부오류");
	}
}
