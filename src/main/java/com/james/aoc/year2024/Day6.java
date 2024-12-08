package com.james.aoc.year2024;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.james.aoc.lib2024.Coord;
import com.james.aoc.lib2024.Direction;
import com.james.aoc.lib2024.Divider;
import com.james.aoc.lib2024.Grid;
import com.james.aoc.lib2024.Tuple.Pair;
import com.james.aoc.util.FileConverter;

public class Day6 {

	public static int getAnswer(String filename, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> input = converter.convertFileToArray(filename);
		Grid<Character> grid = new Grid<>(input, new Divider.Char());
		Set<Coord> visited = new HashSet<>();

		Direction dir = Direction.N;
		Coord start = grid.find('^'), curr = start;

		while (grid.isValid(curr)) {
			visited.add(curr);
			Coord ahead = curr.relative(dir);
			if (grid.isValid(ahead) && grid.get(ahead) == '#') {
				dir = Direction.right(dir);
			} else {
				curr = curr.relative(dir);
			}
		}

		int count = 0;
		for (Coord c : visited) {
			if (start.equals(c)) {
				continue;
			}

			Grid<Character> test = new Grid<>(input, new Divider.Char());
			test.set(c, '#');

			if (loops(test, start)) {
				count++;
			}
		}
		
		if (!part2) {
			return visited.size();
		} else {
			return count;
		}
	}

	static boolean loops(Grid<Character> g, Coord start) {
		Set<Pair<Coord, Direction>> turns = new HashSet<>();
		Direction dir = Direction.N;
		Coord curr = start;
		while (g.isValid(curr)) {
			Coord ahead = curr.relative(dir);

			if (g.isValid(ahead) && g.get(ahead) == '#') {
				dir = Direction.right(dir);
				if (turns.contains(new Pair<>(curr, dir))) {
					return true;
				}
				turns.add(new Pair<>(curr, dir));
			} else {
				curr = curr.relative(dir);
			}
		}
		return false;
	}
}
