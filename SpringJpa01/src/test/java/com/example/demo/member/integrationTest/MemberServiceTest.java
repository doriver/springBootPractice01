package com.example.demo.member.integrationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Member;
import com.example.demo.member.MemberService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MemberServiceTest {

	@Autowired
	MemberService memberService;
	
	@Test
	@DisplayName("멤버 만들기")
	@Rollback(true)
	void createMemberSuccess() {
		Member m = memberService.createMember("hi1", 10);
		assertThat(m.getName()).isEqualTo("hi1");
	}
	
	@Test
	@DisplayName("이름중복으로 만들기 실패")
	@Rollback(true)
	void createMemberFail() {
		Member m = memberService.createMember("hi1", 10);
		assertThatThrownBy(() -> memberService.createMember("hi1", 12))
			.isInstanceOf(IllegalStateException.class);
	}
}
