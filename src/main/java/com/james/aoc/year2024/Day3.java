package com.james.aoc.year2024;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.james.aoc.util.FileConverter;

public class Day3 {

	private static final String DO = "do()";
	private static final String DO_NOT = "don't()";
	private static final Pattern MULT_PATTERN = Pattern.compile("mul[(]([0-9]{1,3}),([0-9]{1,3})[)]");

	public static long getAnswer(String filename, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);

		final StringBuilder input = new StringBuilder();
		for (String row : rows) {
			input.append(row);
		}

		long answer = 0;
		if (!part2) {
			answer += findAndMultiply(input.toString());
		} else {
			answer += findAndMultiply(findDoStrings(input.toString()));
		}

		return answer;

	}

	private static long findAndMultiply(final String string) {
		long answer = 0;
		Matcher matcher = MULT_PATTERN.matcher(string);
		while (matcher.find()) {
			long part1 = Long.parseLong(matcher.group(1));
			long part2 = Long.parseLong(matcher.group(2));
			answer += (part1 * part2);
		}
		return answer;
	}

	private static String findDoStrings(final String string) {
		StringBuilder doStrings = new StringBuilder();
		StringBuilder remainingString = new StringBuilder(string);
		boolean moreToDo = true;
		boolean useString = true; // starts true
		while (moreToDo) {
			if (useString) {
				int nextDont = remainingString.indexOf(DO_NOT);
				// No more 'Don't's so, at the end
				if (nextDont == -1) {
					doStrings.append(remainingString);
					moreToDo = false;
				} else {
					doStrings.append(remainingString, 0, nextDont);
					remainingString.delete(0, nextDont + DO_NOT.length());
				}
				useString = false;
			} else {
				int nextDo = remainingString.indexOf(DO);
				// No more 'Do's' so, at the end
				if (nextDo == -1) {
					moreToDo = false;
				} else {
					remainingString.delete(0, nextDo + DO.length());
				}
				useString = true;
			}
		}
		return doStrings.toString();
	}

}
