package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Member;
import com.example.demo.member.MemberRequestDto;
import com.example.demo.member.MemberResponseDto;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberRequestDto.CreateDto;
import com.example.demo.member.MemberResponseDto.ListDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/members")
	public List<MemberResponseDto.ListDto> getMemberList() {
		return memberService.findAll();
	}
	
	@PostMapping("/members")
	public Long createMember(@RequestBody MemberRequestDto.CreateDto createDto) {
		Member m = memberService.createMember(createDto.getName(), createDto.getAge()); 
		return m.getId();
	}
}
