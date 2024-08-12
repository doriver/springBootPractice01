package com.example.demo.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@ExtendWith(SpringExtension.class)
public class MemberServiceTest {

	// Test 주체
	MemberService memberService;
	
	// Test 협력자
	@MockBean // 가짜 객체를 만드는 역할, 가짜 객체이므로 응답을 정의해줘야 함
	MemberRepository memberRepository;
	
	@BeforeEach // 각각의 @Test실행 전 실행됨
	void setUp() {
		memberService = new MemberServiceImpl(memberRepository);
	}
	
	@Test
	@DisplayName("멤버 생성 성공")
	void createMemberSuccess() {
		// given		
			// 가짜 객체의 로직에대해, 응답을 정의
		Mockito.when(memberRepository.save(Mockito.any(Member.class)))
				.thenAnswer(i -> i.getArguments()[0]); // 뭔진 모름, 구글링해서 테스트 해봤는데 됐음
		// now you can obtain 객체 which you pass as argument.
		
		// 테스트 해본거
//		Member member3 = Member.builder().name("hi3").age(10).build();
//		Member m = memberRepository.save(member3);
//		System.out.println(m);
		
		// when
		Member member = memberService.createMember("hi3", 10);
		
		// then
		assertThat(member.getName()).isEqualTo("hi3");
	}
	
	@Test
	@DisplayName("멤버 생성시 member1과 이름이 같아서 예외 발생")
	void createMemberFail() {
		// given
		Member member1 = Member.builder().name("hi1").age(10).build();
		Mockito.when(memberRepository.findByName("hi1")).thenReturn(Optional.of(member1));
		
		// when, then
		assertThatThrownBy(() -> memberService.createMember("hi1", 10))
							.isInstanceOf(IllegalStateException.class);
			// assertThatThrownBy는 예외 발생을 검증하는 메서드
			// asserThatThrownBy(() -> 예외를 발생시킬 로직).isInstanceOf(예외 클래스)
	}

	@Test
	@DisplayName("멤버 생성시 member1과 다른 이름")
	void createMember() {
		// given
		Member member1 = Member.builder().name("hi1").age(10).build();
		Mockito.when(memberRepository.findByName("hi1")).thenReturn(Optional.of(member1));
		
		// when, then
		assertThatThrownBy(() -> memberService.createMember("hi2", 10))
		.isInstanceOf(IllegalStateException.class);
	}
}
