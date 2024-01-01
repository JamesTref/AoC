package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day1Test {

	@Test
	void test() {
		String filePath = "src/test/resources/Day1_2023/test.txt";
		Integer result = Day1.getAnswer(filePath, false);
		assertTrue(result == 142, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day1_2023/data.txt";
		Integer result = Day1.getAnswer(filePath, false);
		assertTrue(result == 54708, "Actual Answer: " + result);
	}

	@Test
	void test2() {
		String filePath = "src/test/resources/Day1_2023/test2.txt";
		Integer result = Day1.getAnswer(filePath, true);
		assertTrue(result == 281, "Actual Answer: " + result);
	}

	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day1_2023/data.txt";
		Integer result = Day1.getAnswer(filePath, true);
		assertTrue(result == 54087, "Actual Answer: " + result);
	}

}
