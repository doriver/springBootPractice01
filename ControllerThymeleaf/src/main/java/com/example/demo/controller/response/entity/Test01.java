package com.example.demo.controller.response.entity;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // ResponseEntity는 그냥 Controller에 해도 body에 데이터 들어감
public class Test01 {
	
	@GetMapping("/re")
	public ResponseEntity<String> saadf() {
		return ResponseEntity.ok().body("ResponseEntity.ok().body");
	} // 응답Content-Type : text/plain;charset=UTF-8 ( body에 데이터 들어가 있음 )

	@GetMapping("/re1")
	public ResponseEntity<String> s1aadf() {
		return ResponseEntity.badRequest().body("badrequest");
	} // http상태코드가 400 badRequest
}
