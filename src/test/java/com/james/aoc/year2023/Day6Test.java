package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day6Test {

	@Test
	void test() throws Exception {
		String filePath = "src/test/resources/Day6_2023/test.txt";
		Long result = Day6.getAnswer(filePath, false);
		assertTrue(result == 288, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() throws Exception {
		String filePath = "src/test/resources/Day6_2023/data.txt";
		Long result = Day6.getAnswer(filePath, false);
		assertTrue(result == 252000, "Actual Answer: " + result);
	}
	
	@Test
	void test2() throws Exception {
		String filePath = "src/test/resources/Day6_2023/test.txt";
		Long result = Day6.getAnswer(filePath, true);
		assertTrue(result == 71503, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() throws Exception {
		String filePath = "src/test/resources/Day6_2023/data.txt";
		Long result = Day6.getAnswer(filePath, true);
		assertTrue(result == 36992486, "Actual Answer: " + result);
	}
	
}
