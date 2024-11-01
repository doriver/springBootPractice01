package com.example.demo.jdbc;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.example.demo.jdbc.transaction.Transaction01;

public class TransactionTest {

	Transaction01 trs = new Transaction01();
	
//	@Test
	void rollback() throws Exception {
		trs.insertRollback(13, "tyu");
	}
	
	@Test
	void commit() throws Exception{
		trs.insertCommit();
	}
}
