package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RequestUrlEncodedDto {
	@NotBlank
	private String name;
	
	@Min(value=4)
	private int age;
	
	@Min(value=4)
	private int num;
}
