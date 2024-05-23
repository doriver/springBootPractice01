package com.example.demo.repo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.UserSns;
import com.example.demo.repository.UserSnsRepository;

@DataJpaTest// 이거 하나만 있으면 안됨
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserSnsRepositoryTest {

	@Autowired
	UserSnsRepository repo;
	
	@Test
	public void see() {
		Optional<UserSns> ou = repo.findById(1L);
		System.out.println("aa");
	}
	@Test
	public void seaae() {
		UserSns us = new UserSns();
		us.setEmail("1");
		us.setLocation("1");
		us.setLoginId("1");
		us.setNickName("1");
		us.setPassword("1");
		us.setProfileImage("1");
		us.setProfileStatusMessage("1");
		
		UserSns saved = repo.save(us);
		
		System.out.println("aa");
	}
}
