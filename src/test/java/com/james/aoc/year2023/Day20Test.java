package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day20Test {
	
	@Test
	void test() {
		String filePath = "src/test/resources/Day20_2023/test.txt";
		Long result = Day20.getAnswer(filePath, false);
		assertTrue(result == 102, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day20_2023/data.txt";
		Long result = Day20.getAnswer(filePath, false);
		assertTrue(result == 785, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day20_2023/test.txt";
		Long result = Day20.getAnswer(filePath, true);
		assertTrue(result == 94, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day20_2023/data.txt";
		Long result = Day20.getAnswer(filePath, true);
		assertTrue(result == 922, "Actual Answer: " + result);
	}
	
}
