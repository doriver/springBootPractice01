package com.example.demo.apiAssertions.assertjCore;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

public class Basic01 {

	@Test
	void testString01() {
		String aa = "hello, assertJ!";
		assertThat(aa).startsWith("hello").endsWith("assertJ!");
	}

	@Test
	void testString02() {
		String aa = "hello, assertJ!";
		assertThat(aa)
			.isNotNull()
			.startsWith("hello")
			.contains("ass")
			.endsWith("J!");
	}
	
	@Test
	void testCollection() {
		List<String> list = new LinkedList<>();
		list.add("사과");
		list.add("귤");
		list.add("토마토");
		
		assertThat(list).contains("귤").hasSize(3);
	}
}
