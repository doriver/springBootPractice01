package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private RedisRepo redisRepo;
	
	@PostMapping("/save")
	public String save(@RequestParam("key") String key, @RequestParam("value") String value) {
		redisRepo.save(key, value);
		return "saved";
	}
	
	@GetMapping("/get")
	public String get(@RequestParam("key") String key) {
		return redisRepo.get(key);
	}
}
