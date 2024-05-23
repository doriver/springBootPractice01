package com.example.demo.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@SpringBootTest // 이거 없으면 안됨 , 다른 방법도 있을듯?
public class UserRepositoryTest {
	@Autowired 
	UserRepository userRepository;
	
	@Test
	public void findById() {
		// given
		Long id = 1L;
		
		// when
		Optional<User> ou = userRepository.findById(id);
		
		// then
		assertEquals(ou.get().getEmail(), "naver.com");
	}
}
