package com.example.demo.crud.basic;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rr")
public class Read {

	private final UserRepository userRepository;
	
	@GetMapping("/all")
	public List<User> readAll() {
		return userRepository.findAll();
	} // 성공
	
	
	
}
