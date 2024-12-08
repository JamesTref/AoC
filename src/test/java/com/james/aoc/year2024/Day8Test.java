package com.james.aoc.year2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day8Test {

	@Test
	void test() {
		String filePath = "src/test/resources/2024/Day8/test.txt";
		int result = Day8.getAnswer(filePath, false);
		assertTrue(result == 14, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/2024/Day8/data.txt";
		int result = Day8.getAnswer(filePath, false);
		assertTrue(result == 348, "Actual Answer: " + result);
	}

	@Test
	void test2() {
		String filePath = "src/test/resources/2024/Day8/test.txt";
		int result = Day8.getAnswer(filePath, true);
		assertTrue(result == 34, "Actual Answer: " + result);
	}

	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/2024/Day8/data.txt";
		int result = Day8.getAnswer(filePath, true);
		assertTrue(result == 1221, "Actual Answer: " + result);
	}

}
