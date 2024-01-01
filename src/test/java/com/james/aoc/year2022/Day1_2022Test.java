package com.james.aoc.year2022;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day1_2022Test {

	@Test
	void test() {
		String filePath = "src/test/resources/Day1_2022/test.txt";
		Integer result = Day1_2022.getAnswer(filePath);
		assertTrue(result == 45000, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day1_2022/data.txt";
		Integer result = Day1_2022.getAnswer(filePath);
		assertTrue(result == 207148, "Actual Answer: " + result);
	}
	

}
