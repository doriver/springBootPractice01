package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
/*
 * IllegalStateException: Cannot resolve parameter names for constructor
 * IDE에서 -parameter 옵션을 Compile 시에 활성화 시켜야 해결됨
 */
@Getter
@RequiredArgsConstructor
public class RequestUrlEncodedDto {
	@NotBlank
	private final String name;
	
	@Min(value=4)
	private final int age;
	
	@Min(value=4)
	private final int num;
}
