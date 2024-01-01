package com.james.aoc.year2023;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.james.aoc.util.Counter;
import com.james.aoc.util.FileConverter;
import com.james.aoc.util.PokerHand;

public class Day7 {
	
	public static boolean printStuff = false;
	
	public static String cardOrder = "J23456789TPQKA";

	//Wining hands, e.g. pair is [1,1,1,2], full house is [2,3]
	//Order from worst to best
	@SuppressWarnings("serial")
	public static List<String> winningHands = new ArrayList<String>() {
        {
            add("[1, 1, 1, 1, 1]"); //High card
            add("[1, 1, 1, 2]"); //pair
            add("[1, 2, 2]"); //two pairs
            add("[1, 1, 3]"); //three of a kind
            add("[2, 3]"); //full house
            add("[1, 4]"); //4 of a kind
            add("[5]"); //5 of a kind
        }
    };
	
	public static Integer getAnswer(String filename, boolean part2) {
		
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		List<PokerHand> pokerHands = new ArrayList<>();
		for (String row : rows) {
			if (printStuff) {
				System.out.println("------------");
			}
			//First parse hand and bid from row
			String hand = row.split(" ")[0];
			if (!part2) {
				hand= hand.replace('J', 'P');
			}
			Integer bid = Integer.valueOf(row.split(" ")[1]);
			if (printStuff) {
				System.out.println("Hand: " + hand);
				System.out.println("Bid: " + bid);
			}
			//Convert each card in hand to a ranking value, 2=0, 3=1 ... A = 12 etc.
			//Also count and store cards with same value
			Counter counter = new Counter();
			List<Integer> cardValues = new ArrayList<>();
			for (Character card : hand.toCharArray()) {
				cardValues.add(Day7.cardOrder.indexOf(card));
				counter.add(card);
			}	
			
			PokerHand pokerHand = new PokerHand(hand, bid, cardValues, winningHands.get(counter.getBestHandRank()), counter.getBestHandRank());
			pokerHands.add(pokerHand);
			if (printStuff) {
				System.out.println("Card Values: " + cardValues);
				System.out.println("Ordered Card Values: " + counter.getOrderedValues());
				System.out.println("Card Counts: " + counter.getCounts());
				System.out.println("Card Best Hand: " + pokerHand.getBestHand());
				System.out.println("Best Hand Ranking: " + pokerHand.getHandRank());
				System.out.println("------------");
			}
		}

		System.out.println(pokerHands.toString());
		
		Collections.sort(pokerHands, Comparator.comparing(PokerHand::getFullRank));
		int i = 1;
		int answer = 0;
		for (PokerHand pokerHand: pokerHands) {
			//System.out.println(pokerHand.getFullRank());
			//System.out.println("Rank " + i + ": " + pokerHand.toString());
			int score = i * pokerHand.getBid();
			answer = answer + score;
			System.out.println("score: " + score);
			i++;
		}
		System.out.println("------------");
		System.out.println("answer: " + answer);
		System.out.println("------------");
		return answer;
	}

}
