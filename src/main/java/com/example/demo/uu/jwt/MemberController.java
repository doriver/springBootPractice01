package com.example.demo.uu.jwt;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.CustomUserDetails;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
	
	private final MemberService memberService;

    @PostMapping("/sign-in")
    public String signIn(@RequestBody SignInDto signInDto, HttpServletResponse response) {
        String username = signInDto.getUsername();
        String password = signInDto.getPassword();
        JwtToken jwtToken = memberService.signIn(username, password);
        
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setBearerAuth(jwtToken.getAccessToken());
        
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();
        
        
        /*ACCESS TOKEN 쿠키로 발급*/
        Cookie accessCookie = new Cookie("Authorization", jwtToken.getAccessToken());
        accessCookie.setMaxAge(60 * 10); // 10분 동안 유효
//        accessCookie.setPath("/");
//        accessCookie.setDomain("localhost");
//        accessCookie.setSecure(false);

        response.addCookie(accessCookie);
        
        
        return "login success";
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    /*
     * 이건 의도한대로 작동
     * Authorization헤더 추가하면 정상응답
     * 헤더추가안하면 에러메시지 아예 없고 403상태만 있음
     */
    @PostMapping("/aaa")
    public String testaa() {
    	return "인증필요한 요청";  
    }
}
