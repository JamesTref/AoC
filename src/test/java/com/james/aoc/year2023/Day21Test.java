package com.james.aoc.year2023;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Day21Test {
	/*
	@Test
	void test() {
		String filePath = "src/test/resources/Day21_2023/test.txt";
		Long result = Day21.getAnswer(filePath, 6, false);
		assertTrue(result == 16, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer() {
		String filePath = "src/test/resources/Day21_2023/data.txt";
		Long result = Day21.getAnswer(filePath, 64, false);
		assertTrue(result == 3830, "Actual Answer: " + result);
	}
	*/
	
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day21_2023/data.txt";
		Long result = Day21.getAnswer(filePath, 26501365, true);
		assertTrue(result == 637087163925555l, "Actual Answer: " + result);
	}
	
	/*
	@Test
	void findAnswerJames() {
		String filePath = "src/test/resources/Day21_2023/james.txt";
		Long result = Day21.getAnswer(filePath, 26501365, true);
		assertTrue(result == 637087163925555l, "Actual Answer: " + result);
	}*/
	
	
	
	
	
	
	
	
	
	/*
	@Test
	void findAnswer2() {
		String filePath = "src/test/resources/Day21_2023/data.txt";
		Long result = Day21_2.solve(filePath, true);
		assertTrue(result == 3830, "Actual Answer: " + result);
	}
	
	@Test
	void findAnswer3() {
		String filePath = "src/test/resources/Day21_2023/data.txt";
		Long result = Day21_2.solve(filePath, false);
		assertTrue(result == 637087163925555l, "Actual Answer: " + result);
	}*/
	
}
