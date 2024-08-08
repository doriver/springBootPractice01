package com.example.demo.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.member.MemberResponseDto.ListDto;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository;

	@Override
	public List<MemberResponseDto.ListDto> findAll() {

		return memberRepository.findAll()
				.stream().map(a -> new MemberResponseDto.ListDto(a.getName(), a.getAge()))
				.collect(Collectors.toList());
	}

	@Override
	public Long createMember(String name, int age) {

		memberRepository.findByName(name).ifPresent(a -> {
			throw new IllegalStateException("이미 있는 아이디");
		});
		
		Member member = Member.builder()
							.age(age).name(name).build();
		
		return memberRepository.save(member).getId();
	}
	
	
}
