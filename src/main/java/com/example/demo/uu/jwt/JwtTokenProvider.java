package com.example.demo.uu.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.security.CustomUserDetails;
import com.example.demo.security.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import lombok.extern.slf4j.Slf4j;

/*
 *  JWT의 생성, 복호화, 검증기능 구현
 */
@Slf4j
@Component
public class JwtTokenProvider {
	
    private final Key key;
    
    public static Map<String, String> redis = new HashMap<>();

    // application.yml에서 secret값 가져와서 key에 저장
    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    
    // Member 정보를 가지고 AccessToken, RefreshToken을 생성하는 메서드
    public JwtToken generateToken(Authentication authentication) {
        
    	// 권한 가져오기, 뭘까?
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();
        User userInfo = userDetail.getUser();
        userInfo.setPassword(null);
        
        long now = (new Date()).getTime();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .claim("info", userInfo)
                .setExpiration(new Date(now + 2 * 60000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성, 뭘까?
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + 2 * 60000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
    
    // 토큰 재생성
    public JwtToken reGenToken(String authorities, Map<String, Object> userInfo, String sub) {
    		
    	
    	long now = (new Date()).getTime();
    	
    	String accessToken = Jwts.builder()
    			.setSubject(sub)
    			.claim("auth", authorities)
    			.claim("info", userInfo)
    			.setExpiration(new Date(now + 5 * 60000 ))
    			.signWith(key, SignatureAlgorithm.HS256)
    			.compact();
    	
    	String refreshToken = Jwts.builder()
    			.setExpiration(new Date(now + 10 * 60000))
    			.signWith(key, SignatureAlgorithm.HS256)
    			.compact();
    	
    	return JwtToken.builder()
    			.grantType("Bearer")
    			.accessToken(accessToken)
    			.refreshToken(refreshToken)
    			.build();
    }
    
    // Jwt 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        // Jwt 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        
        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        Map userInfo = (Map<String, Object>)(claims.get("info"));
        User user = new User();
        user.setAge((Integer)(userInfo.get("age")));
        user.setUsername((String)(userInfo.get("username")));
        // UserDetails 객체를 만들어서 Authentication return
//        UserDetails principal = new User(claims.getSubject(), "", authorities);
        CustomUserDetails principal = new CustomUserDetails(user ,authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token, Map<String, Boolean> expiration) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
        	expiration.put("token", true);
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    // accessToken
    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
    
    // 테스트용 ex01
    public boolean valiToken() {
    	
    	long now = (new Date()).getTime();
    	
    	String refreshToken = Jwts.builder()
                .setExpiration(new Date(now))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    	return true; // 임시로 아래 메서드 조금 수정됨
//    	return validateToken(refreshToken); // Expired JWT Token
    }
}
