package com.james.aoc.util;

public class Day18Plan {

	private char direction;
	private int distance;
	private String colour;
	
	
	public Day18Plan(char direction, int distance, String colour) {
		this.direction = direction;
		this.distance = distance;
		this.colour = colour.replace("(", "").replace(")", "");
	}

	public char getDirection() {
		return direction;
	}

	public int getDistance() {
		return distance;
	}

	public String getColour() {
		return colour;
	}
	
	public char getColourDirection() {
		char colourDirection = ' ';
		char lastChar = colour.charAt(colour.length()-1);
		switch (lastChar) {
		case '0':
			colourDirection = 'R';
			break;
		case '1':
			colourDirection = 'D';
			break;
		case '2':
			colourDirection = 'L';
			break;
		case '3':
			colourDirection = 'U';
			break;
		default:
			System.out.println("Errr...this is bad colour direction...");
		}
		return colourDirection;
	}
	
	public long getColourDistance() {
		String hexValue = colour.substring(1,6);
		return Long.parseLong(hexValue, 16);
	}
}
