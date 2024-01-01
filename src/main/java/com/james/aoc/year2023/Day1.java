package com.james.aoc.year2023;

import static java.util.Map.entry; 
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.Character;

import com.james.aoc.util.FileConverter;

public class Day1 {
	
	private static Map<String, String> convertMap = Map.ofEntries(
			entry("one", "one1one"),
			entry("two", "two2two"),
			entry("three", "three3three"),
			entry("four", "four4four"),
			entry("five", "five5five"),
			entry("six", "six6six"),
			entry("seven", "seven7seven"),
			entry("eight", "eight8eight"),
			entry("nine", "nine9nine")
			);

	public static Integer getAnswer(String filename, boolean convertStrings) {
		Map<String, LinkedList<Character>> data = new HashMap<String, LinkedList<Character>>();
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		
		for (String row : rows) {
			if (convertStrings) {
				for (String mapValue : convertMap.keySet()) {
					row = row.replaceAll(mapValue, convertMap.get(mapValue));
				}
			}
			
			LinkedList<Character> integers = new LinkedList<Character>();
			for (char c : row.toCharArray()) {
				try {
					Integer value = Integer.parseInt(String.valueOf(c));
					if (value >= 0) {
						integers.add(Character.valueOf(c));
					}
				} catch (NumberFormatException e) {
					// Ok to ignore...
				}
			}
			data.put(row, integers);
		}
		
		int answer = 0;
		for (LinkedList<Character> list : data.values()) {
			if (list.size() > 0) {
				String test = list.getFirst().toString() + list.getLast();
				answer = answer + Integer.valueOf(test);
			}
		}
		
		return answer;
	}

}
