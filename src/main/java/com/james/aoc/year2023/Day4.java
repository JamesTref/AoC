package com.james.aoc.year2023;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import com.james.aoc.util.FileConverter;

public class Day4 {

	private static String GAME_SEP = ":";
	private static String CARD_SEP = "\\|";
	private static String NUM_SEP = " ";

	private static Map<Integer, HashMap<String, ArrayList<Integer>>> data = new HashMap<Integer, HashMap<String, ArrayList<Integer>>>();
	
	public static Integer getAnswer(String filename, boolean part2) throws Exception {
		data = new HashMap<Integer, HashMap<String, ArrayList<Integer>>>();
		// Sort Data
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		int totalcards = rows.size();
		for (String row : rows) {
			//First remove double spaces...
			row = row.replaceAll("   ", " ");
			row = row.replaceAll("  ", " ");
			
			String gamePart = row.split(GAME_SEP)[0];
			String dataPart = row.split(GAME_SEP)[1];
			
			//Split to find game number...
			Integer gameNumber = Integer.valueOf(gamePart.split("Card ")[1]);
			
			//Split to get win numbers...
			String winNumbers = dataPart.split(CARD_SEP)[0].trim();
			String[] winNumberArray = winNumbers.split(NUM_SEP);
			ArrayList<Integer> winNumbersList = new ArrayList<Integer>();
			
			for (String s : winNumberArray) {
				try {
					winNumbersList.add(Integer.parseInt(String.valueOf(s)));
				} catch (NumberFormatException e) {
					System.out.println("Win NFE: " + s);
				}
			}
			
			
			//Now My numbers...
			String myNumbers = dataPart.split(CARD_SEP)[1].trim();
			String[] myNumberArray = myNumbers.split(NUM_SEP);
			ArrayList<Integer> myNumbersList = new ArrayList<Integer>();
			
			for (String s : myNumberArray) {
				try {
					myNumbersList.add(Integer.parseInt(String.valueOf(s)));
				} catch (NumberFormatException e) {
					System.out.println("My NFE: " + s);
				}
			}
			
			//Add to map
			HashMap<String, ArrayList<Integer>> mainMap = new HashMap<String, ArrayList<Integer>>();
			mainMap.put("WIN", winNumbersList);
			mainMap.put("MY", myNumbersList);
			
			data.put(gameNumber, mainMap);
			
		}
		
		
		int answer = 0;
		if (!part2) {
			for (Integer gameNumber : data.keySet()) {
				HashMap<String, ArrayList<Integer>> mainMap = data.get(gameNumber);
				int cardScore = score(mainMap.get("WIN"), mainMap.get("MY"));
				answer = answer + cardScore;
			}
		} else {
			HashMap<Integer, ArrayList<Integer>> cards = new HashMap<Integer, ArrayList<Integer>>();
			for (Integer gameNumber : data.keySet()) {
				HashMap<String, ArrayList<Integer>> mainMap = data.get(gameNumber);
				ArrayList<Integer> cardsWon = score2(mainMap.get("WIN"), mainMap.get("MY"), gameNumber, totalcards);
				cards.put(gameNumber, cardsWon);
			}
			
			HashMap<Integer, Integer> cardsWon = new HashMap<Integer, Integer>();
			int cardCount = 1;
			while (cardCount <= totalcards) {
				if (cardsWon.containsKey(cardCount)) {
					cardsWon.put(cardCount, cardsWon.get(cardCount) + 1);
				} else {
					cardsWon.put(cardCount, 1);
				}
				
				int currentNoCards = cardsWon.get(cardCount);
				for (Integer card : cards.get(cardCount)) {
					if (cardsWon.containsKey(card)) {
						cardsWon.put(card, cardsWon.get(card) + currentNoCards);
					} else {
						cardsWon.put(card, currentNoCards);
					}
				}
				cardCount++;
			}

			for (Integer winnings : cardsWon.values()) {
				answer = answer + winnings;
			}

		}
		return answer;
	}

	private static int score(List<Integer> winners, List<Integer> mine) {
		int score = 0;
		for (Integer i : mine) {
			if (winners.contains(i)) {
				if (score == 0) {
					score = 1;
				} else {
					score = score * 2;
				}
			}
		}
		return score;
	}
	
	private static ArrayList<Integer> score2(List<Integer> winners, List<Integer> mine, int gameNumber, int totalcards) {
		int winCount = 0;
		for (Integer i : mine) {
			if (winners.contains(i)) {
				winCount++;
			}
		}
		
		ArrayList<Integer> cardsWon = new ArrayList<Integer>();
		while (winCount > 0) {
			if (gameNumber + winCount <= totalcards) {
				cardsWon.add(gameNumber + winCount);
				winCount--;
			}
		}
		
		return cardsWon;
	}
}
