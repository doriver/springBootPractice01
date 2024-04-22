package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/01")
@Slf4j
public class Controller01 {
	
	@RequestMapping("/aa")
	public String first(Model model) {
		model.addAttribute("message","Model addAttribute");
		return "first";
	}

	@ResponseBody
	@RequestMapping("/asd")
	public String aaa() {
		log.info("========= asd =========");
		return "hello";
	}
}
