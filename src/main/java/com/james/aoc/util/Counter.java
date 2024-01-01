package com.james.aoc.util;

import java.util.ArrayList;

import java.util.Collections;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import java.util.TreeMap;

import com.james.aoc.year2023.Day7;

public class Counter {

	final Map<Character, Integer> counts = new HashMap<>();

	public void add(Character c) {

		counts.merge(c, 1, Integer::sum);

	}

	public Map<Character, Integer> getCounts() {

		return counts;

	}

	public Integer getBestHandRank() {

		List<Integer> ranks = new ArrayList<>();

		for (Character c : "23456789TQKA".toCharArray()) {

			Map<Character, Integer> mapCopy = copy(counts);

			if (mapCopy.containsKey('J')) {

				Integer count = mapCopy.get('J');

				mapCopy.remove('J');

				while (count > 0) {

					mapCopy.merge(c, 1, Integer::sum);

					count--;

				}

			}

			List<Integer> hand = new ArrayList<>();

			for (Integer i : mapCopy.values()) {

				hand.add(i);

			}

			Collections.sort(hand);

			Integer rank = Day7.winningHands.indexOf(hand.toString());

			ranks.add(rank);

		}

		Collections.sort(ranks);

		return ranks.get(ranks.size() - 1);

	}

	private Map<Character, Integer> copy(Map<Character, Integer> original)

	{

		Map<Character, Integer> copy = new HashMap<Character, Integer>();

		for (Map.Entry<Character, Integer> entry : original.entrySet())

		{

			copy.put(Character.valueOf(entry.getKey()),

// Or whatever List implementation you'd like here.

					Integer.valueOf(entry.getValue()));

		}

		return copy;

	}

//Sort card value list - need to ensure work out rank when have same winning hand

//Here lower rank is worst, need to have most common ordered first

//e.g. 3,3,4,4,8 should be ordered 4,4,3,3,8

//Then simple string order is good, e.g. 4,4,3,3,8 > 3,3,2,2,8

	public List<Integer> getOrderedValues() {

		Map<Integer, List<Integer>> flippedMap = new TreeMap<>(Collections.reverseOrder());

		for (Character c : counts.keySet()) {

			Integer count = counts.get(c);

			if (flippedMap.containsKey(count)) {

				flippedMap.get(count).add(Day7.cardOrder.indexOf(c));

			} else {

				List<Integer> values = new ArrayList<>();

				values.add(Day7.cardOrder.indexOf(c));

				flippedMap.put(count, values);

			}

		}

		List<Integer> handValuesOrdered = new ArrayList<>();

		for (Integer count : flippedMap.keySet()) {

			List<Integer> valueList = flippedMap.get(count);

			Collections.sort(valueList, Collections.reverseOrder());

			for (Integer value : valueList) {

				int i = count.intValue();

				while (i > 0) {

					handValuesOrdered.add(value);

					i--;

				}

			}

		}

		return handValuesOrdered;

	}

}