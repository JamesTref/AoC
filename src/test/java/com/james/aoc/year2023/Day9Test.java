package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day9Test {

	@Test
	void test() {
		String filePath = "src/test/resources/Day9_2023/test.txt";
		Long result = Day9.getAnswer(filePath, false);
		assertTrue(result == 114, "Actual Answer: " + result);
	}
	
	
	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day9_2023/data.txt";
		Long result = Day9.getAnswer(filePath, false);
		assertTrue(result == 1939607039, "Actual Answer: " + result);
	}
	
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day9_2023/test.txt";
		Long result = Day9.getAnswer(filePath, true);
		assertTrue(result == 2, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day9_2023/data.txt";
		Long result = Day9.getAnswer(filePath, true);
		assertTrue(result == 1041, "Actual Answer: " + result);
	}
	

}
