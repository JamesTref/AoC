package com.james.aoc.year2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day2Test {

	@Test
	void test() {
		String filePath = "src/test/resources/Day2_2024/test.txt";
		Integer result = Day2.getAnswer(filePath, false);
		assertTrue(result == 2, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day2_2024/data.txt";
		Integer result = Day2.getAnswer(filePath, false);
		assertTrue(result == 598, "Actual Answer: " + result);
	}

	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day2_2024/test2.txt";
		Integer result = Day2.getAnswer(filePath, true);
		assertTrue(result == 4, "Actual Answer: " + result);
	}

	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day2_2024/data2.txt";
		Integer result = Day2.getAnswer(filePath, true);
		assertTrue(result == 634, "Actual Answer: " + result);
	}

}
