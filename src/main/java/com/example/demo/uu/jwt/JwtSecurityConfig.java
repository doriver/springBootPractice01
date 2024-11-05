package com.example.demo.uu.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // Spring Security를 활성화함
@RequiredArgsConstructor
public class JwtSecurityConfig {

	private final JwtTokenProvider jwtTokenProvider;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			// REST API이므로 basic auth 및 csrf 보안을 사용하지 않음
			.httpBasic(basic -> basic.disable())
			.csrf(csrf -> csrf.disable())
        
	        // JWT를 사용하기 때문에 세션을 사용하지 않음
	        .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
        
	        // HTTP요청에 대한 권한 설정
	        .authorizeHttpRequests(requests -> requests	    
	            .requestMatchers("/members/sign-in").permitAll() // 해당 API에 대해서는 모든 요청을 허가	            
	            .requestMatchers("/members/test").hasRole("USER") // USER 권한이 있어야 요청할 수 있음       
	            .anyRequest().authenticated() // 이 밖에 모든 요청에 대해서 인증을 필요
	        )
        
	        // JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행
	        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider)
	        		, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
	}
	
	
	// 비밀번호 인코딩 Bean도 추가 해줘야함
}
