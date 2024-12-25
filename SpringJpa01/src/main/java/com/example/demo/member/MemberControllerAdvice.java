package com.example.demo.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.demo.controller")
public class MemberControllerAdvice {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String duplicate(IllegalStateException e) {
		return e.getMessage();
	}
}
