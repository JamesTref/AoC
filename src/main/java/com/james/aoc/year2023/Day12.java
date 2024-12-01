package com.james.aoc.year2023;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.james.aoc.util.Day12Key;
import com.james.aoc.util.FileConverter;

public class Day12 {

	public static Map<Day12Key, Long> cache = new HashMap<>();
	
	public static Long getAnswer(String filename, boolean part2) {
		
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		
		long answer = 0;
		for (String row : rows) {
			String springs = row.split(" ")[0];
			String[] pattern = row.split(" ")[1].split(",");
			if (part2) {
				int i = 0;
				String newSprings = springs;
				String newPattern = row.split(" ")[1];
				while(i<4) {
					newSprings = newSprings + "?" + springs;
					newPattern = newPattern + "," + row.split(" ")[1];
					i++;
				}
				springs = newSprings;
				pattern = newPattern.split(",");
			}
			cache = new HashMap<>();
			System.out.println("----------");
			System.out.println("Spring: " + springs);
			System.out.println("----------");
			answer = answer + findCombinations(springs, pattern, 0, 0, 0);
			System.out.println(answer);
		}

		return answer;
	}
	
	private static long findCombinations(String springs, String[] pattern, int springPosition, int patternPosition, int currentBlockLength) {
		System.out.println("Calling: " + springs + ", " + Arrays.toString(pattern) + ", "  + springPosition + ", "  + patternPosition + ", " + currentBlockLength);
		Day12Key key = new Day12Key(springPosition, patternPosition, currentBlockLength);
		if (cache.containsKey(key)) {
			System.out.println("Hit Cache");
			return cache.get(key);
		}
		//At the end..
		if (springPosition == springs.length()) {
			if ((patternPosition == pattern.length) && (currentBlockLength == 0)) {
				System.out.println("End with Pattern1");
				return 1;
			} else if ((patternPosition == pattern.length - 1) && (Integer.valueOf(pattern[patternPosition]) == currentBlockLength)) {
				System.out.println("End with Pattern2");
				return 1;
			} else {
				System.out.println("End NO Pattern");
				return 0;
			}
		}
		
		char[] charToCheck = {'.', '#'};
		long combinations = 0;
		for (char c: charToCheck) {
			char currentChar = springs.charAt(springPosition);
			System.out.println("Char: " + c);
			System.out.println("Checking Char: " + currentChar);
			if (currentChar == c || currentChar == '?') {
				if (c == '.' && currentBlockLength == 0) {
					System.out.println("A");
					combinations = combinations + findCombinations(springs, pattern, springPosition + 1, patternPosition, 0);
				} else if (c == '.' && currentBlockLength > 0 && pattern.length > patternPosition && Integer.valueOf(pattern[patternPosition]) == currentBlockLength) {
					System.out.println("B");
					combinations = combinations + findCombinations(springs, pattern, springPosition + 1, patternPosition + 1, 0);
				} else if (c == '#' ) {
					System.out.println("C");
					combinations = combinations + findCombinations(springs, pattern, springPosition + 1, patternPosition, currentBlockLength + 1);
				} else {
					System.out.println("Failed Combo");
				}
			}
		}
		System.out.println("Adding to Cache..." + key + " " + combinations);
		cache.put(key, combinations);
		return combinations;
	}

}
