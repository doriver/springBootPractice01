package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import lombok.RequiredArgsConstructor;


@Controller
//@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 만들어줌
public class FffController {
	
	@GetMapping("/view")
	public String vv() {
		return "vvv";
	}
	
	@GetMapping("/save")
	@ResponseBody
	public String ssvv() {
		return "save";
	}
	
	@GetMapping("/find")
	@ResponseBody
	public String ffdd(@RequestParam("nnn") Long id) {
		
		return "find  00" + id;
	}
}
