package com.james.aoc.year2023;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.james.aoc.util.FileConverter;

public class Day8 {

	public static Long getAnswer(String filename, boolean part2) {
		String instructions = "";
		String currentNode = "AAA";
		Map<String, Long> part2StartNodes = new HashMap<>();
		Map<String, Long> part2EndNodes = new HashMap<>();
		String targetNode = "ZZZ";
		Map<String, HashMap<Character, String>> data = new HashMap<String, HashMap<Character, String>>();

		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);

		int rowNum = 1;
		for (String row : rows) {
			if (rowNum == 1) {
				instructions = row;
			} else if (!row.isBlank()) {
				row = row.replace("(", "").replace(")", "");
				String start = row.split("=")[0].trim();
				String left = row.split("=")[1].split(",")[0].trim();
				String right = row.split("=")[1].split(",")[1].trim();
				HashMap<Character, String> lrMap = new HashMap<>();
				lrMap.put('L', left);
				lrMap.put('R', right);
				data.put(start, lrMap);
				if (start.endsWith("A")) {
					part2StartNodes.put(start, null);
				}
				if (start.endsWith("Z")) {
					part2EndNodes.put(start, null);
				}
			}
			rowNum++;
		}
		long answer = 0;
		if (!part2) {
			answer = solve1(instructions, currentNode, targetNode, data, answer);
		} else {
			for (String node : part2StartNodes.keySet()) {
				answer = solve2(instructions, node, data);
				part2StartNodes.put(node, answer);
			}
			
			/*for (String node : part2EndNodes.keySet()) {
				answer = solve2(instructions, node, data);
				part2EndNodes.put(node, answer);
			}*/
			
			/*for (String node : part2StartNodes.keySet()) {
				System.out.println(node + ": " + part2StartNodes.get(node));
			}
			
			for (String node : part2EndNodes.keySet()) {
				System.out.println(node + ": " + part2EndNodes.get(node));
			}*/
			
			answer = 1;
			for (String node : part2StartNodes.keySet()) {
				long other = answer;
				answer = lcm(answer, part2StartNodes.get(node));
				System.out.println(other + "," + part2StartNodes.get(node) + ": " + answer);
			}
		}

		return answer;
	}
	
	public static long lcm(long number1, long number2) {
	    if (number1 == 0 || number2 == 0)
	        return 0;
	    else {
	        long gcd = gcd(number1, number2);
	        return Math.abs(number1 * number2) / gcd;
	    }
	}

	public static long gcd(long number1, long number2) {
	    if (number1 == 0 || number2 == 0) {
	        return number1 + number2;
	    } else {
	    	long absNumber1 = Math.abs(number1);
	    	long absNumber2 = Math.abs(number2);
	    	long biggerValue = Math.max(absNumber1, absNumber2);
	    	long smallerValue = Math.min(absNumber1, absNumber2);
	        return gcd(biggerValue % smallerValue, smallerValue);
	    }
	}

	private static long solve1(String instructions, String currentNode, String targetNode,
			Map<String, HashMap<Character, String>> data, long answer) {
		boolean ended = false;
		if (currentNode.equals(targetNode)) {
			ended = true;
		}
		while(!ended) {
			for (Character c : instructions.toCharArray() ) {
				currentNode = data.get(currentNode).get(c);
				answer++;
				if (currentNode.equals(targetNode)) {
					ended = true;
					break;
				}
			}
		}
		return answer;
	}
	
	private static long solve2(String instructions, String currentNode,
			Map<String, HashMap<Character, String>> data) {
		long answer = 0;
		boolean ended = false;
		String start = currentNode;
		while(!ended) {
			int i = 0;
			for (Character c : instructions.toCharArray() ) {
				currentNode = data.get(currentNode).get(c);
				answer++;
				if (currentNode.endsWith("Z")) {
					ended = true;
					if (i > instructions.length()-1) {
						i = 0;
					}
					Character nextC = instructions.toCharArray()[i];
					System.out.println(start + " (First)=> " + data.get(start).get(instructions.toCharArray()[0]) + " (lots of maps)=> "+ currentNode + " after " + answer + " steps. Next Node: " + data.get(currentNode).get(nextC) + " (" + nextC + ")");
					break;
				}
				i++;
				
			}
		}
		return answer;
	}

}
