package com.james.aoc.year2022;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import com.james.aoc.util.FileConverter;

public class Day2_2022 {

	static int rock = 1;
	static int paper = 2;
	static int scissors = 3;
	static int loss = 0;
	static int draw = 3;
	static int win = 6;

	// A,X=Rock B,Y=Paper C,Z=Scissors
	static Map<String, Integer> scoreMap = Map.ofEntries(
			new AbstractMap.SimpleEntry<String, Integer>("A X", rock + draw),
			new AbstractMap.SimpleEntry<String, Integer>("A Y", paper + win),
			new AbstractMap.SimpleEntry<String, Integer>("A Z", scissors + loss),
			new AbstractMap.SimpleEntry<String, Integer>("B X", rock + loss),
			new AbstractMap.SimpleEntry<String, Integer>("B Y", paper + draw),
			new AbstractMap.SimpleEntry<String, Integer>("B Z", scissors + win),
			new AbstractMap.SimpleEntry<String, Integer>("C X", rock + win),
			new AbstractMap.SimpleEntry<String, Integer>("C Y", paper + loss),
			new AbstractMap.SimpleEntry<String, Integer>("C Z", scissors + draw));


	// A=Rock B=Paper C=Scissors X=loss Y=draw Z=win
	static Map<String, Integer> scoreMap2 = Map.ofEntries(
			new AbstractMap.SimpleEntry<String, Integer>("A X", scissors + loss),
			new AbstractMap.SimpleEntry<String, Integer>("A Y", rock + draw),
			new AbstractMap.SimpleEntry<String, Integer>("A Z", paper + win),
			new AbstractMap.SimpleEntry<String, Integer>("B X", rock + loss),
			new AbstractMap.SimpleEntry<String, Integer>("B Y", paper + draw),
			new AbstractMap.SimpleEntry<String, Integer>("B Z", scissors + win),
			new AbstractMap.SimpleEntry<String, Integer>("C X", paper + loss),
			new AbstractMap.SimpleEntry<String, Integer>("C Y", scissors + draw),
			new AbstractMap.SimpleEntry<String, Integer>("C Z", rock + win));
	
	public static Integer getAnswer(String filename) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		int score = 0;
		for (String row : rows) {
			score = score + scoreMap.get(row);
		}

		System.out.println(score);
		return score;
	}
	
	public static Integer getAnswer2(String filename) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		int score = 0;
		for (String row : rows) {
			score = score + scoreMap2.get(row);
		}

		System.out.println(score);
		return score;
	}

}
