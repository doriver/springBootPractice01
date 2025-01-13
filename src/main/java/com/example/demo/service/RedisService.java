package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	
    @Cacheable(value = "oneParam", key = "#p0")
    public String getOnCache(String p0) {      
        int a = 0; // 디버깅용 
        return "someData";
    }

    @Cacheable(value = "multiParam", key = "#p0 + '_' + #p1")
    public String get22OnCache(String p0, String p1) {
        int a = 0; // 디버깅용 
        return "multiParam someData";
    }
	
	
	/*
	 * RedisTemplate가 저장, 읽기 핵심 역할
	 * 
	 * config쪽에서 Connection, Key, Value 세팅 해줌
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
	
	
	// sadd : 집합에 value추가
    public Long sadd(String key, String value) {
        return redisTemplate.opsForSet().add(key, value);
    }
    // scard : 집합 원소개수 얻믕
    public Long scard(String key) {
        return redisTemplate.opsForSet().size(key);
//    	Object oo = redisTemplate.opsForSet().size(key);
//        return 0L;
    }
    // sismember : 집합에 value값 존재하는지 확인
    public Boolean sismember(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }
    // srem : 집합에서 해당원소 제거
    public Long srem(String key, String value) {
        return redisTemplate.opsForSet().remove(key, value);
    }
    // del : 해당key 데이터 삭제
    public void del(String key) {
        redisTemplate.delete(key);
    }
}
