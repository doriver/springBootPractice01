package com.example.demo.jdbc;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.example.demo.jdbc.repository.MemberRepository;

public class MemberRepositoryTest {

	MemberRepository repo = new MemberRepository();
	
	@Test
	void crud() throws SQLException {
		repo.save(98, "baaa");
	}
}
