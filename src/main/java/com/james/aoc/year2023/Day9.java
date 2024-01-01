package com.james.aoc.year2023;

import java.util.ArrayList;
import java.util.List;

import com.james.aoc.util.FileConverter;

public class Day9 {

	public static Long getAnswer(String filename, boolean part2) {
		
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		
		List<List<Long>> data = new ArrayList<>();
		
		for (String row : rows) {
			String[] values = row.split(" ");
			List<Long> newRow = new ArrayList<>();
			for (String value : values) {
				newRow.add(Long.valueOf(value));
			}
			data.add(newRow);
		}
		long answer = 0;
		
		for (List<Long> line : data) {
			System.out.println("*****");
			System.out.println("Line being processed: " + line);
			long tempValue = reduce(line, part2);
			System.out.println("Value Found: " + tempValue);
			answer = answer + tempValue;
		}
		
		return answer;
	}

	private static long reduce(List<Long> line, boolean part2) {
		if (isZeros(line)) {
			return 0;
		}
		List<Long> newLine = new ArrayList<>();
		for (int i = 0; i < line.size() - 1 ; i++) {
			long value = line.get(i + 1) - line.get(i);
			newLine.add(value);
		}
		System.out.println("New Line: " + newLine);
		if (part2) {
			return line.get(0) - reduce(newLine, part2);
		} else {
			return line.get(line.size()-1) + reduce(newLine, part2);
		}
	}

	private static boolean isZeros(List<Long> list) {
		boolean allZero = true;
		for (long value : list) {
			if (value != 0) {
				allZero = false;
			}
		}
		return allZero;
    }

}
