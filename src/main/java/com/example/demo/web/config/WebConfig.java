package com.example.demo.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.web.filter.LogFilter;

import jakarta.servlet.Filter;

@Configuration
public class WebConfig {

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
}
