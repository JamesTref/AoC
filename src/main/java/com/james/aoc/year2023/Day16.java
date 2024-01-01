package com.james.aoc.year2023;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.james.aoc.util.DirectionEnum;
import com.james.aoc.util.FileConverter;

public class Day16 {

	public static Map<String, Map<DirectionEnum, Integer>> cache = new HashMap<>();

	public static Long getAnswer(String filename, boolean part2) {
		cache = new HashMap<>();
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		int length = 0;
		if (rows.get(0) != null) {
			length = rows.get(0).length();
		}
		char[][] mirrorMap = new char[rows.size()][length];
		int[][] scoreMap = new int[rows.size()][length];

		int rowCount = 0;
		for (String row : rows) {
			int columnCount = 0;
			for (Character value : row.toCharArray()) {
				mirrorMap[rowCount][columnCount] = value;
				columnCount++;
			}
			rowCount++;
		}
		long answer = 0;
		if (!part2) {
			walk(mirrorMap, scoreMap, 0, 0, DirectionEnum.EAST, rows.size(), length);
			answer = score(scoreMap, rows.size(), length);
		} else {
			int row = 0;
			while (row < rows.size()) {
				//reset
				scoreMap = new int[rows.size()][length];
				cache = new HashMap<>();
				walk(mirrorMap, scoreMap, row, 0, DirectionEnum.EAST, rows.size(), length);
				answer = Math.max(answer, score(scoreMap, rows.size(), length));
				//reset
				scoreMap = new int[rows.size()][length];
				cache = new HashMap<>();
				walk(mirrorMap, scoreMap, row, length-1, DirectionEnum.WEST, rows.size(), length);
				answer = Math.max(answer, score(scoreMap, rows.size(), length));
				row++;
			}
			int column = 0;
			while (column < length) {
				//reset
				scoreMap = new int[rows.size()][length];
				cache = new HashMap<>();
				walk(mirrorMap, scoreMap, 0, column, DirectionEnum.SOUTH, rows.size(), length);
				answer = Math.max(answer, score(scoreMap, rows.size(), length));
				//reset
				scoreMap = new int[rows.size()][length];
				cache = new HashMap<>();
				walk(mirrorMap, scoreMap, rows.size()-1, column, DirectionEnum.NORTH, rows.size(), length);
				answer = Math.max(answer, score(scoreMap, rows.size(), length));
				column++;
			}
		}

		return answer;
	}

	private static long score(int[][] scoreMap, int countRows, int countColumns) {
		long tilesHit = 0;
		int row = 0;
		while (row < countRows) {
			int column = 0;
			while (column < countColumns) {
				if (scoreMap[row][column] != 0) {
					tilesHit++;
				}
				column++;
			}
			row++;
		}
		return tilesHit;
	}

	private static void walk(char[][] mirrorMap, int[][] scoreMap, int row, int column, DirectionEnum direction,
			int countRows, int countColumns) {
		if (row < 0 || row > countRows - 1 || column < 0 || column > countColumns - 1) {
			// Outside the map
			return;
		}

		String position = "(" + row + "," + column + ")";
		// have we been here and travelling in same direction before..
		if (cache.containsKey(position)) {
			Map<DirectionEnum, Integer> currentMap = cache.get(position);
			if (currentMap.containsKey(direction)) {
				return;
			} else {
				currentMap.put(direction, 1);
			}
		} else {
			Map<DirectionEnum, Integer> newMap = new HashMap<>();
			newMap.put(direction, 1);
			cache.put(position, newMap);
		}

		char currentChar = mirrorMap[row][column];
		scoreMap[row][column] = scoreMap[row][column] + 1;
		// Mark hit
		switch (direction) {
		case EAST:
			switch (currentChar) {
			case '.':
				walk(mirrorMap, scoreMap, row, column + 1, DirectionEnum.EAST, countRows, countColumns);
				break;
			case '-':
				walk(mirrorMap, scoreMap, row, column + 1, DirectionEnum.EAST, countRows, countColumns);
				break;
			case '|':
				walk(mirrorMap, scoreMap, row - 1, column, DirectionEnum.NORTH, countRows, countColumns);
				walk(mirrorMap, scoreMap, row + 1, column, DirectionEnum.SOUTH, countRows, countColumns);
				break;
			case '/':
				walk(mirrorMap, scoreMap, row - 1, column, DirectionEnum.NORTH, countRows, countColumns);
				break;
			case '\\':
				walk(mirrorMap, scoreMap, row + 1, column, DirectionEnum.SOUTH, countRows, countColumns);
				break;
			default:
				System.out.println("Something bad happened, char switch....check code");
				break;
			}
			break;
		case SOUTH:
			switch (currentChar) {
			case '.':
				walk(mirrorMap, scoreMap, row + 1, column, DirectionEnum.SOUTH, countRows, countColumns);
				break;
			case '-':
				walk(mirrorMap, scoreMap, row, column - 1, DirectionEnum.WEST, countRows, countColumns);
				walk(mirrorMap, scoreMap, row, column + 1, DirectionEnum.EAST, countRows, countColumns);
				break;
			case '|':
				walk(mirrorMap, scoreMap, row + 1, column, DirectionEnum.SOUTH, countRows, countColumns);
				break;
			case '/':
				walk(mirrorMap, scoreMap, row, column - 1, DirectionEnum.WEST, countRows, countColumns);
				break;
			case '\\':
				walk(mirrorMap, scoreMap, row, column + 1, DirectionEnum.EAST, countRows, countColumns);
				break;
			default:
				System.out.println("Something bad happened, char switch....check code");
				break;
			}
			break;
		case WEST:
			switch (currentChar) {
			case '.':
				walk(mirrorMap, scoreMap, row, column - 1, DirectionEnum.WEST, countRows, countColumns);
				break;
			case '-':
				walk(mirrorMap, scoreMap, row, column - 1, DirectionEnum.WEST, countRows, countColumns);
				break;
			case '|':
				walk(mirrorMap, scoreMap, row - 1, column, DirectionEnum.NORTH, countRows, countColumns);
				walk(mirrorMap, scoreMap, row + 1, column, DirectionEnum.SOUTH, countRows, countColumns);
				break;
			case '/':
				walk(mirrorMap, scoreMap, row + 1, column, DirectionEnum.SOUTH, countRows, countColumns);
				break;
			case '\\':
				walk(mirrorMap, scoreMap, row - 1, column, DirectionEnum.NORTH, countRows, countColumns);
				break;
			default:
				System.out.println("Something bad happened, char switch....check code");
				break;
			}
			break;
		case NORTH:
			switch (currentChar) {
			case '.':
				walk(mirrorMap, scoreMap, row - 1, column, DirectionEnum.NORTH, countRows, countColumns);
				break;
			case '-':
				walk(mirrorMap, scoreMap, row, column - 1, DirectionEnum.WEST, countRows, countColumns);
				walk(mirrorMap, scoreMap, row, column + 1, DirectionEnum.EAST, countRows, countColumns);
				break;
			case '|':
				walk(mirrorMap, scoreMap, row - 1, column, DirectionEnum.NORTH, countRows, countColumns);
				break;
			case '/':
				walk(mirrorMap, scoreMap, row, column + 1, DirectionEnum.EAST, countRows, countColumns);
				break;
			case '\\':
				walk(mirrorMap, scoreMap, row, column - 1, DirectionEnum.WEST, countRows, countColumns);
				break;
			default:
				System.out.println("Something bad happened, char switch....check code");
				break;
			}
			break;
		default:
			System.out.println("Something bad happened, direction switch....check code");
			break;
		}
	}

}
