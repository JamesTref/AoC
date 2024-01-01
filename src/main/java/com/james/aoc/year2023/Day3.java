package com.james.aoc.year2023;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import com.james.aoc.util.FileConverter;
import com.james.aoc.util.Location;

public class Day3 {

	private static Character EMPTY = '.';
	private static Character STAR = '*';

	private static Map<Location, Character> data = new HashMap<Location, Character>();
	private static Integer totalRows = 0;
	private static Integer totalColumns = 0;

	public static Integer getAnswer(String filename, boolean part2) throws Exception {
		data = new HashMap<Location, Character>();
		totalRows = 0;
		totalColumns = 0;
		// Sort Data
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		totalRows = rows.size();
		int rowCount = 1;
		for (String row : rows) {
			char[] items = row.toCharArray();
			if (totalColumns == 0) {
				totalColumns = items.length;
			} else if (totalColumns != items.length) {
				throw new Exception("ColumnCount Diff: " + totalColumns + ", " + items.length);
			}
			int columnCount = 1;
			for (char item : items) {
				data.put(new Location(rowCount, columnCount), item);
				columnCount++;
			}
			rowCount++;
		}
		
		// Loop through
		// 1Check each number is touching
		// get next
		// accumulate full numbers.
		int currentRow = 1;
		List<Integer> validnumbers = new ArrayList<Integer>();
		Map<Location, List<Integer>> gears = new HashMap<Location, List<Integer>>();
		//List<Integer> invalidnumbers = new ArrayList<Integer>();
		while (currentRow <= totalRows) {
			StringBuilder builder = new StringBuilder();
			boolean valid = false;
			boolean star = false;
			Location starLocation = new Location(0,0);
			int currentColumn = 1;
			while (currentColumn <= totalColumns) {
				Location currentLoc = new Location(currentRow, currentColumn);
				Character currentChar = data.get(currentLoc);
				if (isNumeric(currentChar)) {
					if (touching(currentLoc)) {
						valid = true;
					}
					Location x = touchingStar(currentLoc);
					if (x != null) {
						star = true;
						starLocation = x;
					}
					builder.append(currentChar);
				} 
				
				if ((currentColumn==totalColumns && (builder.length() > 0)) || ((builder.length() > 0) && (!isNumeric(currentChar)))) {
					if (valid) {
						Integer fullValue = Integer.valueOf(builder.toString());
						validnumbers.add(fullValue);
						if (star) {
							if (gears.containsKey(starLocation)) {
								gears.get(starLocation).add(fullValue);
							} else {
								List<Integer> l = new ArrayList<Integer>();
								l.add(fullValue);
								gears.put(starLocation, l);
							}
						}
					} else {
						//Integer fullValue = Integer.valueOf(builder.toString());
						//invalidnumbers.add(fullValue);
					}
					builder = new StringBuilder();
					valid = false;
					star = false;
					starLocation = new Location(0,0);
				}
				currentColumn++;
			}
			currentRow++;
		}
		
		int answer = 0;
		if (part2) {
			for (List<Integer> l : gears.values()) {
				if (l.size() == 2) {
					answer = answer + (l.get(0) * l.get(1));
				}
			}
		} else {
			for (Integer x : validnumbers) {
				answer = answer + x;
			}
		}
		return answer;
	}

	private static boolean touching(Location loc) {
		// NW
		Location nw = new Location(loc.getRow()-1, loc.getColumn()-1);
		if (data.containsKey(nw) && isValue(data.get(nw))) {
			return true;
		}
		// N
		Location n = new Location(loc.getRow()-1, loc.getColumn());
		if (data.containsKey(n) && isValue(data.get(n))) {
			return true;
		}
		// NE
		Location ne = new Location(loc.getRow()-1, loc.getColumn()+1);
		if (data.containsKey(ne) && isValue(data.get(ne))) {
			return true;
		}
		// E
		Location e = new Location(loc.getRow(), loc.getColumn()+1);
		if (data.containsKey(e) && isValue(data.get(e))) {
			return true;
		}
		// SE
		Location se = new Location(loc.getRow()+1, loc.getColumn()+1);
		if (data.containsKey(se) && isValue(data.get(se))) {
			return true;
		}
		// S
		Location s = new Location(loc.getRow()+1, loc.getColumn());
		if (data.containsKey(s) && isValue(data.get(s))) {
			return true;
		}
		// SW
		Location sw = new Location(loc.getRow()+1, loc.getColumn()-1);
		if (data.containsKey(sw) && isValue(data.get(sw))) {
			return true;
		}
		// W
		Location w = new Location(loc.getRow(), loc.getColumn()-1);
		if (data.containsKey(w) && isValue(data.get(w))) {
			return true;
		}
		return false;
	}
	
	private static Location touchingStar(Location loc) {
		// NW
		Location nw = new Location(loc.getRow()-1, loc.getColumn()-1);
		if (data.containsKey(nw) && isValueStar(data.get(nw))) {
			return nw;
		}
		// N
		Location n = new Location(loc.getRow()-1, loc.getColumn());
		if (data.containsKey(n) && isValueStar(data.get(n))) {
			return n;
		}
		// NE
		Location ne = new Location(loc.getRow()-1, loc.getColumn()+1);
		if (data.containsKey(ne) && isValueStar(data.get(ne))) {
			return ne;
		}
		// E
		Location e = new Location(loc.getRow(), loc.getColumn()+1);
		if (data.containsKey(e) && isValueStar(data.get(e))) {
			return e;
		}
		// SE
		Location se = new Location(loc.getRow()+1, loc.getColumn()+1);
		if (data.containsKey(se) && isValueStar(data.get(se))) {
			return se;
		}
		// S
		Location s = new Location(loc.getRow()+1, loc.getColumn());
		if (data.containsKey(s) && isValueStar(data.get(s))) {
			return s;
		}
		// SW
		Location sw = new Location(loc.getRow()+1, loc.getColumn()-1);
		if (data.containsKey(sw) && isValueStar(data.get(sw))) {
			return sw;
		}
		// W
		Location w = new Location(loc.getRow(), loc.getColumn()-1);
		if (data.containsKey(w) && isValueStar(data.get(w))) {
			return w;
		}
		return null;
	}

	private static boolean isValue(Character c) {
		boolean good = false;
		try {
			Integer.valueOf(c.toString());
		} catch (NumberFormatException e) {
			// Not a number...keep checking
			if (!c.equals(EMPTY)) {
				good = true;
			}
		}
		return good;
	}
	
	private static boolean isValueStar(Character c) {
		boolean good = false;
		try {
			Integer.valueOf(c.toString());
		} catch (NumberFormatException e) {
			// Not a number...keep checking
			if (c.equals(STAR)) {
				good = true;
			}
		}
		return good;
	}
	
	private static boolean isNumeric(Character c) {
		boolean good = true;
		try {
			Integer.valueOf(c.toString());
		} catch (NumberFormatException e) {
			good = false;
		}
		return good;
	}

}
