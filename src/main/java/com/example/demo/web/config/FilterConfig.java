package com.example.demo.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.web.filter.LogFilter;
import com.example.demo.web.filter.LoginCheckFilter;

import jakarta.servlet.Filter;

@Configuration
public class FilterConfig {

	/*
	 * FilterRegistrationBean를 사용해서 필터를 등록
	 */
	@Bean
	public FilterRegistrationBean logFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new LogFilter()); // 등록할 필터를 지정
		filterRegistrationBean.setOrder(1); // 필터는 체인으로 동작하기떄문에 순서가 필요
		filterRegistrationBean.addUrlPatterns("/*"); // 필터를 적용할 URL패턴 지정
		return filterRegistrationBean;
	}
//	@Bean // 인터셉터에서 로그인체크 하면 주석해야함
	public FilterRegistrationBean loginCheckFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new LoginCheckFilter()); // 등록할 필터
		filterRegistrationBean.setOrder(2); // 필터 순서
		filterRegistrationBean.addUrlPatterns("/*"); // 필터를 적용 URL패턴
		return filterRegistrationBean;
	}
	

	
}
