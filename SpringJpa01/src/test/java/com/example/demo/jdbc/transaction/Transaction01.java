package com.example.demo.jdbc.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.demo.jdbc.connection.DBConnectionUtil;
import com.example.demo.jdbc.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Transaction01 {

	public void insertRollback(int age, String name) throws SQLException {
		Connection con = DBConnectionUtil.getConnection();
		
		try {
			con.setAutoCommit(false); //트랜잭션 시작

			save(con,age,name);
			saveXX(con);
			
			con.commit(); //성공시 커밋
		} catch (Exception e) {
			con.rollback(); //실패시 롤백
			throw new IllegalStateException(e);
		} finally {
			release(con);
		}
		
	}
	
	public void insertCommit() throws SQLException {
		Connection con = DBConnectionUtil.getConnection();
		
		try {
			con.setAutoCommit(false); //트랜잭션 시작
			
			save(con,4,"qqqd");
			save(con,5,"mmmm");

			con.commit(); //성공시 커밋
		} catch (Exception e) {
			con.rollback(); //실패시 롤백
			throw new IllegalStateException(e);
		} finally {
			release(con);			
		}
		
	}
	
	public void saveXX(Connection con) throws SQLException {
		String sql = "insert into member (age, name) values (aa, bb)";
		
		PreparedStatement prstmt = null;
	
		try {
			prstmt = con.prepareStatement(sql); // db에 전달할 sql정의
			prstmt.executeUpdate(); 
				
		} catch (SQLException e) {
			log.error("db error", e);
			throw e;
		} finally {
			stClose(prstmt);
		}
	}
	
	public String save(Connection con, int age, String name) throws SQLException {
		String sql = "insert into member (age, name) values (?, ?)";
		
		PreparedStatement prstmt = null;
	
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
			stClose(prstmt);
		}
	}
	
	public void stClose(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				log.info("error", e);
			}
		}
	}
	
	private void release(Connection con) {
		if (con != null) {
			try {
				con.setAutoCommit(true); //커넥션 풀 고려
				con.close();
			} catch (Exception e) {
				log.info("error", e);
			}
		}
	}
}
