package com.example.demo.jdbc;

import java.sql.Connection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.demo.jdbc.connection.DBConnectionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBConnectionUtilTest {

	@Test
	void connection() {
		Connection connection = DBConnectionUtil.getConnection();
		Assertions.assertThat(connection).isNotNull();
	}
}
