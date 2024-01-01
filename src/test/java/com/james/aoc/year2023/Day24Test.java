package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day24Test {
	
	@Test
	void test() {
		String filePath = "src/test/resources/Day24_2023/test.txt";
		Long result = Day24.getAnswer(filePath, 7, 27, false);
		assertTrue(result == 2, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day24_2023/data.txt";
		Long result = Day24.getAnswer(filePath, 200000000000000.0, 400000000000000.0, false);
		assertTrue(result == 16018, "Actual Answer: " + result);
	}
	@Test
	void test2() {
		String filePath = "src/test/resources/Day24_2023/test.txt";
		Long result = Day24.getAnswer(filePath, 0, 0, true);
		assertTrue(result == 47, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day24_2023/data.txt";
		Long result = Day24.getAnswer(filePath, 0, 0, true);
		assertTrue(result == 1004774995964533l, "Actual Answer: " + result);
	}
	
}
