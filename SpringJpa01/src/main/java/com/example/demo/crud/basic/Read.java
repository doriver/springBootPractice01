package com.example.demo.crud.basic;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{id}")
	public Optional<User> readById(@PathVariable("id") Long id) {
		return userRepository.findById(id);
	} // 성공

	@GetMapping("/n/{name}")
	public List<User> readByUsername(@PathVariable("name") String name) {
		return userRepository.findByUsername(name);
	} // 성공
/*
 * where문에 조건 여러개	
 */
	@GetMapping("/2/{name}/{email}")
	public List<User> readBytwo(@PathVariable("name") String name
			, @PathVariable("email") String email) {
		return userRepository.findByUsernameAndEmail(name, email);
	} // ㅅㄱ

/*
 * 내가 원하는 필드만
 */
	@GetMapping("/f/{id}")
	public String fieldChoice(@PathVariable("id") Long id) {
		return userRepository.findUsernameById(id);
	} // ㅅㄱ
	
	@GetMapping("/fs/{email}")
	public List<Object> fieldsChoice(@PathVariable("email") String email) {
		return userRepository.findTwoFieldsByEmail(email);
	} // ㅅㄱ

/*
 * Order By
 */
	@GetMapping("/o/email")
	public List<User> oo() {
		return userRepository.findAllByOrderByEmailDesc();
	} // ㅅㄱ
	
	
	public void aa() {
//		userRepository
	}
}
