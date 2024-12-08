package com.james.aoc.year2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day7Test {

	@Test
	void test() {
		String filePath = "src/test/resources/2024/Day7/test.txt";
		long result = Day7.getAnswer(filePath, false);
		assertTrue(result == 3749l, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/2024/Day7/data.txt";
		long result = Day7.getAnswer(filePath, false);
		assertTrue(result == 3351424677624l, "Actual Answer: " + result);
	}

	@Test
	void test2() {
		String filePath = "src/test/resources/2024/Day7/test.txt";
		long result = Day7.getAnswer(filePath, true);
		assertTrue(result == 11387l, "Actual Answer: " + result);
	}

	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/2024/Day7/data.txt";
		long result = Day7.getAnswer(filePath, true);
		assertTrue(result == 204976636995111l, "Actual Answer: " + result);
	}

}
