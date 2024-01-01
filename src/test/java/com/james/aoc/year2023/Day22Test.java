package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day22Test {
	
	@Test
	void test() {
		String filePath = "src/test/resources/Day22_2023/test.txt";
		Long result = Day22.getAnswer(filePath, false);
		assertTrue(result == 5, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day22_2023/data.txt";
		Long result = Day22.getAnswer(filePath, false);
		assertTrue(result == 398, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day22_2023/test.txt";
		Long result = Day22.getAnswer(filePath, true);
		assertTrue(result == 7, "Actual Answer: " + result);
	}

	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day22_2023/data.txt";
		Long result = Day22.getAnswer(filePath, true);
		assertTrue(result == 70727, "Actual Answer: " + result);
	}
	
}
