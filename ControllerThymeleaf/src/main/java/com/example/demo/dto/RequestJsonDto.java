package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestJsonDto {
	@NotBlank
	private String name;
	
	@Min(value=4)
	private int age;
	
	@Min(value=4)
	private int num;
}
