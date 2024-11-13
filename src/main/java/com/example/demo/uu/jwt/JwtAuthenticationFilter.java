package com.example.demo.uu.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
	
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String accessToken = null;
		// 요청Header에서 JWT 토큰 추출
//		String token = resolveToken((HttpServletRequest) request);

		// 일단 Cookie로 한거 , 컨트롤러 메서드 파라미터에선 간단히 @CookieValue(name="memberId", required=false) Long memberId 이런식으로 하면되는데
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		Cookie[] cookies = httpRequest.getCookies(); // 모든 쿠키 가져오기
		if (cookies != null) {
			 for (Cookie cookie : cookies) {
				 String name = cookie.getName(); // 쿠키 이름 가져오기
                 if (name.equals("Authorization")) {
                	 accessToken = cookie.getValue(); // 쿠키 값 가져오기
                	 break;
                 }
             }
		}
		
		Map<String, Boolean> expiration = new HashMap<>();
		expiration.put("token", false);
		
		// 유효한 토큰인경우 인증권한 설정해 필터에의해 판단되도록함 , 토큰의 인증정보(Authentication)를 SecurityContext에 저장
        if (accessToken != null && jwtTokenProvider.validateToken(accessToken, expiration)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        // AccessToken이 만료된경우, RefreshToken확인해서 재발급 or 다시 로그인하라고 요청
        if (expiration.get("token")) {
        	String refreshToken = JwtTokenProvider.redis.get(accessToken);
        
        	expiration.put("token", false);
        	jwtTokenProvider.validateToken(refreshToken, expiration);
        	
        	// RefreshToken이 만료 안된경우
        	// 토큰 재발급 , SecurityContext에 토큰의 정보로 만든Authentication를 넣어 securityFilter들 통과후 요청 정상처리 , 레디스에 다시 저장, 쿠키로access전달 
        	if (!expiration.get("token")) {
            	// Jwt 토큰 복호화
                Claims claims = jwtTokenProvider.parseClaims(accessToken);
            	
            	JwtToken jwtToken = jwtTokenProvider.reGenToken(
            			claims.get("auth").toString(), (Map<String, Object>)(claims.get("info")), claims.getSubject());
            	
            	accessToken = jwtToken.getAccessToken();
            	log.info("reGen jwtToken accessToken = {}, refreshToken = {}", accessToken, jwtToken.getRefreshToken());
            	
            	JwtTokenProvider.redis.put(accessToken, jwtToken.getRefreshToken());
            	
            	HttpServletResponse httpResponse = (HttpServletResponse)response;
            	
            	Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            	
                Cookie accessCookie = new Cookie("Authorization", accessToken);
                httpResponse.addCookie(accessCookie);
        	}
        	
        	// RefreshToken이 만료된 경우는 SecurityContext에 Authentication를 안넣어 securityFilter에 의해 자동으로 걸러짐     
        }
        
		chain.doFilter(request, response);
	}
	
	// Request Header에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
