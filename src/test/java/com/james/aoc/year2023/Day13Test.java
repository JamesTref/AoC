package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day13Test {
	
	@Test
	void test() {
		String filePath = "src/test/resources/Day13_2023/test.txt";
		Long result = Day13.getAnswer(filePath, false);
		assertTrue(result == 405, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day13_2023/data.txt";
		Long result = Day13.getAnswer(filePath, false);
		assertTrue(result == 30535, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day13_2023/test.txt";
		Long result = Day13.getAnswer(filePath, true);
		assertTrue(result == 400, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day13_2023/data.txt";
		Long result = Day13.getAnswer(filePath, true);
		assertTrue(result == 30844, "Actual Answer: " + result);
	}
	
}
