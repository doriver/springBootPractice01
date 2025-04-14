package com.example.demo.dataBinding;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RequestJsonDto;
import com.example.demo.dto.RequestUrlEncodedDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bind")
public class BindingController {

	/*
	 * Post - application/json
	 */
	@PostMapping("/t1")
	public String ex01(@RequestBody RequestJsonDto dto) {
		log.info("{} {}", dto.getName(), dto.getAge());
		return "성공";
	}
	/*
	 * @RequestBody에서 발생할수 있는 에러
	 * org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize value of type `int` from String "ㅁㄴㅇ"
	 * org.springframework.web.HttpMediaTypeNotSupportedException: Content-Type 'text/plain;charset=UTF-8' is not supported
	 */
	
	/*
	 * Get - query params
	 * Post - application/x-www-form-urlencoded
	 */
	@RequestMapping("/t2")
	public String ex02(@ModelAttribute RequestUrlEncodedDto dto) {
		log.info("{} {}", dto.getName(), dto.getAge());
		return "성공";
	}
}
