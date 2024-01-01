package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day15Test {
	
	@Test
	void test() {
		String filePath = "src/test/resources/Day15_2023/test.txt";
		Long result = Day15.getAnswer(filePath, false);
		assertTrue(result == 1320, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day15_2023/data.txt";
		Long result = Day15.getAnswer(filePath, false);
		assertTrue(result == 507291, "Actual Answer: " + result);
	}
	
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day15_2023/test.txt";
		Long result = Day15.getAnswer(filePath, true);
		assertTrue(result == 145, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day15_2023/data.txt";
		Long result = Day15.getAnswer(filePath, true);
		assertTrue(result == 296921, "Actual Answer: " + result);
	}
}
