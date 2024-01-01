package com.james.aoc.util;

public class Day5Map {
	
	private long destStart;
	private long sourceStart;
	private long range;
	
	public Day5Map(Long destStart, Long sourceStart, Long range) {
		this.destStart = destStart;
		this.sourceStart = sourceStart;
		this.range = range;
	}
	
	public long mapValue(long sourceValue) {
		long destValue = sourceValue;
		if (sourceValue >= sourceStart && sourceValue < sourceStart + range) {
			destValue = destStart + (sourceValue - sourceStart);
		} 
		return destValue;
	}

	public long getDestStart() {
		return destStart;
	}

	public long getSourceStart() {
		return sourceStart;
	}

	public long getRange() {
		return range;
	}

}
