package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day17Test {
	
	@Test
	void test() {
		String filePath = "src/test/resources/Day17_2023/test.txt";
		Long result = Day17.getAnswer(filePath, false);
		assertTrue(result == 102, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day17_2023/data.txt";
		Long result = Day17.getAnswer(filePath, false);
		assertTrue(result == 785, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day17_2023/test.txt";
		Long result = Day17.getAnswer(filePath, true);
		assertTrue(result == 94, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day17_2023/data.txt";
		Long result = Day17.getAnswer(filePath, true);
		assertTrue(result == 922, "Actual Answer: " + result);
	}
	
}
