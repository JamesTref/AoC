package com.james.aoc.year2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.james.aoc.util.Day5Map;
import com.james.aoc.util.FileConverter;
import com.james.aoc.util.SeedMap;

public class Day5 {

	public static Long getAnswer(String filename, boolean part2) {
		//HashMap<Long, Long> seedMap = new HashMap<Long, Long>();
		
		HashMap<SeedMap, Long> seedRangeMap = new HashMap<SeedMap, Long>();
		
		HashMap<Integer, String> mapNames = new HashMap<Integer, String>();
		
		HashMap<Integer, ArrayList<Day5Map>> allMaps = new HashMap<Integer, ArrayList<Day5Map>>();
		//seeds
		//<space>
		//Map...
		//Data
		//<Space>
		///Map..
		//Data etc....
		
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		int rowNum = 1;
		boolean mapStart = false;
		Integer currentMap = 0;
		for (String row : rows) {
			if (rowNum == 1) { //Seed Row
				if (!part2) {
					String seedNumbers = row.replaceAll("seeds: ", "");
					String[] seedArray = seedNumbers.split(" ");
					for (String seedNum : seedArray) {
						Long seed = Long.valueOf(seedNum);
						
						seedRangeMap.put(new SeedMap(seed, 1l), null);
						//seedMap.put(seed, null);
					}
				} else {
					String seedNumbers = row.replaceAll("seeds: ", "");
					String[] seedArray = seedNumbers.split(" ");
					int pair = 1;
					long start = 0;
					for (String seedNum : seedArray) {
						if (pair == 1) {
							start = Long.valueOf(seedNum);
							pair = 2;
						} else {
							seedRangeMap.put(new SeedMap(start, Long.valueOf(seedNum)), null);
							pair = 1;
						}
					}
				}
			} else if (row.isBlank()) { //Empty Row
				currentMap++;
				mapStart = true;
			} else if (mapStart) { //First row of Map
				mapStart = false;
				String currentMapName = row.replaceAll(" map:", "");
				mapNames.put(currentMap, currentMapName);
			} else { //A map row
				String[] mapArray = row.split(" ");
				Day5Map map = new Day5Map(Long.valueOf(mapArray[0]), Long.valueOf(mapArray[1]), Long.valueOf(mapArray[2]));
				if (allMaps.containsKey(currentMap)) {
					ArrayList<Day5Map> maps = allMaps.get(currentMap);
					maps.add(map);
				} else {
					ArrayList<Day5Map> maps = new ArrayList<Day5Map>();
					maps.add(map);
					allMaps.put(currentMap, maps);
				}
			}
			rowNum++;
		}
		
		long answer = 0;
		
		int count = 1;
		for (SeedMap seedMapIt : seedRangeMap.keySet()) {
			long startValue = seedMapIt.getSourceStart();
			long range = seedMapIt.getRange();
			
			long currentValue = startValue;
			long lowestNewValue = Long.MAX_VALUE;
			
			while (currentValue < startValue + range) {
				
				long startValue2 = currentValue;
				for (ArrayList<Day5Map> maps : allMaps.values()) {
					for (Day5Map map : maps) {
						Long newValue = map.mapValue(currentValue);
						if (currentValue != newValue.longValue()) {
							currentValue = newValue;
							break;
						}
					}
				}
				if (currentValue < lowestNewValue) {
					lowestNewValue = currentValue;
				}
				
				currentValue = startValue2 + 1;
			}
			
			seedRangeMap.put(seedMapIt, lowestNewValue);
			System.out.println("Completed a map (" + count + "/" + seedRangeMap.keySet().size() + ") [" +  seedMapIt.getSourceStart() + " " + seedMapIt.getRange() + "], lowest value: " + lowestNewValue);
			count++;
		}
		
		for (Long finalValue : seedRangeMap.values()) {
			if (answer == 0) {
				answer = finalValue;
			} else if (finalValue < answer) {
				answer = finalValue;
			}
		}
		
		return answer;
	}

}
