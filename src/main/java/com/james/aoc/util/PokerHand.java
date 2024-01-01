package com.james.aoc.util;

import static java.util.Map.entry;

import java.util.List;
import java.util.Map;

public class PokerHand {
	
	private String hand;
	private Integer bid;
	private List<Integer> handValues;
	private String bestHand;
	private int handRank;
	
	private Map<Integer, String> convertMap = Map.ofEntries(
			entry(0, "a"),
			entry(1, "b"),
			entry(2, "c"),
			entry(3, "d"),
			entry(4, "e"),
			entry(5, "f"),
			entry(6, "g"),
			entry(7, "h"),
			entry(8, "i"),
			entry(9, "j"),
			entry(10, "k"),
			entry(11, "l"),
			entry(12, "n")
			);
	
	public PokerHand(String hand, Integer bid, List<Integer> handValues, String bestHand, int handRank) {
		this.hand = hand;
		this.bid = bid;
		this.handValues = handValues;
		this.bestHand = bestHand;
		this.handRank = handRank;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Hand: " + hand + " Bid: " + bid + " HandValues: " + handValues + " BestHand: " + bestHand + " HandRank: " + handRank + "]";
	}
	
	public List<Integer> getOrderedHandValues() {
		return handValues;
	}

	public String getHand() {
		return hand;
	}

	public Integer getBid() {
		return bid;
	}

	public String getBestHand() {
		return bestHand;
	}

	public int getHandRank() {
		return handRank;
	}
	
	public String getFullRank() {
		StringBuilder sb = new StringBuilder();
		sb.append(handRank);
		for (Integer i : handValues) {
			sb.append(convertMap.get(i));
		}
		return sb.toString();
	}
}
