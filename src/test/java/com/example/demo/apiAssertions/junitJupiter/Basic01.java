package com.example.demo.apiAssertions.junitJupiter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Basic01 {
	
	@Test
	void testSum() {
		assertEquals(2, 1+1);
	}
	
	@Test
	void testNotNull() {
		String str = "JUnit";
		assertNotNull(str);
	}

	@Test
	void testString02() {
		String aa = "hello, JUnit!";
		assertTrue(aa.startsWith("he"));
		assertTrue(aa.contains("JU"));
		assertTrue(aa.endsWith("t!"));
	}
}
