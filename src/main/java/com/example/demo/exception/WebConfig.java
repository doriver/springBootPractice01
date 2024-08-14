package com.example.demo.exception;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.exception.resolver.MyHandlerExceptionResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add(new MyHandlerExceptionResolver());
	}
}
