package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 만들어줌
public class FffController {
	
	private final UserRepository userRepository; 
	
	@GetMapping("/view")
	public String vv() {
		return "vvv";
	}
	
	@GetMapping("/save")
	@ResponseBody
	public User ssvv(@RequestParam("un") String userName, @RequestParam("em") String email) {
		User us = new User();
		us.setUsername(userName);
		us.setEmail(email);
		return userRepository.save(us);
	}
	
	@GetMapping("/find")
	@ResponseBody
	public Optional<User> ffdd(@RequestParam("nnn") Long id) {
		Optional<User> ou = userRepository.findById(id);
		return ou;
	}
}
