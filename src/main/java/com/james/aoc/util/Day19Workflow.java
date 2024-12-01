package com.james.aoc.util;

public class Day19Workflow {

	private Day19PartType partType;
	private String compareType;
	private int compareValue; //< or >
	private String result; //New map or A or R
	
	public Day19Workflow(String partType, String compareType, int compareValue, String result) {
		switch (partType) {
		case "x":
			this.partType = Day19PartType.X;
			break;
		case "m":
			this.partType = Day19PartType.M;
			break;
		case "a":
			this.partType = Day19PartType.A;
			break;
		case "s":
			this.partType = Day19PartType.S;
			break;
		default:
			System.out.println("Bad part type....check code ....");
		}
		this.compareType = compareType;
		this.compareValue = compareValue;
		this.result = result;
	}

	public Day19PartType getPartType() {
		return partType;
	}

	public String getCompareType() {
		return compareType;
	}

	public int getCompareValue() {
		return compareValue;
	}

	public String getResult() {
		return result;
	}
	
	
	
}
