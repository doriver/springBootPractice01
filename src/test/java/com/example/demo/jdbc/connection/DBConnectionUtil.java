package com.example.demo.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBConnectionUtil {

	public static final String URL = "jdbc:mysql://localhost:3306/jpa_test_code?characterEncoding=UTF-8";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "qlqjs";
	
	public static Connection getConnection() {
		 
		try {
			// DB에 연결
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// DB드라이버를 찾아 작동시켜 DB와 커넥션을 맺고, 해당 드라이버가 제공하는 커넥션을 반환해줌
			
			log.info("get connection={}", connection); // com.mysql.cj.jdbc.ConnectionImpl@7be58f16
			return connection;
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
		 
	}

		 
}
