package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day3Test {

	@Test
	void test() throws Exception {
		String filePath = "src/test/resources/Day3_2023/test.txt";
		Integer result = Day3.getAnswer(filePath, false);
		assertTrue(result == 4361, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() throws Exception {
		String filePath = "src/test/resources/Day3_2023/data.txt";
		Integer result = Day3.getAnswer(filePath, false);
		assertTrue(result == 519444, "Actual Answer: " + result);
	}
	
	@Test
	void test2() throws Exception {
		String filePath = "src/test/resources/Day3_2023/test.txt";
		Integer result = Day3.getAnswer(filePath, true);
		assertTrue(result == 467835, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() throws Exception {
		String filePath = "src/test/resources/Day3_2023/data.txt";
		Integer result = Day3.getAnswer(filePath, true);
		assertTrue(result == 74528807, "Actual Answer: " + result);
	}
	

}
