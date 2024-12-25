package com.example.demo.crud.basic;


import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cc")
public class Create {
	
	private final UserRepository userRepository;
	
	@PostMapping("")
	public User create(@RequestBody Map<String, String> data) {
		User user = User.builder()
				.email(data.get("email")).username(data.get("username"))
				.build();
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	public void aa() {
//		userRepository
	}
	
	
}
