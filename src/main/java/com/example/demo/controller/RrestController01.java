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
	/*
	 * Postman에서 Post요청으로 http://localhost:8080/redis/save?key=12&value=345 요청해서 성공
	 * Query Params에 넣으면 저렇게 uri에 나옴
	 */
	@PostMapping("/save")
	public String save(@RequestParam("key") String key, @RequestParam("value") String value) {
		redisService.save(key, value);
		return "saved";
	}
	
	/*
	 * 브라우저에서 http://localhost:8080/redis/get?key=key 로 요청해서 원하는 결과 얻음
	 */
	@GetMapping("/get")
	public String get(@RequestParam("key") String key) {
		return redisService.get(key);
	}
}
