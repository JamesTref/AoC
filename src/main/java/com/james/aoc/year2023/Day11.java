package com.james.aoc.year2023;

import java.util.ArrayList;
import java.util.List;

import com.james.aoc.util.FileConverter;
import com.james.aoc.util.Location;

public class Day11 {

	public static Long getAnswer(String filename, long expandSize) {
		
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		//Stores if the tile is in the loop
		int length = 0;
		if (rows.get(0) != null) {
			length = rows.get(0).length();
		}
		boolean[][] starMap = new boolean[rows.size()][length];
		
		//int emptyRowCount = 0;
		//int emptyColumnCount = 0;
		//Here True means Empty!!
		boolean[] emptyRows = new boolean[length];
		//Here True means NOT Empty!!
		boolean[] emptyColumns = new boolean[length];
		
		int x = 0;
		for (String row : rows) {
			int y = 0;
			if (row.indexOf('#') < 0) {
				//emptyRowCount++;
				emptyRows[x] = true;
			}
			for (Character value : row.toCharArray()) {
				if (value == '#') {
					starMap[x][y] = true;
					if (!emptyColumns[y]) {
						emptyColumns[y] = true;
					}
				} else {
					starMap[x][y] = false;
				}
				y++;
			}
			x++;
		}
		
		//for (boolean value : emptyColumns) {
		//	if (!value) {
				//emptyColumnCount++;
		//	}
		//}
		
		//Expand
		/*boolean[][] starMapExpanded = new boolean[rows.size() + emptyRowCount][length + emptyColumnCount];
		int currentRow = 0;
		int addedRows = 0;
		while (currentRow < rows.size()) {
			boolean[] row = starMap[currentRow];
			boolean[] expandedRow = new boolean[length + emptyColumnCount];
			int currentColumn = 0;
			int addedColumns = 0;
			while (currentColumn < length) {
				if (row[currentColumn]) {
					expandedRow[currentColumn + addedColumns] = true;
				} else if (!emptyColumns[currentColumn]) {
					addedColumns++;
					expandedRow[currentColumn + addedColumns] = false;
				}
				currentColumn++;
			}
			
			starMapExpanded[currentRow + addedRows] = expandedRow;
			if (emptyRows[currentRow]) {
				addedRows++;
				starMapExpanded[currentRow + addedRows] = expandedRow;
			}
			currentRow++;
		}*/	
		
		//Store Locations
		List<Location> locations = new ArrayList<>();
		int currentRow = 0;
		while (currentRow < rows.size()) { //while (currentRow < rows.size() + emptyRowCount) {
			int currentColumn = 0;
			while (currentColumn < length) { //while (currentColumn < length  + emptyColumnCount) {
				if (starMap[currentRow][currentColumn]) {
					locations.add(new Location(currentRow, currentColumn));
				}
				currentColumn++;
			}
			currentRow++;
		}
		
		long minDistanceTotal = 0;
		int currentLocation = 0;
		for (Location current : locations) {
			int nextLocationCount = 1;
			while (currentLocation + nextLocationCount < locations.size()) {
				Location nextLocation = locations.get(currentLocation + nextLocationCount);
				
				int startRow = current.getRow();
				int endRow = nextLocation.getRow();
				int minRow = Math.min(startRow, endRow);
				int maxRow = Math.max(startRow, endRow);
				//Now check for empty rows between
				int countEmptyRows = 0;
				minRow++;
				while (minRow < maxRow) {
					if (emptyRows[minRow]) {
						countEmptyRows++;
					}
					minRow++;
				}
				
				int startCol = current.getColumn();
				int endCol = nextLocation.getColumn();
				int minCol = Math.min(startCol, endCol);
				int maxCol = Math.max(startCol, endCol);
				//Now check for empty rows between
				int countEmptyCols = 0;
				minCol++;
				while (minCol < maxCol) {
					if (!emptyColumns[minCol]) {
						countEmptyCols++;
					}
					minCol++;
				}
				
				int xDistance = Math.abs(nextLocation.getColumn() - current.getColumn());
				int yDistance = Math.abs(nextLocation.getRow() - current.getRow());
				long mindistance = xDistance + yDistance + (countEmptyRows*(expandSize - 1)) + (countEmptyCols*(expandSize-1));
				minDistanceTotal = minDistanceTotal + mindistance;
				System.out.println(currentLocation + ": " + current + " to " + nextLocation + " : " + xDistance + "," + yDistance + "," + (countEmptyRows*expandSize) + "," + (countEmptyCols*expandSize) + " : " + mindistance + " : " + minDistanceTotal);
				nextLocationCount++;
			}
			currentLocation++;
		}
		
		long answer = minDistanceTotal;
		
		return answer;
	}

}
