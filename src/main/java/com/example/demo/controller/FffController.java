package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.example.demo.entity.QUser;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 만들어줌
public class FffController {
	
	private final UserRepository userRepository; 
	private final JPAQueryFactory queryFactory;
	
	@GetMapping("/view")
	public String vv() {
		return "vvv";
	}
	
	@GetMapping("/save")
	@ResponseBody
	public User ssvv(@RequestParam("un") String userName, @RequestParam("em") String email) {
		User us = User.builder()
				.username(userName).email(email)
				.build();
		return userRepository.save(us);
	}
	
//	@GetMapping("/find")
//	@ResponseBody
//	public Optional<User> ffdd(@RequestParam("nnn") Long id) {
//		Optional<User> ou = userRepository.findById(id);
//		return ou;
//	}

//	@GetMapping("/qdfind")
//	@ResponseBody
//	public List<User> ffdd(@RequestParam("nnn") String username) {
//		
//		QUser qUser = QUser.user;
//
//		return queryFactory.selectFrom(qUser)
//				.where(qUser.username.eq(username))
//				.fetch();
//	}
}
