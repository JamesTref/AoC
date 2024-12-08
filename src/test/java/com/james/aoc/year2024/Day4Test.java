package com.james.aoc.year2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day4Test {

	@Test
	void test() {
		String filePath = "src/test/resources/2024/Day4/test.txt";
		long result = Day4.getAnswer(filePath, false);
		assertTrue(result == 18, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/2024/Day4/data.txt";
		long result = Day4.getAnswer(filePath, false);
		assertTrue(result == 2593, "Actual Answer: " + result);
	}


	@Test
	void test2() {
		String filePath = "src/test/resources/2024/Day4/test.txt";
		long result = Day4.getAnswer(filePath, true);
		assertTrue(result == 9, "Actual Answer: " + result);
	}

	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/2024/Day4/data.txt";
		long result = Day4.getAnswer(filePath, true);
		assertTrue(result == 1950, "Actual Answer: " + result);
	}
	
}
