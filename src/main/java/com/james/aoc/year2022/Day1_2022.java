package com.james.aoc.year2022;

import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.james.aoc.util.FileConverter;

public class Day1_2022 {

	public static Integer getAnswer(String filename) {
		SortedMap<Integer, Integer> results = new TreeMap<>(Collections.reverseOrder());
		
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		int elfCount = 1;
		int calorieCount = 0;
		for (String row : rows) {
			if (row.isBlank()) {
				//New elf so sort pervious details
				results.put(calorieCount, elfCount);
				elfCount++;
				calorieCount = 0;
			} else {
				Integer calorie = Integer.valueOf(row);
				calorieCount = calorieCount + calorie;
			}
		}
		//Add last entry if not empty
		if (calorieCount > 0) {
			results.put(calorieCount, elfCount);
		}
		
		System.out.println(results.firstKey());
		System.out.println(results);
		int i = 0;
		int topThree = 0;
		for (int current : results.keySet()) {
			if (i > 2) {
				break;
			}
			i++;
			topThree = topThree + current;
		}
		System.out.println(topThree);
		return topThree;
	}

}
