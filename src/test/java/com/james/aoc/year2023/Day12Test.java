package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day12Test {
	
	@Test
	void test() {
		String filePath = "src/test/resources/Day12_2023/test.txt";
		Long result = Day12.getAnswer(filePath, false);
		assertTrue(result == 21, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day12_2023/data.txt";
		Long result = Day12.getAnswer(filePath, false);
		assertTrue(result == 7017, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day12_2023/test.txt";
		Long result = Day12.getAnswer(filePath, true);
		assertTrue(result == 525152, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day12_2023/data.txt";
		Long result = Day12.getAnswer(filePath, true);
		assertTrue(result == 527570479489l, "Actual Answer: " + result);
	}
	
}
