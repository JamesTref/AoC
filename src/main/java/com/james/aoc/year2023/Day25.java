package com.james.aoc.year2023;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.james.aoc.util.Day25Link;
import com.james.aoc.util.FileConverter;

public class Day25 {

	public static Long getAnswer(String filename, boolean part2) throws Exception {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		
		Set<String> allNodes = new HashSet<>();
		Set<Day25Link> allLinks = new HashSet<>();
		Map<String, Set<String>> connections = new HashMap<>();
		
		for (String row : rows) {
			String node = row.split(":")[0];
			String[] targetNodes = row.split(":")[1].trim().split(" ");
			
			allNodes.add(node);
			for (String targetNode: targetNodes) {
				allNodes.add(targetNode);
				if (connections.containsKey(targetNode)) {
					connections.get(targetNode).add(node);
				} else {
					Set<String> newTargets = new HashSet<>();
					newTargets.add(node);
					connections.put(targetNode, newTargets);
				}
				if (connections.containsKey(node)) {
					connections.get(node).add(targetNode);
				} else {
					Set<String> newTargets = new HashSet<>();
					newTargets.add(targetNode);
					connections.put(node, newTargets);
				}
				allLinks.add(new Day25Link(node, targetNode));
			}
		}
		
		Set<List<Day25Link>> combinations = findAllCombinations(3, allLinks);

		long answer = 0;

		return answer;
	}
	
	public static Set<List<Day25Link>> findAllCombinations(int n, Set<Day25Link> links) {
	    List<Day25Link> linksList = new ArrayList<>(links);
	    ArrayList<Day25Link> resultList = new ArrayList<>();
	    Set<List<Day25Link>> result = new HashSet<>();
	    int carry;
	    int[] indices = new int[n];
	    do
	    {
	        for(int index : indices) {
	            resultList.add(linksList.get(index));
	        }
	        result.add(resultList);
	        resultList = new ArrayList<>();

	        carry = 1;
	        for(int i = indices.length - 1; i >= 0; i--)
	        {
	            if(carry == 0)
	                break;

	            indices[i] += carry;
	            carry = 0;

	            if(indices[i] == linksList.size())
	            {
	                carry = 1;
	                indices[i] = 0;
	            }
	        }
	    }
	    while(carry != 1);

	    return result;
	}

}
