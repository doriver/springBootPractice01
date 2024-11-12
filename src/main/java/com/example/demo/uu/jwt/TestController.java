package com.example.demo.uu.jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
	
	private final JwtTokenProvider jwtTokenProvider;
	
    @GetMapping("/ex01")
    public boolean asdf() {
    	
    	
    	return jwtTokenProvider.valiToken();
    }
}
