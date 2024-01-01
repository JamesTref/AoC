package com.james.aoc.year2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.james.aoc.util.FileConverter;

public class Day14 {

	public static Long getAnswer(String filename, long cycleEndCount) {
		
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		
		List<String> combinations = new ArrayList<>();
		
		//Initialise data array
		int length = 0;
		if (rows.get(0) != null) {
			length = rows.get(0).length();
		}
		char[][] data = new char[rows.size()][length];
		
		//Populate data
		int x = 0;
		for (String row : rows) {
			int y = 0;
			for (Character value : row.toCharArray()) {
				data[x][y] = value;
				y++;
			}
			x++;
		}
		
		//System.out.println("Start: " + Arrays.deepToString(data));

		long cycleCount = 0;
		while (cycleCount < cycleEndCount) {
			for (int i = 0; i < 4; i++) {
				data = slide(data);
				data = rotateCounterClockwise(data);
			}
			//System.out.println((cycleCount+1) + ": " + Arrays.deepToString(data));
			String dataAsString = Arrays.deepToString(data);
			if (combinations.contains(dataAsString)) {
				System.out.println("*******");
				System.out.println("We got a hit!" + cycleCount);
				System.out.println("Index of hit:" + combinations.indexOf(dataAsString));
				long cycle_length = cycleCount - combinations.indexOf(dataAsString);
				System.out.println("Cycle length:" + cycle_length);
				long skipCycles = (cycleEndCount-cycleCount)/cycle_length;
				System.out.println("skipCycles:" + skipCycles);
				cycleCount = cycleCount + (skipCycles * cycle_length);
				System.out.println("new cycleCount:" + cycleCount);
				combinations.clear();
			} else {
				combinations.add(dataAsString);
			}			
			cycleCount++;
		}
		
		if (cycleEndCount == 0) {
			//We are in part 1...which slides north! part 2 ends east...30 min to see!!!
			data = slide(data);
		}
		
		long answer = evaluateLoad(data);
		
		return answer;
	}
	
	private static char[][] slide(char[][] data) {
		int rowCount = data.length;
		int columnCount = data[0].length;
		int currentColumn = 0;
		while (currentColumn < columnCount) {
			int currentRow = 0;
			while (currentRow < rowCount) {
				int currentRow2 = 0;
				while (currentRow2 < rowCount) {
					if (data[currentRow2][currentColumn] == 'O' && currentRow2 > 0 && data[currentRow2-1][currentColumn] == '.') {
						data[currentRow2][currentColumn] = '.';
						data[currentRow2 - 1][currentColumn] = 'O';
					}
					currentRow2++;
				}
				currentRow++;
			}
			currentColumn++;
		}
		return data;
	}
	
	private static char[][] rotateCounterClockwise(char[][] data) {
		int rowCount = data.length;
		int columnCount = data[0].length;
		char[][] rotatedData = new char[columnCount][rowCount];
		for (int currentRow = 0; currentRow < rowCount; currentRow++) {
		    for (int currentColumn = 0; currentColumn < columnCount; currentColumn++) {
		    	rotatedData[currentColumn][rowCount-1-currentRow] = data[currentRow][currentColumn];
		    }
		}
		return rotatedData;
	}
	
	private static long evaluateLoad(char[][] data) {
		long load = 0;
		int rowCount = data.length;
		int columnCount = data[0].length;
				  //R = len(G)
				  //C = len(G[0])
		int currentRow = 0;
		while (currentRow < rowCount) {
			int currentcolumn = 0;
			while (currentcolumn < columnCount) {
				if (data[currentRow][currentcolumn]=='O') {
					load = load + rowCount - currentRow;
				}
				currentcolumn++;
			}
			currentRow++;
		}
		return load;
	}

}
