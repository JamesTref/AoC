package com.james.aoc.year2023;

import java.util.ArrayList;
import java.util.List;

import com.james.aoc.util.FileConverter;

public class Day13 {

	public static Long getAnswer(String filename, boolean part2) {
		
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		List<boolean[][]> data = new ArrayList<>();
		List<String> currentPuzzle = new ArrayList<>();
		for (String row : rows) {
			if (row.isBlank()) {
				//New Puzzle
				if (currentPuzzle.get(0) != null) {
					data.add(createStoneMap(currentPuzzle));
				}
				currentPuzzle = new ArrayList<>();
			} else {
				currentPuzzle.add(row);
			}
		}
		//Add final map
		if (currentPuzzle.get(0) != null) {
			data.add(createStoneMap(currentPuzzle));
		}
		
		long answer = 0;
		for (boolean[][] stoneMap : data) {
			//Check Horizontal
			answer = checkForReflection(stoneMap, answer, 100, part2);
			//Transpose
			boolean[][] transposedMap = transpose(stoneMap);
			//Check Vertical
			answer = checkForReflection(transposedMap, answer, 1, part2);
		}
		
		return answer;
	}

	private static long checkForReflection(boolean[][] stoneMap, long answer, int score, boolean part2) {
		
			int rowCount = stoneMap.length;
			//check for horizontal mirror
			int reflectionRowAttempt = 0;
			while (reflectionRowAttempt < rowCount - 1) { //-1 due to last row not being a valid mirror position
				int diffCount = 0;
				int row = reflectionRowAttempt;
				int checkRow = reflectionRowAttempt + 1;
				while (row >= 0 && checkRow < rowCount) {
					diffCount = diffCount + compareArrays(stoneMap[row], stoneMap[checkRow]);
					row--;
					checkRow++;
				}
				if ((!part2 && diffCount == 0) || (part2 && diffCount == 1)) {
					//System.out.println("Reflection found (" + score + "): " + reflectionRowAttempt);
					answer = answer + (score * (reflectionRowAttempt + 1));
				}
				reflectionRowAttempt++;
			}

		return answer;
	}

	private static boolean[][] createStoneMap(List<String> currentPuzzle) {
		int length = currentPuzzle.get(0).length();
		boolean[][] stoneMap = new boolean[currentPuzzle.size()][length];
		int rowCount = 0;
		for (String puzzleRow : currentPuzzle) {
			int columnCount = 0;
			for (char c : puzzleRow.toCharArray()) {
				if (c == '#') {
					stoneMap[rowCount][columnCount] = true;
				} else {
					stoneMap[rowCount][columnCount] = false;
				}
				columnCount++;
			}
			rowCount++;
		}
		return stoneMap;
	}
	
	private static int compareArrays(boolean[] array1, boolean[] array2) {
		int differences = 0;
		if (array1.length != array2.length) {
			System.out.println("Check code...shouldn't be here");
			return 99999;
		}
		int i = 0;
		while (i < array1.length) {
			if (array1[i] != array2[i]) {
				differences++;
			}
			i++;
		}
		return differences;
	}
	
	private static boolean[][] transpose(boolean[][] array) {
	    if (array == null || array.length == 0) {
	        return array;
	    }
	    int width = array.length;
	    int height = array[0].length;

	    boolean[][] array_new = new boolean[height][width];

	    for (int x = 0; x < width; x++) {
	        for (int y = 0; y < height; y++) {
	            array_new[y][x] = array[x][y];
	        }
	    }
	    return array_new;
	}

}
