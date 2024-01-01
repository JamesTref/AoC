package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day19Test {
	
	@Test
	void test() {
		String filePath = "src/test/resources/Day19_2023/test.txt";
		Long result = Day19.getAnswer(filePath, false);
		assertTrue(result == 19114, "Actual Answer: " + result);
	}

	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day19_2023/data.txt";
		Long result = Day19.getAnswer(filePath, false);
		assertTrue(result == 489392, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day19_2023/test.txt";
		Long result = Day19.getAnswer(filePath, true);
		assertTrue(result == 167409079868000l, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day19_2023/data.txt";
		Long result = Day19.getAnswer(filePath, true);
		assertTrue(result == 134370637448305l, "Actual Answer: " + result);
	}
	
}
