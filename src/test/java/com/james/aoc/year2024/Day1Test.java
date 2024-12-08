package com.james.aoc.year2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day1Test {

	@Test
	void test() {
		String filePath = "src/test/resources/2024/Day1/test.txt";
		Integer result = Day1.getAnswer(filePath, false);
		assertTrue(result == 11, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/2024/Day1/data.txt";
		Integer result = Day1.getAnswer(filePath, false);
		assertTrue(result == 2086478, "Actual Answer: " + result);
	}

	
	@Test
	void test2() {
		String filePath = "src/test/resources/2024/Day1/test2.txt";
		Integer result = Day1.getAnswer(filePath, true);
		assertTrue(result == 31, "Actual Answer: " + result);
	}

	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/2024/Day1/data2.txt";
		Integer result = Day1.getAnswer(filePath, true);
		assertTrue(result == 24941624, "Actual Answer: " + result);
	}

}
