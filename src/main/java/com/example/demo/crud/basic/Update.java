package com.example.demo.crud.basic;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/uu")
public class Update {

	private final UserRepository userRepository;
	
	@PatchMapping("/uu")
	public void update() {
		
	}

	
}
