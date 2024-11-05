package com.example.demo.uu.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
//	private final MemberRepository memberRepository;
//    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    
    public JwtToken signIn(String username, String password) {
    	// 1. username + password 를 기반으로 Authentication 객체 생성, 이때 인증여부를 확인하는 authenticated값이 false
    	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    	
    	
    	
    	
    	
    	// 3. 인증 정보를 기반으로 JWT 토큰 생성
    	JwtToken jwtToken = null;
    	
    	return jwtToken;
    }
    
    
    
}
