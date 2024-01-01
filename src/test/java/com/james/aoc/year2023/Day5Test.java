package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day5Test {

	@Test
	void test() throws Exception {
		String filePath = "src/test/resources/Day5_2023/test.txt";
		Long result = Day5.getAnswer(filePath, false);
		assertTrue(result == 35, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() throws Exception {
		String filePath = "src/test/resources/Day5_2023/data.txt";
		Long result = Day5.getAnswer(filePath, false);
		assertTrue(result == 318728750, "Actual Answer: " + result);
	}
	
	@Test
	void test2() throws Exception {
		String filePath = "src/test/resources/Day5_2023/test.txt";
		Long result = Day5.getAnswer(filePath, true);
		assertTrue(result == 46, "Actual Answer: " + result);
	}
	/*
	@Test
	void findAnswer2() throws Exception {
		String filePath = "src/test/resources/Day5_2023/data.txt";
		Long result = Day5.getAnswer(filePath, true);
		assertTrue(result == 37384986, "Actual Answer: " + result);
	}
	*/

}
