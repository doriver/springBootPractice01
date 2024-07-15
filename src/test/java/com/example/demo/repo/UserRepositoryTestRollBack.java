package com.example.demo.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/*
 * 롤백 되게 한거
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@RequiredArgsConstructor // 이거 있으니까 안되네
public class UserRepositoryTestRollBack {

	@Autowired
	UserRepository userRepository;
	
	@Test
	public void save() {
		User us = User.builder()
				.username("noSave").email("nono")
				.build();
		
		User inserted = userRepository.save(us);
		
		Optional<User> ou = userRepository.findById(inserted.getId());
		
		System.out.println(inserted);
		System.out.println(ou);
		
		assertThat(inserted).isEqualTo(ou.get());
	}
}
