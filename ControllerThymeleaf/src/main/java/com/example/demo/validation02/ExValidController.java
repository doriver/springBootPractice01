package com.example.demo.validation02;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/valid")
public class ExValidController {

	@PostMapping("/t1")
	public String ex01() {
		
		return "통과";
	}
}
