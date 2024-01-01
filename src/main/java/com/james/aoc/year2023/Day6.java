package com.james.aoc.year2023;

import java.util.List;

import com.james.aoc.util.FileConverter;

public class Day6 {

	public static Long getAnswer(String filename, boolean part2) {
		
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		
		String[] times = new String[10];
		long times2 = 0;
		String[] distances = new String[10];
		long distances2 = 0;
		int rowCount = 1;
		for (String row : rows) {
			if (rowCount == 1) {
				String temp = row.split(":")[1].trim();
				while (temp.contains("  ")) {
					temp = temp.replace("  ", " ");
				}				
				times = temp.split(" ");
				times2 = Long.parseLong(temp.replace(" ", ""));
			} else if (rowCount == 2) {
				String temp = row.split(":")[1].trim();
				while (temp.contains("  ")) {
					temp = temp.replace("  ", " ");
				}
				distances = temp.split(" ");
				distances2 = Long.parseLong(temp.replace(" ", ""));
			} else {
				System.out.println("Too Many Rows!!!");
			}
			rowCount++;
		}
		
		//Convert to ints
		long[] timesInt = new long[times.length];
		long[] distancesInt = new long[distances.length];
		int i = 0;
		for (String s : times) {
			timesInt[i] = Long.parseLong(s);
			i++;
		}
		i = 0;
		for (String s : distances) {
			distancesInt[i] = Long.parseLong(s);
			i++;
		}
		long answer = 1;
		if (!part2) {
			i=0;
			for (long time : timesInt) {
				answer = answer * (calculateBF(time, distancesInt[i]));
				i++;
			}	
		} else {
			answer = calculateBF(times2, distances2);
		}
		return answer;
	}
	
	private static long calculateBF(long time, long distance) {
		long result = 0;
		long currentSpeed = 0;
		while (currentSpeed <= time) {
			long distanceTemp = currentSpeed * (time - currentSpeed);
			if (distanceTemp > distance) {
				result++;
			}
			currentSpeed++;
		}
		return result;
	}
}
