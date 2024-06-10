package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.security.CustomAuthenticationSuccessHandler;
import com.example.demo.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity // Spring Security를 활성화함 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final CustomUserDetailsService userDetailsService;
	
	public SecurityConfig(CustomUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * UserDetailsService로 사용자 인증 처리
		 * PasswordEncoder가 비밀번호를 비교하는 역할
		 * ( SpringSecurity01에선 메모리 내 인증 사용했었음 )
		 */
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // 나이 31 비번 1111로 고정 , username은 입력값
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/").permitAll() 
                .anyRequest().authenticated() 
                .and()         
            .formLogin()
                .loginPage("/plogin")  
                .permitAll()  
                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/suc") // successHandler() 넣어주면 defaultSuccessUrl없애고 AuthenticationSuccessHandler여기에서 response.sendRedirect("/suc"); 추가해줘야함 
                .successHandler(new CustomAuthenticationSuccessHandler()) // AuthenticationSuccessHandler 사용할수 있게 등록
                .and() 
            .logout()
            	.logoutUrl("/custom-logout") 
            	.logoutSuccessUrl("/")  
            	.deleteCookies("JSESSIONID")
                .permitAll(); 
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // NoOpPasswordEncoder 비밀번호를 평문으로 저장하며, 암호화를 하지 않음
    }
}
