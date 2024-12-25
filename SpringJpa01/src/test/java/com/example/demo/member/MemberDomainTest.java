package com.example.demo.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.entity.Member;

public class MemberDomainTest {

	@Test
	@DisplayName("멤버 생성되는지 테스트")
	void createMember() {
		// given
		Member member = Member.builder()
							.age(10).name("hi")
							.build();
		
		// when, then
		Assertions.assertThat(member.getAge()).isEqualTo(10);
		Assertions.assertThat(member.getName()).isEqualTo("hi");
	}

	@Test
	@DisplayName("멤버 나이 바뀌는지 테스트")
	void changeAgeTest() {
		// given
		Member member = Member.builder()
				.age(10).name("hi")
				.build();
		
		// when
		member.changeAge(13);
		
		// then
		Assertions.assertThat(member.getAge()).isEqualTo(13);
	}
}
