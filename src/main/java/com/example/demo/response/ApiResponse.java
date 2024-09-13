package com.example.demo.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
	
	private static final String SUCCESS_STATUS = "success";
	private static final String FAIL_STATUS = "fail";
		
	private String status;
	private String message;
	private T data;
			
	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(SUCCESS_STATUS, data, null);
	}
		
	public static ApiResponse<?> fail(String message) {
		return new ApiResponse<>(FAIL_STATUS, null, message);
	}
		
	private ApiResponse(String status, T data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}
}
