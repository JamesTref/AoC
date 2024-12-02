package com.james.aoc.year2024;

import java.util.List;

import com.james.aoc.util.FileConverter;

public class Day2 {

	public static Integer getAnswer(String filename, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		
		int answer = 0;
		
		for (String row : rows) {
			String[] splitRow = row.split(" ");
			boolean valid = validateRow(splitRow);
			if (part2 && !valid) {
				int j = 0;
				while (j < splitRow.length && !valid) {
					String[] newSplitRow = remove(splitRow,j);
					valid = validateRow(newSplitRow);		
					j++;
				}
			}
			if (valid) {
				answer++;
			}
		}
		
		return answer;
	}
	
	private static String[] remove(String[] arr, int in) {
        if (arr == null || in < 0 || in >= arr.length) {
            return arr;
        }
        String[] arr2 = new String[arr.length - 1];
        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == in)
                continue;
            
              arr2[k++] = arr[i];
        }
        return arr2;
    }

	private static boolean validateRow(String[] splitRow) {
		Integer previousInt = 0;
		boolean valid = true;
		boolean increase = false;
		int i =0;
		for (String item : splitRow) {
			Integer currentInt = Integer.parseInt(item);
			if (i==0) {
				//Do nothing
			} else if (i==1) {
				//Find increase or decrease
				if (currentInt.intValue() == previousInt.intValue()) {
					valid = false;
				} else if (currentInt.intValue() > previousInt.intValue()) {
					increase = true;
				}
				
				int diff = Math.abs(currentInt.intValue() - previousInt.intValue());
				if (diff < 0 || diff > 3) {
					valid = false;
				}
			} else {
				boolean currentIncrease = false;
				if (currentInt.intValue() == previousInt.intValue()) {
					valid = false;
				} else if (currentInt.intValue() > previousInt.intValue()) {
					currentIncrease = true;
				}
				if (increase != currentIncrease) {
					valid = false;
				}
				int diff = Math.abs(currentInt.intValue() - previousInt.intValue());
				if (diff < 0 || diff > 3) {
					valid = false;
				}
			}
			previousInt = currentInt;
			i++;
		}
		return valid;
	}

}
