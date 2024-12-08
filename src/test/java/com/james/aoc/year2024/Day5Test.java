package com.james.aoc.year2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day5Test {

	@Test
	void test() {
		String filePath = "src/test/resources/2024/Day5/test.txt";
		int result = Day5.getAnswer(filePath, false);
		assertTrue(result == 143, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/2024/Day5/data.txt";
		int result = Day5.getAnswer(filePath, false);
		assertTrue(result == 6041, "Actual Answer: " + result);
	}

	@Test
	void test2() {
		String filePath = "src/test/resources/2024/Day5/test.txt";
		int result = Day5.getAnswer(filePath, true);
		assertTrue(result == 123, "Actual Answer: " + result);
	}

	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/2024/Day5/data.txt";
		int result = Day5.getAnswer(filePath, true);
		assertTrue(result == 4884, "Actual Answer: " + result);
	}

}
