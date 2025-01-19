package com.example.demo.redis;

import org.springframework.data.annotation.Id;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@RedisHash(value = "jwtToken", timeToLive = 60*3)
public class RefreshToken {
/*
 * @RedisHash 안쓸꺼임
 * 이상한데이터들 생김
 * 
 */
	@Id
    private String id;

    private String refreshToken;

    @Indexed
    private String accessToken;
}
