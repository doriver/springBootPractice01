package com.example.demo.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

/**
 * 이미 테스트 해서 성공한 메소드들
 */

@DataJpaTest// 이거 하나만 있으면 안됨
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTestSuccess {
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
	
	@Test
	public void findAll() {
		List<User> li = userRepository.findAll();
		System.out.println(li);
	}

	@Test
	public void save() {
		User us = new User();
		us.setUsername("asdad");
		us.setEmail("gooom");
		
		User inserted = userRepository.save(us);
		
		Optional<User> ou = userRepository.findById(inserted.getId());
		
		System.out.println(inserted);
		System.out.println(ou);
		
		assertThat(inserted).isEqualTo(ou.get());
	}
	
	@Test
	public void deleteById() {
		userRepository.deleteById(7L);
	}
	
	@Test
	public void findBy() {
		List<User> un = userRepository.findByUsername("nnn");
		
		List<User> em = userRepository.findByEmail("gooom");
		
		System.out.println("aa");
	}
}
