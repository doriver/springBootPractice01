package com.example.demo.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration 
@EnableCaching
public class RedisConfig {

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
	    config.setDatabase(5); // 5번 DB 설정
		return new LettuceConnectionFactory(config);
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		return template;
	}
	
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig() // static메서드 defaultCacheConfig
                .entryTtl(Duration.ofHours(1)) // 캐시에 저장된 항목의 Time-To-Live(TTL)을 설정, 캐시 항목의 유효한 기간을 의미, 이 기간이 지나면 캐시에서 자동으로 제거
                .disableCachingNullValues(); // null 값을 캐시에 저장하지 않도록 설정
        
        /*
         * .prefixCacheNameWith(this.getClass().getPackageName() + ".") // 이거 있으면 Redis에 저장되는 key앞에 com.example.demo.config. 붙음
         * 
         * serializeKeysWith() : Redis에 저장되는 캐시 키를 직렬화하는 방식을 지정, serializeKeysWith()이 없으면 기본 직렬화 방식이 사용됨
         * 
         * .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
         * .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
         */
        
        return RedisCacheManager.builder(connectionFactory) // static메서드 builder 반환타입은 RedisCacheManagerBuilder 
                .cacheDefaults(config) //
                .build();
    }
}
