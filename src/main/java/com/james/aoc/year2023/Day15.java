package com.james.aoc.year2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.james.aoc.util.Day12Key;
import com.james.aoc.util.FileConverter;

public class Day15 {

	public static Map<Day12Key, Long> cache = new HashMap<>();

	public static Long getAnswer(String filename, boolean part2) {

		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		String data = "";
		for (String row : rows) {
			data = row;
		}

		String[] commands = data.split(",");
		long answer = 0;
		if (!part2) {
			for (String command : commands) {
				answer = answer + calculateHash(command);
			}
		} else {
			// List of boxes index is box number
			// Map key is string value e.g. rn
			// Map value is the long value;
			List<Map<String, Integer>> boxes = new ArrayList<>();
			int i = 0; 
			while (i < 256) {
				boxes.add(i ,null);
				i++;
			}
			for (String command : commands) {
				int length = command.length();
				if (command.toCharArray()[length - 1] == '-') {
					String value = command.substring(0, length - 1);
					int hashValue = calculateHash(value);
					if (boxes.get(hashValue) != null) {
						Map<String, Integer> map = boxes.get(hashValue);
						map.remove(value);
					}
				} else if (command.toCharArray()[length - 2] == '=') {
					String value = command.substring(0, length - 2);
					int intValue = Integer.parseInt(String.valueOf(command.toCharArray()[length-1]));
					int hashValue = calculateHash(value);
					if (boxes.get(hashValue) != null) {
						Map<String, Integer> boxContents = boxes.get(hashValue);
						if (boxContents.containsKey(value)) {
							if (boxContents.get(value) != null) {
								boxContents.replace(value, intValue);
							} else {
								boxContents.put(value, intValue);
							}
						} else {
							boxContents.put(value, intValue);
						}

					} else {
						Map<String, Integer> boxContents = new LinkedHashMap<>();
						boxContents.put(value, intValue);
						boxes.remove(hashValue);
						boxes.add(hashValue, boxContents);
					}
				}
			}

			int boxNo = 0;
			while (boxNo < 256) {
				if (boxes.get(boxNo) != null) {
					Map<String, Integer> currentBox = boxes.get(boxNo);
					int slot = 1;
					for (String value : currentBox.keySet()) {
						int focus = currentBox.get(value);
						int power = (boxNo+1) * slot * focus;
						answer = answer + power;
						System.out.println(answer);
						slot++;
					}
				}
				boxNo++;
			}
			
		}
		return answer;
	}

	private static int calculateHash(String value) {
		int hashValue = 0;
		for (Character c : value.toCharArray()) {
			int asciiCode = c;
			/*
			 * System.out.println("a: " + asciiCode); long step1 = hashValue + asciiCode;
			 * System.out.println("1: " + step1); long step2 = step1 * 17;
			 * System.out.println("2: " + step2); long step3 = step2 % 256;
			 * System.out.println("3: " + step3);
			 */
			hashValue = ((hashValue + asciiCode) * 17) % 256;
		}
		return hashValue;
	}

}
