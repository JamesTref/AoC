package com.james.aoc.year2023;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.james.aoc.util.FileConverter;
import com.james.aoc.util.Location;

public class Day21 {

	public static Long getAnswer(String filename, int steps, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		Location start = null;
		int mapColumnCount = 0;
		if (rows.get(0) != null) {
			mapColumnCount = rows.get(0).length();
		}
		char[][] gardenMap = new char[rows.size()][mapColumnCount];
		int mapRowCount = 0;
		for (String row : rows) {
			int columnCount = 0;
			for (Character value : row.toCharArray()) {
				if (value == 'S') {
					start = new Location(mapRowCount, columnCount);	
					gardenMap[mapRowCount][columnCount] = '.';
				} else {
					gardenMap[mapRowCount][columnCount] = value;
				}
				columnCount++;
			}
			mapRowCount++;
		}
		
		long answer = 0;
		int squareSize = mapColumnCount; //Assuming input is square;
		if (part2) {
			System.out.println("squareSize:" + squareSize); //checked
			System.out.println("Steps:" + steps); //checked
			//Assuming no rocks vertically or horizontally
			//Assuming start is in the middle
			//assuming width is odd
			long gridWidth = (steps-(squareSize/2))/squareSize - 1;
			System.out.println("gridWidth:" + gridWidth); //checked
			//Find number of add and even full grids...
			//Odd means start grid with odd steps left
			//Even means start grid with even steps left
			long oddFullGridCount = (gridWidth)*(gridWidth); //checked
			long evenFullGridCount = ((gridWidth+1))*((gridWidth+1)); //checked
			System.out.println("oddFullGridCount:" + oddFullGridCount);
			System.out.println("evenFullGridCount:" + evenFullGridCount);
			long oddGridCount = part1(squareSize*2, squareSize, new Location(squareSize-1, ((squareSize-1)/2)), gardenMap); //checked
			System.out.println("oddGridCount:" + oddGridCount);
			long evenGridCount = part1(squareSize*2+1, squareSize, new Location(squareSize-1, ((squareSize-1)/2)), gardenMap); //checked
			System.out.println("evenGridCount:" + evenGridCount);
			
			//further checks
			/*
			int james = 0;
			while (james < 20) {
				System.out.println(part1(squareSize*2+1+james, squareSize, new Location(((squareSize-1)/2), squareSize-1), gardenMap));
				james++;
			}
			*/
			//Calculate 4 corners
			int cornerSteps = squareSize-1;
			long corner_n = part1(cornerSteps, squareSize, new Location(squareSize-1, ((squareSize-1)/2)), gardenMap); 
			System.out.println("corner_n:" + corner_n);
			long corner_s = part1(cornerSteps, squareSize, new Location(0, ((squareSize-1)/2)), gardenMap); 
			System.out.println("corner_s:" + corner_s);
			long corner_e = part1(cornerSteps, squareSize, new Location(((squareSize-1)/2), 0), gardenMap); 
			System.out.println("corner_e:" + corner_e);
			long corner_w = part1(cornerSteps, squareSize, new Location(((squareSize-1)/2), squareSize-1), gardenMap); 
			System.out.println("corner_w:" + corner_w);
			
			//Calculate small triangle
			int smallSteps = squareSize-1-((squareSize-1)/2)-1;
			System.out.println("smallSteps:" + smallSteps);
			long small_tr = part1(smallSteps, squareSize, new Location(squareSize-1, 0), gardenMap); 
			System.out.println("small_tr:" + small_tr);
			long small_br = part1(smallSteps, squareSize, new Location(0, 0), gardenMap); 
			System.out.println("small_br:" + small_br);
			long small_bl = part1(smallSteps, squareSize, new Location(0, squareSize-1), gardenMap); 
			System.out.println("small_bl:" + small_bl);
			long small_tl = part1(smallSteps, squareSize, new Location(squareSize-1, squareSize-1), gardenMap); 
			System.out.println("small_tl:" + small_tl);
			
			//Calculate big triangle
			int bigSteps = squareSize + squareSize - 1 -((squareSize-1)/2)-1;
			System.out.println("bigSteps:" + bigSteps);
			long big_tr = part1(bigSteps, squareSize, new Location(squareSize-1, 0), gardenMap); 
			System.out.println("big_tr:" + big_tr);
			long big_br = part1(bigSteps, squareSize, new Location(0, 0), gardenMap); 
			System.out.println("big_br:" + big_br);
			long big_bl = part1(bigSteps, squareSize, new Location(0, squareSize-1), gardenMap); 
			System.out.println("big_bl:" + big_bl);
			long big_tl = part1(bigSteps, squareSize, new Location(squareSize-1, squareSize-1), gardenMap); 
			System.out.println("big_tl:" + big_tl);
			
			
			//Add all full grids
			answer = (oddFullGridCount*oddGridCount) + (evenFullGridCount*evenGridCount);//checked
			System.out.println("full count:" + answer);
			//Add the 4 corners
			answer = answer + corner_n + corner_s + corner_e + corner_w;
			long cornerCount = corner_n + corner_s + corner_e + corner_w;
			System.out.println("cornerCount:" + cornerCount);
			//add small triangles, gridWidth+1 is number of each of these
			answer = answer + (gridWidth+1)*(small_tr+small_br+small_bl+small_tl);
			long smallCount = (gridWidth+1)*(small_tr+small_br+small_bl+small_tl);
			System.out.println("smallCount:" + smallCount);
			//add big triangles, gridWidth is number of each of these
			answer = answer + (gridWidth)*(big_tr+big_br+big_bl+big_tl);
			long bigCount = (gridWidth)*(big_tr+big_br+big_bl+big_tl);
			System.out.println("bigCount:" + bigCount);
					
			
			System.out.println("answer:" + answer);
		} else {
			answer = part1(steps, squareSize, start, gardenMap);
		}

		return answer;
	}

	private static long part1(long steps, long squareSize, Location startPoint, char[][] gardenMap) {
		Set<Location> plots = new HashSet<>();
		int i = 0;
		plots.add(startPoint);
		while (i < steps) {
			plots = takeAStep(squareSize, gardenMap, plots);
			i++;
		}
		long answer = plots.size();
		return answer;
	}

	private static Set<Location> takeAStep(long squareSize, char[][] gardenMap, Set<Location> plots) {
		Set<Location> newPlots = new HashSet<>();
		for (Location plot : plots) {
			int c = plot.getColumn();
			int r = plot.getRow();
			//check north
			if (r - 1 >= 0) {
				char n = gardenMap[r-1][c];
				if (n=='.') {
					newPlots.add(new Location(r-1, c));
				}
			}
			//check south
			if (r + 1 < squareSize) {
				char n = gardenMap[r+1][c];
				if (n=='.') {
					newPlots.add(new Location(r+1, c));
				}
			}
			//check east
			if (c + 1 < squareSize) {
				char n = gardenMap[r][c+1];
				if (n=='.') {
					newPlots.add(new Location(r, c+1));
				}
			}
			//check west
			if (c - 1 >= 0) {
				char n = gardenMap[r][c-1];
				if (n=='.') {
					newPlots.add(new Location(r, c-1));
				}
			}
		}
		plots = newPlots;
		return plots;
	}

}
