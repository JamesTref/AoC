package com.james.aoc.year2023;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.james.aoc.util.Day19Workflow;
import com.james.aoc.util.FileConverter;

public class Day19 {

	public static Long getAnswer(String filename, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		Map<String, Map<String, List<Day19Workflow>>> workflows = new HashMap<>();
		Map<String, Integer> data = new HashMap<>();
		
		
		for (String row : rows) {
			
		}

		long answer = 0;

		return answer;
	}

}
