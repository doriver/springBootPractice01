package com.example.demo.jwt;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class Ex01 {

	@Test
	void aa() {
		long now = (new Date()).getTime();
		/*
		 * new Date()는 현재 날짜와 시간을 나타내는 Date객체를 생성
		 * .getTime()은 해당 날짜와 시간을 1970년 1월 1일 00:00:00 UTC 기준으로 밀리초 단위의 타임스탬프로 반환
		 */
		System.out.println(now);
		System.out.println(new Date(now + 86400000).getTime());
		// now + 86400000은 현재 시간에 하루(24시간)를 밀리초 단위로 더한 값
		// new Date(now + 86400000)는 하루가 더해진 시점의 Date 객체를 생성
		
		/*
		 * 밀리초(ms) 단위에서 1초, 1분, 1시간
		 * 1초 = 1000 밀리초
		 * 1분 = 60초 × 1000 밀리초 = 60000 밀리초
		 * 1시간 = 60분 × 60000 밀리초 = 3600000 밀리초
		 */
		
	}
}
