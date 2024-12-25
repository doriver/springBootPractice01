package com.example.demo.member;

import java.util.List;

import com.example.demo.entity.Member;

public interface MemberService {

	List<MemberResponseDto.ListDto> findAll();
	Member createMember(String name, int age);
}
