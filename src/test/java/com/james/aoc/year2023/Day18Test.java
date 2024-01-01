package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day18Test {
	
	@Test
	void test() {
		String filePath = "src/test/resources/Day18_2023/test.txt";
		Long result = Day18.getAnswer(filePath, false);
		assertTrue(result == 62, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day18_2023/data.txt";
		Long result = Day18.getAnswer(filePath, false);
		assertTrue(result == 31171, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day18_2023/test.txt";
		Long result = Day18.getAnswer(filePath, true);
		assertTrue(result == 952408144115l, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day18_2023/data.txt";
		Long result = Day18.getAnswer(filePath, true);
		assertTrue(result == 131431655002266l, "Actual Answer: " + result);
	}
	
}
