package com.example.demo.crud.basic;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dd")
public class Delete {

	private final UserRepository userRepository;
	
	@DeleteMapping("/dd")
	public void delete() {
		
	}
}
