package com.james.aoc.year2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.james.aoc.lib2024.Coord;
import com.james.aoc.lib2024.Direction;
import com.james.aoc.lib2024.Divider;
import com.james.aoc.lib2024.Grid;
import com.james.aoc.util.FileConverter;

public class Day8 {

	public static int getAnswer(String filename, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> input = converter.convertFileToArray(filename);

		Grid<Character> g = new Grid<>(input, new Divider.Char());

        Map<Character, List<Coord>> antennae = new HashMap<>();
        for (int r = 0; r < g.getHeight(); r++) {
            for (int c = 0; c < g.getWidth(); c++) {
                char a = g.get(r, c);
                if (a != '.') {
                    if (!antennae.containsKey(a)) {
                        antennae.put(a, new ArrayList<>());
                    }
                    antennae.get(a).add(new Coord(r, c));
                }
            }
        }

        Set<Coord> antinodes = new HashSet<>(), full = new HashSet<>();
        for (char c : antennae.keySet()) {
            List<Coord> matching = antennae.get(c);
            for (int i = 0; i < matching.size(); i++) {
                for (int j = 0; j < matching.size(); j++) {
                    if (i == j) { continue; }
                    Direction d = matching.get(i).distance(matching.get(j));
                    if (g.isValid(matching.get(j).relative(d))) {
                        antinodes.add(matching.get(j).relative(d));
                    }
                    Coord curr = matching.get(i);
                    while (g.isValid(curr.relative(d))) {
                        full.add(curr.relative(d));
                        curr = curr.relative(d);
                    }
                }
            }
        }
        
        if (!part2) {
        	return antinodes.size();
        } else {
        	return full.size();
        }

	}

}
