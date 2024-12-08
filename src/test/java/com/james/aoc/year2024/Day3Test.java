package com.james.aoc.year2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day3Test {

	@Test
	void test() {
		String filePath = "src/test/resources/2024/Day3/test.txt";
		long result = Day3.getAnswer(filePath, false);
		assertTrue(result == 161l, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/2024/Day3/data.txt";
		long result = Day3.getAnswer(filePath, false);
		assertTrue(result == 156388521l, "Actual Answer: " + result);
	}


	@Test
	void test2() {
		String filePath = "src/test/resources/2024/Day3/test2.txt";
		long result = Day3.getAnswer(filePath, true);
		assertTrue(result == 48l, "Actual Answer: " + result);
	}

	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/2024/Day3/data2.txt";
		long result = Day3.getAnswer(filePath, true);
		assertTrue(result == 75920122l, "Actual Answer: " + result);
	}

}
