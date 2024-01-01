package com.james.aoc.year2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.james.aoc.util.Day23MazeLocation;
import com.james.aoc.util.DirectionEnum;
import com.james.aoc.util.FileConverter;
import com.james.aoc.util.Location;

public class Day23 {

	private static int locationCount = 1;
	//private static int[][] visitedFromN;
	//private static int[][] visitedFromE;
	//private static int[][] visitedFromS;
	//private static int[][] visitedFromW;

	public static Long getAnswer(String filename, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		int length = 0;
		if (rows.get(0) != null) {
			length = rows.get(0).length();
		}
		char[][] mazeMap = new char[rows.size()][length];
		boolean[][] visited = new boolean[rows.size()][length];
		//visitedFromN = new int[rows.size()][length];
		//visitedFromE = new int[rows.size()][length];
		//visitedFromS = new int[rows.size()][length];
		//visitedFromW = new int[rows.size()][length];
		visited[0][1] = true;
		Day23MazeLocation start = new Day23MazeLocation(locationCount, new Location(0, 1), DirectionEnum.SOUTH, 0,
				visited);
		locationCount++;
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

		List<Day23MazeLocation> completeLocations = new ArrayList<>();

		// Assumption always bottom right (-1)
		Location end = new Location(rows.size() - 1, length - 2);

		List<Day23MazeLocation> currentLocations = new ArrayList<>();
		currentLocations.add(start);
		int i = 1;
		while (currentLocations.size() > 0) {
			List<Day23MazeLocation> newLocations = new ArrayList<>();
			for (Day23MazeLocation currentLocation : currentLocations) {
				newLocations.addAll(takeAStep(currentLocation, mazeMap, rows.size() - 1, length - 1));
			}
			/*
			// Check valid - remove no valid
			List<Integer> fasterPath = new ArrayList<>();
			for (Day23MazeLocation currentLocation : newLocations) {
				DirectionEnum travelDirection = currentLocation.getTravelDirection();
				int row = currentLocation.getLocation().getRow();
				int column = currentLocation.getLocation().getColumn();
				int name = currentLocation.getName();
				switch (travelDirection) {
				case NORTH:
					if (visitedFromS[row][column] != 0) {
						fasterPath.add(visitedFromS[row][column]);
						System.out.println("**Preparing to remove South: " + "(" + row + "," + column + ") " + visitedFromS[row][column] + ", replaced by " + name);
					}
					visitedFromS[row][column] = name;
					break;
				case EAST:
					if (visitedFromW[row][column] != 0) {
						fasterPath.add(visitedFromW[row][column]);
						System.out.println("**Preparing to remove West: " + "(" + row + "," + column + ") " + visitedFromW[row][column] + ", replaced by " + name);
					}
					visitedFromW[row][column] = name;
					break;
				case SOUTH:
					if (visitedFromN[row][column] != 0) {
						fasterPath.add(visitedFromN[row][column]);
						System.out.println("**Preparing to remove North: " + "(" + row + "," + column + ") " + visitedFromN[row][column] + ", replaced by " + name);
					}
					visitedFromN[row][column] = name;
					break;
				case WEST:
					if (visitedFromE[row][column] != 0) {
						fasterPath.add(visitedFromE[row][column]);
						System.out.println("**Preparing to remove East: " + "(" + row + "," + column + ") " + visitedFromE[row][column] + ", replaced by " + name);
					}
					visitedFromE[row][column] = name;
					break;
				}
			}

			// Remove paths..
			Iterator<Day23MazeLocation> iterator = newLocations.iterator();
			while (iterator.hasNext()) {
				Day23MazeLocation currentLocation = iterator.next();
				if (fasterPath.contains(currentLocation.getName())) {
					//iterator.remove();
					System.out.println("***Removed old path: " + currentLocation.getName());
				}
			}
*/
			// check if at the end..
			for (Day23MazeLocation currentLocation : newLocations) {
				if (currentLocation.getLocation().equals(end)) {
					completeLocations.add(currentLocation);
				}
			}
			
			currentLocations = newLocations;
			i++;
			System.out.println(i + ": Path Count: " + currentLocations.size());
		}

		long answer = 0;
		for (Day23MazeLocation currentLocation : completeLocations) {
			answer = Math.max(answer, currentLocation.getStepsTaken());
		}

		return answer;
	}

	private static List<Day23MazeLocation> takeAStep(Day23MazeLocation currentLocation, char[][] mazeMap, int maxRow,
			int maxColumn) {
		List<Day23MazeLocation> newLocations = new ArrayList<>();

		int row = currentLocation.getLocation().getRow();
		int column = currentLocation.getLocation().getColumn();
		int stepCount = currentLocation.getStepsTaken() + 1;
		int name = currentLocation.getName();
		boolean[][] visited = currentLocation.getVisited();
		DirectionEnum travelDirection = currentLocation.getTravelDirection();

		boolean firstPathDone = false;
		// Try North
		if (travelDirection != DirectionEnum.SOUTH) {
			if (row - 1 >= 0) {
				char newValue = mazeMap[row - 1][column];
				if ((newValue == '.') || (newValue == '^') || (newValue == '<') || (newValue == '>')) {
					boolean beenBefore = visited[row - 1][column];
					if (!beenBefore) {
						if (!firstPathDone) {
							firstPathDone = true;
						} else {
							// New Path increment name
							name = locationCount++;
						}
						boolean[][] clone = deepCopy(visited);
						clone[row - 1][column] = true;
						Location newLocation = new Location(row - 1, column);
						Day23MazeLocation newMazeLocation = new Day23MazeLocation(name, newLocation,
								DirectionEnum.NORTH, stepCount, clone);
						newLocations.add(newMazeLocation);
					}
				}
			}
		}

		// Try East
		if (travelDirection != DirectionEnum.WEST) {
			if (column + 1 <= maxColumn) {
				char newValue = mazeMap[row][column + 1];
				if ((newValue == '.') || (newValue == '^') || (newValue == 'v') || (newValue == '>')) {
					boolean beenBefore = visited[row][column + 1];
					if (!beenBefore) {
						if (!firstPathDone) {
							firstPathDone = true;
						} else {
							// New Path increment name
							name = locationCount++;
						}
						boolean[][] clone = deepCopy(visited);
						clone[row][column + 1] = true;
						Location newLocation = new Location(row, column + 1);
						Day23MazeLocation newMazeLocation = new Day23MazeLocation(name, newLocation, DirectionEnum.EAST,
								stepCount, clone);
						newLocations.add(newMazeLocation);
					}
				}
			}
		}

		// Try SOUTH
		if (travelDirection != DirectionEnum.NORTH) {
			if (row + 1 <= maxRow) {
				char newValue = mazeMap[row + 1][column];
				if ((newValue == '.') || (newValue == '<') || (newValue == 'v') || (newValue == '>')) {
					boolean beenBefore = visited[row + 1][column];
					if (!beenBefore) {
						if (!firstPathDone) {
							firstPathDone = true;
						} else {
							// New Path increment name
							name = locationCount++;
						}
						boolean[][] clone = deepCopy(visited);
						clone[row + 1][column] = true;
						Location newLocation = new Location(row + 1, column);
						Day23MazeLocation newMazeLocation = new Day23MazeLocation(name, newLocation,
								DirectionEnum.SOUTH, stepCount, clone);
						newLocations.add(newMazeLocation);
					}
				}
			}
		}

		// Try WEST
		if (travelDirection != DirectionEnum.EAST) {
			if (column - 1 >= 0) {
				char newValue = mazeMap[row][column - 1];
				if ((newValue == '.') || (newValue == '<') || (newValue == 'v') || (newValue == '^')) {
					boolean beenBefore = visited[row][column - 1];
					if (!beenBefore) {
						if (!firstPathDone) {
							firstPathDone = true;
						} else {
							// New Path increment name
							name = locationCount++;
						}
						boolean[][] clone = deepCopy(visited);
						clone[row][column - 1] = true;
						Location newLocation = new Location(row, column - 1);
						Day23MazeLocation newMazeLocation = new Day23MazeLocation(name, newLocation, DirectionEnum.WEST,
								stepCount, clone);
						newLocations.add(newMazeLocation);
					}
				}
			}
		}

		return newLocations;
	}

	private static boolean[][] deepCopy(boolean[][] original) {
		if (original == null) {
			return null;
		}

		final boolean[][] result = new boolean[original.length][];
		for (int i = 0; i < original.length; i++) {
			result[i] = Arrays.copyOf(original[i], original[i].length);
		}
		return result;
	}

}
