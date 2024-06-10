package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String sss(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		model.addAttribute("age", session.getAttribute("age")); // 31로 고정
		return "success";
	}
}
