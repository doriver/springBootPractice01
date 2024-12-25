package com.example.demo.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MemberRequestDto {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CreateDto {
		String name;
		int age;
	}
}
