package com.james.aoc.year2023;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import com.james.aoc.util.FileConverter;

public class Day2 {
	
	private static enum Colour {
	    BLUE, RED, GREEN
	}

	public static Integer getAnswer(String filename, boolean part2) {
		HashMap<Integer, List<HashMap<Day2.Colour, Integer>>> data = new HashMap<Integer, List<HashMap<Day2.Colour, Integer>>>();
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		
		for (String row : rows) {
			String gamePart = row.split(":")[0];
			String dataPart = row.split(":")[1];
			
			Integer game = Integer.valueOf(gamePart.split("Game ")[1]);
			
			List<HashMap<Colour, Integer>> colourList = new ArrayList<HashMap<Day2.Colour, Integer>>();
			String[] colourData = dataPart.split(";");
		
			for (String colourArray : colourData) {	
				HashMap<Colour, Integer> colourMap = new HashMap<Day2.Colour, Integer>();
				
				String[] colours = colourArray.split(",");
				Integer blue = Integer.valueOf(0);
				Integer red = Integer.valueOf(0);
				Integer green = Integer.valueOf(0);
				for (String colour : colours) {
					colour = colour.trim();
					//String[] test = colour.split("blue");
					if (colour.contains("blue")) {
						blue = Integer.valueOf(colour.split("blue")[0].trim());
					}
					if (colour.contains("red")) {
						red = Integer.valueOf(colour.split("red")[0].trim());
					}
					if (colour.contains("green")) {
						green = Integer.valueOf(colour.split("green")[0].trim());
					}
				}
				colourMap.put(Day2.Colour.BLUE, blue);
				colourMap.put(Day2.Colour.RED, red);
				colourMap.put(Day2.Colour.GREEN, green);
				
				colourList.add(colourMap);
			}

			data.put(game, colourList);
		}
		
		int answer = 0;
		if (part2) {
			for (Integer game : data.keySet()) {
				answer = answer + power(data.get(game));
			}
			
		} else {
			for (Integer game : data.keySet()) {
				if (canExist(data.get(game), 12, 13, 14)) {
					answer = answer + game;
				}
			}	
		}
		return answer;
	}
	
	private static boolean canExist(List<HashMap<Day2.Colour, Integer>> gameData, int maxRed, int maxGreen, int maxBlue) {
		boolean possible = true;
		
		for (HashMap<Day2.Colour, Integer> item : gameData) {
			if (item.get(Day2.Colour.RED) > maxRed) {
				possible = false;
				break;
			}
			if (item.get(Day2.Colour.GREEN) > maxGreen) {
				possible = false;
				break;
			}
			if (item.get(Day2.Colour.BLUE) >  maxBlue) {
				possible = false;
				break;
			}
		}
		
		return possible;
	}
	
	private static int power(List<HashMap<Day2.Colour, Integer>> gameData) {
		int maxRed = 0;
		int maxGreen = 0;
		int maxBlue = 0;
		
		for (HashMap<Day2.Colour, Integer> item : gameData) {
			if (item.get(Day2.Colour.RED) > maxRed) {
				maxRed = item.get(Day2.Colour.RED);
			}
			if (item.get(Day2.Colour.GREEN) > maxGreen) {
				maxGreen = item.get(Day2.Colour.GREEN);
			}
			if (item.get(Day2.Colour.BLUE) >  maxBlue) {
				maxBlue = item.get(Day2.Colour.BLUE);
			}
		}
		
		return maxRed * maxGreen * maxBlue;
	}

}
