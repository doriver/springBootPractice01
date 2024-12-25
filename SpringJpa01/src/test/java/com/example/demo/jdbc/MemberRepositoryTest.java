package com.example.demo.jdbc;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.example.demo.jdbc.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRepositoryTest {

	MemberRepository repo = new MemberRepository();
	
//	@Test
	void save() throws SQLException {
		repo.save(98, "baaa");
	}

	@Test
	void read() throws SQLException {
		String a = repo.findById(12L);
		log.info(a);
	}
}
