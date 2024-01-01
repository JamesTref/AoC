package com.james.aoc.year2023;

import java.util.ArrayList;
import java.util.List;

import com.james.aoc.util.Day18Plan;
import com.james.aoc.util.FileConverter;

public class Day18 {

	public static Long getAnswer(String filename, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);

		List<Day18Plan> instructions = new ArrayList<>();

		for (String row : rows) {
			char direction = row.split(" ")[0].charAt(0);
			int distance = Integer.parseInt(row.split(" ")[1]);
			String colour = row.split(" ")[2];
			Day18Plan plan = new Day18Plan(direction, distance, colour);
			instructions.add(plan);
		}

		List<Point> coordinates = new ArrayList<>();
		Point current = new Point(0, 0);
		coordinates.add(current);
		int i = 1;
		long totalDistance = 0;
		for (Day18Plan step : instructions) {
			char direction = step.getDirection();
			long previousX = current.x;
			long previousY = current.y;
			long distance = step.getDistance();
			if (part2) {
				direction = step.getColourDirection();
				distance = step.getColourDistance();
			}
			
			totalDistance = totalDistance + distance;

			switch (direction) {
			case 'R':
				current = new Point(previousX + distance, previousY);
				break;
			case 'D':
				current = new Point(previousX, previousY - distance);
				break;
			case 'L':
				current = new Point(previousX - distance, previousY);
				break;
			case 'U':
				current = new Point(previousX, previousY + distance);
				break;
			default:
				System.out.println("Errr...this is bad...");
			}
			
			if (i < rows.size()) {
				coordinates.add(current);
			} else {
				// Last move should be start point, 0,0
				if (current.x != 0 || current.y != 0) {
					System.out.println("Bad last point - Check code!!");
				}
			}
			i++;
		}

		long area = calculateArea(coordinates);

		long answer = area + (totalDistance/2) + 1;

		return answer;
	}

	private static long calculateArea(List<Point> v) {
		int n = v.size();
		long a = 0;
		for (int i = 0; i < n - 1; i++) {
			a += v.get(i).x * v.get(i + 1).y - v.get(i + 1).x * v.get(i).y;
		}
		return Math.abs(a + v.get(n - 1).x * v.get(0).y - v.get(0).x * v.get(n - 1).y) / 2;
	}

	private static class Point {
		long x, y;

		Point(long x, long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return String.format("(%d, %d)", x, y);
		}
	}

}
