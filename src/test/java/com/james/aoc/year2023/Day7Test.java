package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day7Test {

	@Test
	void test() throws Exception {
		String filePath = "src/test/resources/Day7_2023/test.txt";
		Integer result = Day7.getAnswer(filePath, false);
		assertTrue(result == 6440, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() throws Exception {
		String filePath = "src/test/resources/Day7_2023/data.txt";
		Integer result = Day7.getAnswer(filePath, false);
		assertTrue(result == 247815719, "Actual Answer: " + result);
	}
	
	@Test
	void test2() throws Exception {
		String filePath = "src/test/resources/Day7_2023/test.txt";
		Integer result = Day7.getAnswer(filePath, true);
		assertTrue(result == 5905, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() throws Exception {
		String filePath = "src/test/resources/Day7_2023/data.txt";
		Integer result = Day7.getAnswer(filePath, true);
		assertTrue(result == 248747492, "Actual Answer: " + result);
	}
	
}
