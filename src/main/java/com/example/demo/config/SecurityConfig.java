package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // Spring Security를 활성화함  
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * 메모리 내 인증을 사용 + 'user'라는 사용자와 '1234'라는 비밀번호를 가진 사용자 추가
		 */
		 auth.inMemoryAuthentication()
         .withUser("user")
         .password("{noop}1234")  // {noop}은 비밀번호 암호화를 사용하지 않음을 나타냅니다.
         .roles("USER");  // 사용자의 역할을 설정합니다.

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// HTTP 요청에 대한 보안 설정을 구성합니다.
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()  // '/' 경로는 인증 없이 접근을 허용합니다.
                .anyRequest().authenticated()  // 나머지 모든 요청은 인증을 요구합니다.
         //     인증 필요한 경로에, 인증없이 접근했을경우 > 로그인페이지로 연결됨
                .and()
  // formLogin() 사용하면, 폼 기반 로그인을 활성화         
            .formLogin()
                .loginPage("/plogin")  // 시발 왜 이거 없애니까 되냐 , 로그인 페이지의 경로를 설정합니다.
                .permitAll()  // 로그인 페이지는 인증 없이 접근을 허용합니다.
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/suc") // 로그인 성공 시 리디렉션할 URL 
                .and()
   // logout() 메서드를 사용하면 Spring Security가 자동으로 로그아웃 기능을 처리해줌       
            .logout()
            	.logoutUrl("/custom-logout")  // 로그아웃 URL을 변경합니다.
            	.logoutSuccessUrl("/")  // 로그아웃 성공 후 리디렉션할 URL을 설정
            	.deleteCookies("JSESSIONID")
                .permitAll();  // 로그아웃도 인증 없이 접근을 허용합니다.
	}
/*
 * 로그아웃 처리
 * 사용자의 세션을 무효화
 * 인증된 사용자의 보안 컨텍스트를 지웁니다.
 */
}
