package com.example.demo.redis;

import java.util.concurrent.TimeUnit;

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


	
	
	
	@PostMapping("/saveT")
	public String sav(@RequestParam("key") String key, @RequestParam("value") String value) {
		redisRepo.saveWithTTL(key, value, 1, TimeUnit.MINUTES);
		return "saved";
	}
	
	
	@PostMapping("/save")
	public String save(@RequestParam("key") String key, @RequestParam("value") String value) {
		redisRepo.save(key, value);
		return "saved";
	}
	
	@GetMapping("/get")
	public String get(@RequestParam("key") String key) {
		return redisRepo.get(key);
	}
	
	
	
	@Autowired
	private RefreshTokenRepository refreshRepo;
	
	@GetMapping("/refresh")
    public void saveTokenInfo(@RequestParam("id") String id) {
    	refreshRepo.save(new RefreshToken(id, "aa", "bb"));
    }
}
