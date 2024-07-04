package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class QueryDslConfig {
	
	private final EntityManager entityManager;
	
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
    	return new JPAQueryFactory(entityManager);
    }
}
/**
 * Q클래스 : Gradle Tasks에서 해당프로젝트-build-build해야 생김, build/generated/sources/annotationProcessor/java/main 디렉토리에 생성됨
 * 
 * Q클래스가 있는 build/generated/sources/annotationProcessor/java/main를 Source폴더에 등록해줘야함
 * ( 프로젝트 - Properties - Java Build Path - Source - Add Folder )
 */

