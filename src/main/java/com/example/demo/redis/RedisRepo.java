package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepo {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	/*
	 * RedisTemplate가 저장, 읽기 핵심 역할
	 * 
	 * config쪽에서 
	 * RedisTemplate에 Connection, Key, Value 세팅 해줌
	 * RedisTemplate > ValueOperations > set(), get()
	 */
	
	// set : key-value형태로 저장
	public void save(String key, String value) {
		redisTemplate.opsForValue().set(key, value); 
	}
	
	// get : key에 해당하는 value값 얻음
	public String get(String key) {
		return (String)redisTemplate.opsForValue().get(key);
	}
}
