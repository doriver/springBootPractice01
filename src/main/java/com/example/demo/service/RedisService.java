package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	
	/*
	 * RedisTemplate가 저장, 읽기 핵심 역할
	 * 
	 * config쪽에서 Connection, Key, Value 세팅 해줌
	 * RedisTemplate > ValueOperations > set(), get()
	 */
	public void save(String key, String value) {
		redisTemplate.opsForValue().set(key, value); 
	}
	
	public String get(String key) {
		return (String)redisTemplate.opsForValue().get(key);
	}
}
