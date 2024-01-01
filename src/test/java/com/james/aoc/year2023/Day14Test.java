package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day14Test {

	@Test
	void test() {
		String filePath = "src/test/resources/Day14_2023/test.txt";
		Long result = Day14.getAnswer(filePath, 0);
		assertTrue(result == 136, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day14_2023/data.txt";
		Long result = Day14.getAnswer(filePath, 0);
		assertTrue(result == 105208, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day14_2023/test.txt";
		Long result = Day14.getAnswer(filePath, 1000000000);
		assertTrue(result == 64, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day14_2023/data.txt";
		Long result = Day14.getAnswer(filePath, 1000000000);
		assertTrue(result == 102943, "Actual Answer: " + result);
	}
	
}
