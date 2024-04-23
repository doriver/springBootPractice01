package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/c01")
@Slf4j
public class Controller01 {
	
	@GetMapping("/first")
	public String first(Model model) {
		model.addAttribute("message","Model addAttribute");
		return "first";
	}

	/**
	 * 데이터를 반환
	 */
	@ResponseBody
	@PostMapping("/aaa")
	public String aaa(@RequestParam("nnn") String name) {
		log.info("========= form recieve =========");
		
		log.info("데이터( 이름 ) : " + name);
		
		return name; 
		// form제출했을땐, url이 c01/aaa로 바뀌면서그냥 화면이 name값만을 보여줘버림
		// ajax요청 했을땐, url변경 없이, success부분 메소드만 뜸 
	}

	/**
	 * HTML파일 반환
	 */
//	@PostMapping("/aaa")
//	public String aaa(Model model, @RequestParam("nnn") String name) {
//		log.info("========= form recieve =========");
//		
//		log.info("데이터( 이름 ) : " + name);
//		model.addAttribute("message",name);
//		
//		return "second"; 
//		// form제출했을땐, url이 c01/aaa로 바뀌면서 해당HTML파일 렌더링됨
//		// ajax요청 했을땐, 거의 에러,  url변경 없이, HTML소스가 넘어가버림 
//	}
}
