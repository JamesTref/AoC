package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day25Test {
	
	@Test
	void test() {
		String filePath = "src/test/resources/Day25_2023/test.txt";
		Long result = null;
		try {
			result = Day25.getAnswer(filePath, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(result == 54, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day25_2023/data.txt";
		Long result = null;
		try {
			result = Day25.getAnswer(filePath, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(result == 533628, "Actual Answer: " + result);
	}
	/*
	@Test
	void test2() {
		String filePath = "src/test/resources/Day25_2023/test.txt";
		Long result = Day25.getAnswer(filePath, true);
		assertTrue(result == 94, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day25_2023/data.txt";
		Long result = Day25.getAnswer(filePath, true);
		assertTrue(result == 922, "Actual Answer: " + result);
	}
	*/
}
