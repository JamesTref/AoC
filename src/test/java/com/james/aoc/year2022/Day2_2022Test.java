package com.james.aoc.year2022;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day2_2022Test {

	@Test
	void test() {
		String filePath = "src/test/resources/Day2_2022/test.txt";
		Integer result = Day2_2022.getAnswer(filePath);
		assertTrue(result == 15, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day2_2022/data.txt";
		Integer result = Day2_2022.getAnswer(filePath);
		assertTrue(result == 13005, "Actual Answer: " + result);
	}
	
	@Test
	void test2() {
		String filePath = "src/test/resources/Day2_2022/test.txt";
		Integer result = Day2_2022.getAnswer2(filePath);
		assertTrue(result == 12, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day2_2022/data.txt";
		Integer result = Day2_2022.getAnswer2(filePath);
		assertTrue(result == 11373, "Actual Answer: " + result);
	}
	

}
