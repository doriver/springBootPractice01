package com.example.demo.member;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
		return memberService.createMember(createDto.getName(), createDto.getAge());
	}
}
