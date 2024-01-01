package com.james.aoc.year2023;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.james.aoc.util.Day17Object;
import com.james.aoc.util.FileConverter;

public class Day17 {

	public static PriorityQueue<Day17Object> pq = new PriorityQueue<>();
	// NOTE is answer returns anything close to this, then increase!
	public static int maxHeatLoss = 9999999;

	public static Long getAnswer(String filename, boolean part2) {
		pq = new PriorityQueue<>();
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		int mapColumnCount = 0;
		if (rows.get(0) != null) {
			mapColumnCount = rows.get(0).length();
		}
		int[][] heatLossMap = new int[rows.size()][mapColumnCount];
		int mapRowCount = 0;
		for (String row : rows) {
			int columnCount = 0;
			for (Character value : row.toCharArray()) {
				heatLossMap[mapRowCount][columnCount] = Integer.parseInt(value.toString());
				columnCount++;
			}
			mapRowCount++;
		}

		Day17Object startHor = new Day17Object(0, 0, 0, 0); //!!!0 as heat loss on first block does not count!
		pq.add(startHor);
		Day17Object startVert = new Day17Object(0, 0, 0, 1); //!!!0 as heat loss on first block does not count!
		pq.add(startVert);

		long answer = 0;

		Map<String, Integer> processed = new HashMap<>();
		int maxMoves = 3;
		int minMoves = 1;
		if (part2) {
			maxMoves = 10;
			minMoves = 4;
		}

		while (!pq.isEmpty()) {
			Day17Object current = pq.poll();
			System.out.println("Processing: " + current);

			// Check if we have processed already
			String key = current.getRow() + "," + current.getColumn() + "," + current.getDirection();
			if (processed.containsKey(key)) {
				//Looping back to same point cannot give a faster answer, so do not add anything more to process
				continue;
			}
			processed.put(key, current.getHeatLoss());

			// We have reach the end!
			if (current.getRow() == mapRowCount - 1 && current.getColumn() == mapColumnCount - 1) {
				System.out.println("Ended!");
				System.out.println("Object Value: " + current);
				if (answer == 0) {
					answer = current.getHeatLoss();
				} else {
					answer = Math.min(answer, current.getHeatLoss());
				}
			}
			
			//Maybe not need - but if heat lost so far is greater than an answer we have already found...we can quit
			if (answer > 0 && current.getHeatLoss() > answer) {
				System.out.println("SKIPPED");
				continue; //No point in continuing, as already lost more heat
			}
			
			//Not at the end, so add possible moves...
			addPossibleMoves(mapColumnCount, heatLossMap, mapRowCount, maxMoves, minMoves, current);
		}

		return answer;
	}

	private static void addPossibleMoves(int mapColumnCount, int[][] heatLossMap, int mapRowCount, int maxMoves,
			int minMoves, Day17Object current) {
		int currentHeatLoss = current.getHeatLoss();
		if (current.getDirection() == 0) {
			int i = 1;
			int newHeatLoss = 0;
			while (i <= maxMoves) {
				newHeatLoss = newHeatLoss + calc(current.getRow(), current.getColumn() + i, mapRowCount, mapColumnCount, heatLossMap);
				if (newHeatLoss >= maxHeatLoss) {
					// Move takes us outside the map
					i++;
					break;
				}
				if (i < minMoves) {
					//Must complete at least min steps, so not a valid move
					i++;
					continue;
				}
				Day17Object newObject = new Day17Object(currentHeatLoss + newHeatLoss, current.getRow(), current.getColumn() + i, 1);
				pq.add(newObject);
				System.out.println("Added: " + newObject);
				i++;
			}

			i = 1;
			newHeatLoss = 0;
			while (i <= maxMoves) {
				newHeatLoss = newHeatLoss + calc(current.getRow(), current.getColumn() - i, mapRowCount, mapColumnCount, heatLossMap);
				if (newHeatLoss >= maxHeatLoss) {
					// Move takes us outside the map
					i++;
					break;
				}
				if (i < minMoves) {
					i++;
					continue;
				}
				Day17Object newObject = new Day17Object(currentHeatLoss + newHeatLoss, current.getRow(), current.getColumn() - i, 1);
				pq.add(newObject);
				System.out.println("Added: " + newObject);
				i++;
			}
		} else if (current.getDirection() == 1) {
			int i = 1;
			int newHeatLoss = 0;
			while (i <= maxMoves) {
				newHeatLoss = newHeatLoss + calc(current.getRow() + i, current.getColumn(), mapRowCount, mapColumnCount, heatLossMap);
				if (newHeatLoss >= maxHeatLoss) {
					// Move takes us outside the map
					i++;
					break;
				}
				if (i < minMoves) {
					i++;
					continue;
				}
				Day17Object newObject = new Day17Object(currentHeatLoss + newHeatLoss, current.getRow() + i, current.getColumn(), 0);
				pq.add(newObject);
				System.out.println("Added: " + newObject);
				i++;
			}

			i = 1;
			newHeatLoss = 0;
			while (i <= maxMoves) {
				newHeatLoss = newHeatLoss + calc(current.getRow() - i, current.getColumn(), mapRowCount, mapColumnCount, heatLossMap);
				if (newHeatLoss >= maxHeatLoss) {
					// Move takes us outside the map
					i++;
					break;
				}
				if (i < minMoves) {
					i++;
					continue;
				}
				Day17Object newObject = new Day17Object(currentHeatLoss + newHeatLoss, current.getRow() - i, current.getColumn(), 0);
				pq.add(newObject);
				System.out.println("Added: " + newObject);
				i++;
			}
		}
	}

	private static int calc(int row, int column, int maxRows, int maxColumns, int[][] heatLossMap) {
		if ((row < 0 || row >= maxRows) || (column < 0 || column >= maxColumns)) {
			return maxHeatLoss;
		}
		return heatLossMap[row][column];
	}

}
