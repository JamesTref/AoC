package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day2Test {

	@Test
	void test() {
		String filePath = "src/test/resources/Day2_2023/test.txt";
		Integer result = Day2.getAnswer(filePath, false);
		assertTrue(result == 8, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day2_2023/data.txt";
		Integer result = Day2.getAnswer(filePath, false);
		assertTrue(result == 2563, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day2_2023/test.txt";
		Integer result = Day2.getAnswer(filePath, true);
		assertTrue(result == 2286, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day2_2023/data.txt";
		Integer result = Day2.getAnswer(filePath, true);
		assertTrue(result == 70768, "Actual Answer: " + result);
	}
	

}
