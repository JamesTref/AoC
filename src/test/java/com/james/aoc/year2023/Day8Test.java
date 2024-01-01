package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day8Test {
	
	@Test
	void test() throws Exception {
		String filePath = "src/test/resources/Day8_2023/test.txt";
		Long result = Day8.getAnswer(filePath, false);
		assertTrue(result == 2, "Actual Answer: " + result);
	}
	
	@Test
	void test2() throws Exception {
		String filePath = "src/test/resources/Day8_2023/test2.txt";
		Long result = Day8.getAnswer(filePath, false);
		assertTrue(result == 6, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() throws Exception {
		String filePath = "src/test/resources/Day8_2023/data.txt";
		Long result = Day8.getAnswer(filePath, false);
		assertTrue(result == 11911, "Actual Answer: " + result);
	}
	
	@Test
	void test3() throws Exception {
		String filePath = "src/test/resources/Day8_2023/test3.txt";
		Long result = Day8.getAnswer(filePath, true);
		assertTrue(result == 6, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() throws Exception {
		String filePath = "src/test/resources/Day8_2023/data.txt";
		Long result = Day8.getAnswer(filePath, true);
		assertTrue(result == 10151663816849l, "Actual Answer: " + result);
	}
	
}
