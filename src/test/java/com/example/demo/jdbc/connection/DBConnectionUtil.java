package com.example.demo.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	
	// 리소스 정리
	// 안해주면,커넥션이 끊어지지 않고 계속 유지되는 문제가 발생할 수 있다( 리소스 누수 )
	public static void close(Connection con, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.info("error", e);
			}
		}
		
		// PreparedStatement는 Statement의 자식 타입, ? 를 통한 파라미터 바인딩을 가능하게 해줌
		// SQL Injection 공격을 예방하려면 PreparedStatement를 통한 파라미터 바인딩 방식을 사용해야함
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				log.info("error", e);
			}
		}
		
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				log.info("error",e);
			}
		}
		
	}

		 
}
