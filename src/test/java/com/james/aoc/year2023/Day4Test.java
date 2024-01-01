package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day4Test {

	@Test
	void test() throws Exception {
		String filePath = "src/test/resources/Day4_2023/test.txt";
		Integer result = Day4.getAnswer(filePath, false);
		assertTrue(result == 13, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() throws Exception {
		String filePath = "src/test/resources/Day4_2023/data.txt";
		Integer result = Day4.getAnswer(filePath, false);
		assertTrue(result == 21105, "Actual Answer: " + result);
	}
	
	@Test
	void test2() throws Exception {
		String filePath = "src/test/resources/Day4_2023/test.txt";
		Integer result = Day4.getAnswer(filePath, true);
		assertTrue(result == 30, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() throws Exception {
		String filePath = "src/test/resources/Day4_2023/data.txt";
		Integer result = Day4.getAnswer(filePath, true);
		assertTrue(result == 5329815, "Actual Answer: " + result);
	}
	

}
