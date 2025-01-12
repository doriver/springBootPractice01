package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotViewController {

	@GetMapping("/data")
	public String ddd() {
		return "정상작동";
	}
}
