package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.RedisService;

@RestController
@RequestMapping("/redis")
public class RrestController01 {

	@Autowired
	private RedisService redisService;
	
	@PostMapping("/save")
	public String save(@RequestParam String key, @RequestParam String value) {
		redisService.save(key, value);
		return "saved";
	}
	
	@GetMapping("/get")
	public String get(@RequestParam String key) {
		return redisService.get(key);
	}
}
