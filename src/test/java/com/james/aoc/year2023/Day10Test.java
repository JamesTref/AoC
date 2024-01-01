package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day10Test {
	@Test
	void test() {
		String filePath = "src/test/resources/Day10_2023/test.txt";
		Integer result = Day10.getAnswer(filePath, false);
		assertTrue(result == 4, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day10_2023/data.txt";
		Integer result = Day10.getAnswer(filePath, false);
		assertTrue(result == 6870, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day10_2023/test.txt";
		Integer result = Day10.getAnswer(filePath, true);
		assertTrue(result == 1, "Actual Answer: " + result);
	}
	
	@Test
	void test3() {
		String filePath = "src/test/resources/Day10_2023/test2.txt";
		Integer result = Day10.getAnswer(filePath, true);
		assertTrue(result == 10, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day10_2023/data.txt";
		Integer result = Day10.getAnswer(filePath, true);
		assertTrue(result == 287, "Actual Answer: " + result);
	}

}
