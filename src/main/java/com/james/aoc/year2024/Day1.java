package com.james.aoc.year2024;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.james.aoc.util.FileConverter;

public class Day1 {

	public static Integer getAnswer(String filename, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		
		List<Integer> leftList = new ArrayList<Integer>();
		List<Integer> rightList = new ArrayList<Integer>();
		Map<Integer, Integer> rightMap = new HashMap<Integer, Integer>();
		
		for (String row : rows) {
			String[] splitRow = row.split("   ");
			leftList.add(Integer.valueOf(splitRow[0]));
			Integer rightValue = Integer.valueOf(splitRow[1]);
			rightList.add(rightValue);
			if (rightMap.containsKey(rightValue)) {
				Integer count = rightMap.get(rightValue) + 1;
				rightMap.put(rightValue, count);
			} else {
				rightMap.put(rightValue, 1);
			}
			
		}
		
		int answer = 0;
		
		if (!part2) {
			Collections.sort(leftList);
			Collections.sort(rightList);
			int i = 0;
			while (i < leftList.size()) {
				answer = answer + Math.abs(leftList.get(i) - rightList.get(i));
				i++;
			}
		} else {
			int i = 0;
			while (i < leftList.size()) {
				Integer value = leftList.get(i);
				Integer count = 0;
				if (rightMap.containsKey(value)) {
					count = rightMap.get(value);
				}
				answer = answer + (value * count);
				i++;
			}
		}
		
		return answer;
	}

}
