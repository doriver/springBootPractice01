package com.example.demo.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Test
	@DisplayName("멤버 만들기")
	void createMember() {
		// given
		Member member1 = Member.builder()
							.name("hi1").age(10)
							.build();
		Member member2 = Member.builder()
							.name("hi2").age(20)
							.build();
		
		// when
		Member result1 = memberRepository.save(member1);
		Member result2 = memberRepository.save(member2);
		
		// then
		assertThat(result1.getAge()).isEqualTo(member1.getAge());
		assertThat(result2.getAge()).isEqualTo(member2.getAge());
		
	}
	
	@Test
	@DisplayName("멤버리스트 반환하는지 확인")
	void MemberList() {
		// given
		Member member1 = Member.builder().name("hh11").age(10).build();
		Member member2 = Member.builder().name("hh22").age(20).build();
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		// when
		List<Member> result = memberRepository.findAll();
		
		// then
		assertThat(result.size()).isEqualTo(2);
		
	}
	
	
}
