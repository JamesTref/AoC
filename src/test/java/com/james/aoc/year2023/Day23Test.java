package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day23Test {
	@Test
	void test() {
		String filePath = "src/test/resources/Day23_2023/test.txt";
		Long result = Day23.getAnswer(filePath, false);
		assertTrue(result == 94, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day23_2023/data.txt";
		Long result = Day23.getAnswer(filePath, false);
		assertTrue(result == 2362, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day23_2023/test.txt";
		Long result = Day23_2.getAnswer(filePath, true);
		assertTrue(result == 154, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day23_2023/data.txt";
		Long result = Day23_2.getAnswer(filePath, true);
		assertTrue(result == 6538, "Actual Answer: " + result);
	}
}
