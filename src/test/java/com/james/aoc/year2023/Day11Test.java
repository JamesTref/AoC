package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day11Test {
	
	@Test
	void test() {
		String filePath = "src/test/resources/Day11_2023/test.txt";
		Long result = Day11.getAnswer(filePath, 2);
		assertTrue(result == 374, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day11_2023/data.txt";
		Long result = Day11.getAnswer(filePath, 2);
		assertTrue(result == 10077850, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day11_2023/test.txt";
		Long result = Day11.getAnswer(filePath, 10);
		assertTrue(result == 1030, "Actual Answer: " + result);
	}
	
	@Test
	void test3() {
		String filePath = "src/test/resources/Day11_2023/test.txt";
		Long result = Day11.getAnswer(filePath, 100);
		assertTrue(result == 8410, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day11_2023/data.txt";
		Long result = Day11.getAnswer(filePath, 1000000);
		assertTrue(result == 504715068438l, "Actual Answer: " + result);
	}
	
}
