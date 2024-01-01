package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day16Test {
	
	@Test
	void test() {
		String filePath = "src/test/resources/Day16_2023/test.txt";
		Long result = Day16.getAnswer(filePath, false);
		assertTrue(result == 46, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day16_2023/data.txt";
		Long result = Day16.getAnswer(filePath, false);
		assertTrue(result == 7632, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day16_2023/test.txt";
		Long result = Day16.getAnswer(filePath, true);
		assertTrue(result == 51, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day16_2023/data.txt";
		Long result = Day16.getAnswer(filePath, true);
		assertTrue(result == 8023, "Actual Answer: " + result);
	}
}
