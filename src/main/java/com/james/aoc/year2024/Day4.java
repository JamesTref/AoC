package com.james.aoc.year2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.james.aoc.lib2024.Coord;
import com.james.aoc.lib2024.Direction;
import com.james.aoc.lib2024.Divider;
import com.james.aoc.lib2024.Grid;
import com.james.aoc.util.FileConverter;

public class Day4 {
	public static long getAnswer(String filename, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		Grid<Character> g = new Grid<>(rows, new Divider.Char());
		
		long answer = 0;
		if (!part2) {
			for (int r = 0; r < g.getHeight(); r++) {
				for (int c = 0; c < g.getWidth(); c++) {
					if (g.get(r, c) == 'X') {
						List<List<Character>> matches = g.radialSearch(new Coord(r, c), 3, Direction.ALL_DIRECTIONS);
						for (List<Character> list : matches) {
							if (list.toString().replaceAll("[\\[\\], ]", "").equals("MAS")) {
								answer++;
							}
						}
					}
				}
			}
		} else {
			for (int r = 0; r < g.getHeight(); r++) {
				for (int c = 0; c < g.getWidth(); c++) {
					if (g.get(r, c) == 'A') {
						List<List<Character>> matches = g.radialSearch(new Coord(r, c), 1, Direction.ORDINAL_DIRECTIONS);
						List<String> validMatches = new ArrayList<>(Arrays.asList("MSSM", "MMSS", "SMMS", "SSMM"));
						if (validMatches.contains(matches.toString().replaceAll("[\\[\\], ]", ""))) {
							answer++;
						}
					}
				}
			}
		}

		return answer;
	}

}
