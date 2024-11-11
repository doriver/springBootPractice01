package com.example.demo.uu.jwt;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
	
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String token = null;
		// 요청Header에서 JWT 토큰 추출
//		String token = resolveToken((HttpServletRequest) request);

		// 일단 Cookie로 한거 , 컨트롤러 메서드 파라미터에선 간단히 @CookieValue(name="memberId", required=false) Long memberId 이런식으로 하면되는데
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		Cookie[] cookies = httpRequest.getCookies(); // 모든 쿠키 가져오기
		if (cookies != null) {
			 for (Cookie cookie : cookies) {
				 String name = cookie.getName(); // 쿠키 이름 가져오기
                 if (name.equals("Authorization")) {
                	 token = cookie.getValue(); // 쿠키 값 가져오기
                	 break;
                 }
             }
		}
		
		// 유효한 토큰인경우 해당 토큰의 인증 정보(Authentication)를 SecurityContext에 저장
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
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
