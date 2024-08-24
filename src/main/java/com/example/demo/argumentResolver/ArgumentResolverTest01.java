package com.example.demo.argumentResolver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArgumentResolverTest01 {

	@GetMapping("/user-info")
    public String getUserInfo(UserInfo userInfo) {
        // HandlerMethodArgumentResolver에 의해 UserInfo 객체가 자동으로 바인딩됩니다.
        return "User Info: " + userInfo.toString();
    }
}
