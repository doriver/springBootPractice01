package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

	@GetMapping("/")
	public String hhh() {
		return "home";
	}
	
	@GetMapping("/plogin")
	public String lll(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return "login";
	}

	@GetMapping("/suc")
	public String sss(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return "success";
	}
}
