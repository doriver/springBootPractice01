package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Spring Security를 활성화함  
public class SecurityConfig {

	/* 
	 * 구버전에선 AuthenticationManagerBuilder auth , auth.inMemoryAuthentication() ~ 였음
	 * 메모리 내 인증을 사용 + 'user'라는 사용자와 '1111'라는 비밀번호를 가진 사용자 추가
	 * 
	 * UserDetailsService의 구현체 InMemoryUserDetailsManager를 스프링빈 등록
	 * User로 UserDetails을 빌드해서 InMemoryUserDetailsManager에 담음
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("1111")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}

	/*
	 * HttpSecurity에서 빌드해서 SecurityFilterChain스프링빈으로 등록해줌
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// HTTP 요청에 대한 보안 설정을 구성합니다.
        http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/").permitAll()
				.anyRequest().authenticated()
			)  
        	.formLogin((form) -> form
				.loginPage("/plogin")
				.permitAll()
				.loginProcessingUrl("/login")
                .defaultSuccessUrl("/suc")
			)           
        	.logout((logout) -> logout
        			.logoutUrl("/custom-logout")  // 로그아웃 URL을 변경합니다.
                	.logoutSuccessUrl("/")  // 로그아웃 성공 후 리디렉션할 URL을 설정
                	.deleteCookies("JSESSIONID")
        			.permitAll()
        	);
     
        return http.build();
	}
}
