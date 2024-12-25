package com.example.demo.exception.exhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ErrorResult {
	
	private String status = "error";
	private String code;
	private String message;
	
	public ErrorResult(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
