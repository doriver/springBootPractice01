package com.example.demo.controller.response.body;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/apiResponse")
public class ApiResponseTest {
	
	// 성공시 데이터 있는경우
	// 반환타입은 성공했을때 데이터타입 
	@GetMapping("/01/{id}")
	public ApiResponse<Map<String,Boolean>> aa(
			@PathVariable("id") String id ) {
		
		Map<String,Boolean> rr = new HashMap<>();
		rr.put("api", true);
		rr.put("response", false);
		
		if (id.equals("suc")) {
			return ApiResponse.success(rr);
		} else if (id.equals("sucNull")) {
			return ApiResponse.success();
		}
	
		return ApiResponse.fail("실패했습");
	}

	// 성공시 데이터 없는경우
	@GetMapping("/02/{id}")
	public ApiResponse<?> a1a(
			@PathVariable("id") String id ) {	
	
		if (id.equals("suc")) {
			return ApiResponse.success();
		} 
		return ApiResponse.fail("실패했습");
	}
}
