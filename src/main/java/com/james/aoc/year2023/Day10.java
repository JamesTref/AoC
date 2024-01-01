package com.james.aoc.year2023;

import java.util.List;

import com.james.aoc.util.FileConverter;

public class Day10 {

	public static Integer getAnswer(String filename, boolean part2) {

		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		int[] start = findStart(rows);
		int length = 0;
		if (rows.get(0) != null) {
			length = rows.get(0).length();
		}
		//Stores if the tile is in the loop
		boolean[][] loop = new boolean[rows.size()][length];

		// Now find starting direction - assuming there is only 1!!
		char direction = findStartDirection(rows, start);

		int x = start[0];
		int y = start[1];
		// Make the first move
		if (direction == 'N') {
			y--;
		} else if (direction == 'S') {
			y++;
		} else {
			x++; // As we picked E as default
		}
		
		//Mark first tiles as in loop
		//Loop[row][column]
		loop[start[1]][start[0]] = true;
		loop[y][x] = true;

		// Now follow the path counting each step, until back at the start
		int steps = 1;
		while (x != start[0] || y != start[1]) {
			int deltaX = 0;
			int deltaY = 0;
			String key = getChar(rows, y, x) + String.valueOf(direction); // lines[y][x] + direction
			switch (key) {
			case "|S":
				deltaY = 1;
				break;
			case "|N":
				deltaY = -1;
				break;
			case "-E":
				deltaX = 1;
				break;
			case "-W":
				deltaX = -1;
				break;
			case "LS":
				deltaX = 1;
				break;
			case "LW":
				deltaY = -1;
				break;
			case "JS":
				deltaX = -1;
				break;
			case "JE":
				deltaY = -1;
				break;
			case "7N":
				deltaX = -1;
				break;
			case "7E":
				deltaY = 1;
				break;
			case "FN":
				deltaX = 1;
				break;
			case "FW":
				deltaY = 1;
				break;
			default:
				System.out.println(
						"You messed up!!!!! something wrong with: " + rows.get(y) + " " + direction);

			}
			if (deltaY == 1) {
				direction = 'S';
			} else if (deltaY == -1) {
				direction = 'N';
			} else if (deltaX == -1) {
				direction = 'W';
			} else {
				direction = 'E';
			}
			x += deltaX;
			y += deltaY;
			steps++;
			loop[y][x] = true;
		}
		
		int answer = 0;
		if (!part2) {
			answer = steps / 2;
		} else {
			int rowCount = loop.length;
			int columnCount = loop[0].length;
			int currentRow = 0;
			while (currentRow < rowCount) {
				int currentColumn = 0;
				while (currentColumn < columnCount) {
					if (!loop[currentRow][currentColumn]) {
						//Check above
						// 0 = left, 1= right
						int aboveCounts[] = new int[2];
						int aboveRow = currentRow - 1;
						while (aboveRow > -1) {
							if (loop[aboveRow][currentColumn] && getChar(rows, aboveRow, currentColumn) != '|') {
								char value = getChar(rows, aboveRow, currentColumn);
								if (value == '-') {
									aboveCounts[0] = aboveCounts[0] + 1;
									aboveCounts[1] = aboveCounts[1] + 1;
								} else if (value == '7' || value == 'J') {
									aboveCounts[0] = aboveCounts[0] + 1;
								} else if (value == 'F' || value == 'L') {
									aboveCounts[1] = aboveCounts[1] + 1;
								}
							}
							aboveRow--;
						}
						boolean above = false;
						if (aboveCounts[0]%2 == 1 && aboveCounts[1]%2 == 1) {
							above = true;
						}
						
						//Check below
						// 0 = left, 1= right
						int[] belowCounts = new int[2];
						int belowRow = currentRow + 1;
						while (belowRow < rowCount) {
							if (loop[belowRow][currentColumn] && getChar(rows, belowRow, currentColumn) != '|') {
								char value = getChar(rows, belowRow, currentColumn);
								if (value == '-') {
									belowCounts[0] = belowCounts[0] + 1;
									belowCounts[1] = belowCounts[1] + 1;
								} else if (value == '7' || value == 'J') {
									belowCounts[0] = belowCounts[0] + 1;
								} else if (value == 'F' || value == 'L') {
									belowCounts[1] = belowCounts[1] + 1;
								}
							}
							belowRow++;
						}
						boolean below = false;
						if (belowCounts[0]%2 == 1 && belowCounts[1]%2 == 1) {
							below = true;
						}
						
						//Check left
						// 0 = top, 1= bottom
						int[] leftCounts = new int[2];
						int leftColumn = currentColumn - 1;
						while (leftColumn > -1) {
							if (loop[currentRow][leftColumn] && getChar(rows, currentRow, leftColumn) != '-') {
								char value = getChar(rows, currentRow, leftColumn);
								if (value == '|') {
									leftCounts[0] = leftCounts[0] + 1;
									leftCounts[1] = leftCounts[1] + 1;
								} else if (value == 'J' || value == 'L') {
									leftCounts[0] = leftCounts[0] + 1;
								} else if (value == 'F' || value == '7') {
									leftCounts[1] = leftCounts[1] + 1;
								}
							}
							leftColumn--;
						}
						boolean left = false;
						if (leftCounts[0]%2 == 1 && leftCounts[1]%2 == 1) {
							left = true;
						}
						
						//Check right
						// 0 = top, 1= bottom
						int[] rightCounts = new int[2];
						int rightColumn = currentColumn + 1;
						while (rightColumn < columnCount) {
							if (loop[currentRow][rightColumn] && getChar(rows, currentRow, rightColumn) != '-') {
								char value = getChar(rows, currentRow, rightColumn);
								if (value == '|') {
									rightCounts[0] = rightCounts[0] + 1;
									rightCounts[1] = rightCounts[1] + 1;
								} else if (value == 'J' || value == 'L') {
									rightCounts[0] = rightCounts[0] + 1;
								} else if (value == 'F' || value == '7') {
									rightCounts[1] = rightCounts[1] + 1;
								}
							}
							rightColumn++;
						}
						boolean right = false;
						if (rightCounts[0]%2 == 1 && rightCounts[1]%2 == 1) {
							right = true;
						}
						
						if (above && below && left && right) {
							answer++;
						}
					}
					currentColumn++;
				}
				currentRow++;
			}
		}

		return answer;
	}

	// top left is assumed to be (0,0)
	private static char getChar(List<String> rows, int row, int column) {
		char value = 'X';
		if (rows.size() > row) {
			String line = rows.get(row);
			if (line.length() > column) {
				value = line.charAt(column);
			}
		}
		return value;
	}

	private static char findStartDirection(List<String> rows, int[] start) {
		int x = start[0];
		int y = start[1];
		char direction = 'X';
		String lineBelow = "";
		char valueBelow = 'X';
		if (rows.size() >= y + 1) {
			lineBelow = rows.get(y + 1);
			valueBelow = lineBelow.toCharArray()[x];
		}

		if (valueBelow == '|' || valueBelow == 'L' || valueBelow == 'J') {
			direction = 'S';
			y++;
		}
		if (direction == 'X') {
			String lineAbove = "";
			char valueAbove = 'X';
			if (y > 0) {
				lineAbove = rows.get(y - 1);
				valueAbove = lineAbove.toCharArray()[x];
			}
			if (valueAbove == '|' || valueAbove == 'F' || valueAbove == '7') {
				direction = 'N';
				y--;
			}
		}
		if (direction == 'X') {
			// only possible starting shape left is -, pick E or W, it doesn't matter which
			// way round the loop we go
			direction = 'E';
			x++;
		}
		return direction;
	}

	private static int[] findStart(List<String> rows) {
		int[] start = new int[2];
		int i = 0;
		for (String row : rows) {
			if (row.indexOf('S') > 0) {
				start[0] = row.indexOf('S');
				start[1] = i;
				break;
			}
			i++;
		}
		return start;
	}

}
