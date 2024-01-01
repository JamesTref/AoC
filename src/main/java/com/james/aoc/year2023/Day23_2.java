package com.james.aoc.year2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.james.aoc.util.Day23NodeLocation;
import com.james.aoc.util.DirectionEnum;
import com.james.aoc.util.FileConverter;
import com.james.aoc.util.Location;

public class Day23_2 {

	public static Long getAnswer(String filename, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		int length = 0;
		if (rows.get(0) != null) {
			length = rows.get(0).length();
		}
		char[][] mazeMap = new char[rows.size()][length];
		int rowCount = 0;
		for (String row : rows) {
			int columnCount = 0;
			if (part2) {
				row = row.replace('^', '.');
				row = row.replace('>', '.');
				row = row.replace('v', '.');
				row = row.replace('<', '.');
			}
			for (Character value : row.toCharArray()) {
				mazeMap[rowCount][columnCount] = value;
				columnCount++;
			}
			rowCount++;
		}
		// Assumption always bottom right (-1)
		Location start = new Location(0, 1);
		Location end = new Location(rows.size() - 1, length - 2);
		List<Location> keyLocations = new ArrayList<>();
		keyLocations.add(start);
		keyLocations.add(end);
		
		//Find other key locations
		int rowNum = 0;
		while (rowNum < rows.size()) {
			char[] currentRow = mazeMap[rowNum];
			int colNum = 0;
			while (colNum < length) {
				char currentChar = currentRow[colNum];
				if (currentChar == '.') {
					int countPaths = 0;
					//check above
					if ((rowNum-1 >= 0) && (mazeMap[rowNum-1][colNum] == '.')) {
						countPaths++;
					}
					//check right
					if ((colNum+1 < length) && (mazeMap[rowNum][colNum+1] == '.')) {
						countPaths++;
					}
					//check down
					if ((rowNum+1 < rows.size()) && (mazeMap[rowNum+1][colNum] == '.')) {
						countPaths++;
					}
					//check left
					if ((colNum-1 >= 0) && (mazeMap[rowNum][colNum-1] == '.')) {
						countPaths++;
					}
					if (countPaths>2) {
						keyLocations.add(new Location(rowNum, colNum));
					}
					
				}
				colNum++;
			}
			rowNum++;
		}
		System.out.println(keyLocations);
		
		Map<Location, Map<Location, Integer>> keyPointDistances = new HashMap<>();
		//Build new map
		for (Location keyLocation : keyLocations) {
			
			Map<Location, Integer> nodeMap = new HashMap<>();
			
			//repeat
			int row = keyLocation.getRow();
			int column = keyLocation.getColumn();
			//Find neighbours
			char north = 'X';
			char east = 'X';
			char south = 'X';
			char west = 'X';
			
			try {
				north = mazeMap[row-1][column];
			} catch (ArrayIndexOutOfBoundsException e) {
				//ignore, leave as 'X'
			}
			try {
				east = mazeMap[row][column+1];
			} catch (ArrayIndexOutOfBoundsException e) {
				//ignore, leave as 'X'
			}
			try {
				south = mazeMap[row+1][column];
			} catch (ArrayIndexOutOfBoundsException e) {
				//ignore, leave as 'X'
			}
			try {
				west = mazeMap[row][column-1];
			} catch (ArrayIndexOutOfBoundsException e) {
				//ignore, leave as 'X'
			}
			
			if (north == '.') {
				nodeMap.putAll(takeAStep(new Location(row-1, column), mazeMap, DirectionEnum.NORTH, keyLocations, 0));
			}
			if (east == '.') {
				nodeMap.putAll(takeAStep(new Location(row, column+1), mazeMap, DirectionEnum.EAST, keyLocations, 0));
			}
			if (south == '.') {
				nodeMap.putAll(takeAStep(new Location(row+1, column), mazeMap, DirectionEnum.SOUTH, keyLocations, 0));
			}
			if (west == '.') {
				nodeMap.putAll(takeAStep(new Location(row, column-1), mazeMap, DirectionEnum.WEST, keyLocations, 0));
			}
			//end repeat

			keyPointDistances.put(keyLocation, nodeMap);
		}
		
		//Now brute force routes keeping highest...
		List<Day23NodeLocation> completeLocations = new ArrayList<>();
		List<Day23NodeLocation> currentLocations = new ArrayList<>();
		List<Location> visited = new ArrayList<>();
		visited.add(start);
		Day23NodeLocation startNode = new Day23NodeLocation(start, 0, visited);
		currentLocations.add(startNode);
		int i = 1;
		while (currentLocations.size() > 0) {
			List<Day23NodeLocation> newLocations = new ArrayList<>();
			
			for (Day23NodeLocation nextLocation : currentLocations) {
				newLocations.addAll(takeAStep(nextLocation, keyPointDistances));
			}
			currentLocations = newLocations;
			
			for (Day23NodeLocation location : newLocations) {
				if (location.getLocation().equals(end)) {
					completeLocations.add(location);
				}
			}
			i++;
			System.out.println(i + ": Path Count: " + currentLocations.size());
		}
		
		long answer = 0;
		for (Day23NodeLocation currentLocation : completeLocations) {
			answer = Math.max(answer, currentLocation.getDistance());
		}

		return answer;
	}
	
	private static List<Day23NodeLocation> takeAStep(Day23NodeLocation currentLocation, Map<Location, Map<Location, Integer>> keyPointDistances) {
		List<Day23NodeLocation> newLocations = new ArrayList<>();
		
		int currentDistance = currentLocation.getDistance();
		Map<Location, Integer> possibleMoves = keyPointDistances.get(currentLocation.getLocation());
		
		for (Location newLocation : possibleMoves.keySet()) {
			List<Location> visitedLocations = new ArrayList<>();
			visitedLocations.addAll(currentLocation.getVisited());
			if (!visitedLocations.contains(newLocation)) {
				int newDistance = currentDistance + possibleMoves.get(newLocation);
				visitedLocations.add(newLocation);
				Day23NodeLocation nextStep = new Day23NodeLocation(newLocation, newDistance, visitedLocations);
				newLocations.add(nextStep);
			}
		}
	
		return newLocations;
	}
	
	private static Map<Location, Integer> takeAStep(Location newLocation, char[][] mazeMap, DirectionEnum moveDirection, List<Location> keyLocations, int steps) {
		steps++;
		if (keyLocations.contains(newLocation)) {
			Map<Location, Integer> locationLength = new HashMap<>();
			locationLength.put(newLocation, steps);
			return locationLength;
		}
		
		int row = newLocation.getRow();
		int column = newLocation.getColumn();
		
		//Find neighbours
		char north = 'X';
		char east = 'X';
		char south = 'X';
		char west = 'X';
		
		try {
			north = mazeMap[row-1][column];
		} catch (ArrayIndexOutOfBoundsException e) {
			//ignore, leave as 'X'
		}
		try {
			east = mazeMap[row][column+1];
		} catch (ArrayIndexOutOfBoundsException e) {
			//ignore, leave as 'X'
		}
		try {
			south = mazeMap[row+1][column];
		} catch (ArrayIndexOutOfBoundsException e) {
			//ignore, leave as 'X'
		}
		try {
			west = mazeMap[row][column-1];
		} catch (ArrayIndexOutOfBoundsException e) {
			//ignore, leave as 'X'
		}
		
		if (north == '.' && moveDirection != DirectionEnum.SOUTH) {
			return takeAStep(new Location(row-1, column), mazeMap, DirectionEnum.NORTH, keyLocations, steps);
		}
		if (east == '.'  && moveDirection != DirectionEnum.WEST) {
			return takeAStep(new Location(row, column+1), mazeMap, DirectionEnum.EAST, keyLocations, steps);
		}
		if (south == '.'  && moveDirection != DirectionEnum.NORTH) {
			return takeAStep(new Location(row+1, column), mazeMap, DirectionEnum.SOUTH, keyLocations, steps);
		}
		if (west == '.'  && moveDirection != DirectionEnum.EAST) {
			return takeAStep(new Location(row, column-1), mazeMap, DirectionEnum.WEST, keyLocations, steps);
		}
		
		//shouldn't be here...so for now want a Nullpointer to highlight the issue....
		return null;
	}

}
