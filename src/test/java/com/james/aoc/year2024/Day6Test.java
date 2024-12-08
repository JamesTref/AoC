package com.james.aoc.year2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day6Test {

	@Test
	void test() {
		String filePath = "src/test/resources/2024/Day6/test.txt";
		int result = Day6.getAnswer(filePath, false);
		assertTrue(result == 41, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/2024/Day6/data.txt";
		int result = Day6.getAnswer(filePath, false);
		assertTrue(result == 5404, "Actual Answer: " + result);
	}

	@Test
	void test2() {
		String filePath = "src/test/resources/2024/Day6/test.txt";
		int result = Day6.getAnswer(filePath, true);
		assertTrue(result == 6, "Actual Answer: " + result);
	}

	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/2024/Day6/data.txt";
		int result = Day6.getAnswer(filePath, true);
		assertTrue(result == 1984, "Actual Answer: " + result);
	}

}
