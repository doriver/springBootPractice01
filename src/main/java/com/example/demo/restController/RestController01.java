package com.example.demo.restController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@RestController
//@RequestMapping("/rc01")
@Slf4j
public class RestController01 {
	
	@GetMapping("/rcss")
	public String sdf() {
		return "asdjflajdfl";
	} // 이렇게 하면 그냥 화면이 String값만을 보여줘버림

	@GetMapping("/rree")
	public ResponseEntity<String> saadf() {
		return ResponseEntity.ok().body("ResponseEntity.ok().body");
	} // 화면에 ResponseEntity.ok().body 출력
}
