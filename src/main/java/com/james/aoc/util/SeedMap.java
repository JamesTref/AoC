package com.james.aoc.util;

public class SeedMap {
	
	private long sourceStart;
	private long range;
	
	public SeedMap(Long sourceStart, Long range) {
		this.sourceStart = sourceStart;
		this.range = range;
	}

	public long getSourceStart() {
		return sourceStart;
	}

	public long getRange() {
		return range;
	}

}
