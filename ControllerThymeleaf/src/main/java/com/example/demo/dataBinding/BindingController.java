package com.example.demo.dataBinding;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RequestJsonDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bind")
public class BindingController {

	@PostMapping("/t1")
	public String ex01(@RequestBody RequestJsonDto dto) {
		log.info("{} {}", dto.getName(), dto.getAge());
		return "성공";
	}
}
