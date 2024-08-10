package com.example.demo.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.repository.MemberRepository;

@ExtendWith(SpringExtension.class)
public class MemberServiceTest {

	// Test 주체
	MemberService memberService;
	
	// Test 협력자
	@MockBean // 가짜 객체를 만드는 역할, 가짜 객체이므로 응답을 정의해줘야 함
	MemberRepository memberRepository;
	
	@BeforeEach
	void setUp() {
		memberService = new MemberServiceImpl(memberRepository);
	}
}
