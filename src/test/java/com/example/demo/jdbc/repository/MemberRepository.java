package com.example.demo.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

import com.example.demo.jdbc.connection.DBConnectionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRepository {

	public String save(int age, String name) throws SQLException {
		String sql = "insert into member (age, name) values (?, ?)";
		Connection con = null;
		PreparedStatement prstmt = null;

		con = DBConnectionUtil.getConnection();
		
		try {
			prstmt = con.prepareStatement(sql); // db에 전달할 sql정의
			prstmt.setInt(1, age);
			prstmt.setString(2, name);
			prstmt.executeUpdate(); // Statement를 통해 준비된 SQL을 커넥션을 통해 DB에 전달
			// int 를 반환하는데 영향받은 DB row 수를 반환한다. 여기서는 하나의 row를 등록했으므로 1을 반환
			
			return "success";			
		} catch (SQLException e) {
			log.error("db error", e);
			throw e;
		} finally {
			close(con, prstmt, null);
		}
	}
	
	// 데이터를 변경할 때는 executeUpdate(), 데이터를 조회할 때는 executeQuery()를 사용
	public String findById(Long id) throws SQLException {
		String sql = "select * from member where id = ?";
		
		Connection con = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		
		con = DBConnectionUtil.getConnection();
		
		try {
			prstmt = con.prepareStatement(sql);
			prstmt.setLong(1, id);
			
			rs = prstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getString("name") + ": " + rs.getInt("age");
			} else {
				throw new NoSuchElementException("member not found Id="+ id);
			}
				
		} catch (SQLException e) {
			log.error("db error", e);
			throw e;
		} finally {
			close(con, prstmt, rs);
		}
		
	}
	
	
	
	// 리소스 정리
	// 안해주면,커넥션이 끊어지지 않고 계속 유지되는 문제가 발생할 수 있다( 리소스 누수 )
	private void close(Connection con, Statement stmt, ResultSet rs) {
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
