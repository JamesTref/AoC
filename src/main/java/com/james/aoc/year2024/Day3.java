package com.james.aoc.year2024;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.james.aoc.util.FileConverter;

public class Day3 {

	private static final String DO = "do()";
    private static final String DONT = "don't()";
    private static final Pattern MULT_PATTERN = Pattern.compile("mul[(]([0-9]+),([0-9]+)[)]");
	
	public static long getAnswer(String filename, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		
		long sumOfMultiplication = 0;
        final StringBuilder fullInput = new StringBuilder();
		
		for (String row : rows) {
			fullInput.append(row);
		}
		sumOfMultiplication += extractAndMultiply(fullInput.toString());
        
		final long sumOfDoRuns = extractDoRuns(fullInput.toString(), true).stream().mapToLong(Day3::extractAndMultiply).sum();

		List<String> test = extractDoRuns(fullInput.toString(), true);
		
		
        System.out.println("Sum of mul operations: " + sumOfMultiplication);
        System.out.println("Sum of do runs: " + sumOfDoRuns);
		if (part2) {
			return sumOfDoRuns;
		} else {
			return sumOfMultiplication;
		}
		
		
	}
	
	private static long extractAndMultiply(final String string) {
        System.out.println("Extract & Multiply from " + string);
        long sumOfMultiplication = 0;
        final Matcher matcher = MULT_PATTERN.matcher(string);
        while (matcher.find()) {
            final long operand1 = Long.parseLong(matcher.group(1));
            final long operand2 = Long.parseLong(matcher.group(2));
            sumOfMultiplication += (operand1 * operand2);
        }
        return sumOfMultiplication;
    }

    private static List<String> extractDoRuns(final String string, boolean isDo) {
        final List<String> doRuns = new ArrayList<>();
        if (isDo) {
            final int nextDont = string.indexOf(DONT);
            if (nextDont == -1) {
                doRuns.add(string);
            } else {
                doRuns.add(string.substring(0, nextDont));
                doRuns.addAll(extractDoRuns(string.substring(nextDont + DONT.length()), false));
            }
            return doRuns;
        } else {
            final int nextDo = string.indexOf(DO);
            return extractDoRuns(string.substring(nextDo + DO.length()), true);
        }
    }
	

}
