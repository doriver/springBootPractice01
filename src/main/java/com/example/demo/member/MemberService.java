package com.example.demo.member;

import java.util.List;

public interface MemberService {

	List<MemberResponseDto.ListDto> findAll();
	Long createMember(String name, int age);
}
