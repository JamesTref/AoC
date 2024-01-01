package com.james.aoc.util;

import java.util.Objects;

public class Day12Key {
	
	private Integer springPosition;
	private Integer patternPosition;
	private Integer blockLength;
	
	public Day12Key(Integer springPosition, Integer patternPosition, Integer blockLength) {
		this.springPosition = springPosition;
		this.patternPosition = patternPosition;
		this.blockLength = blockLength;
	}

	@Override
	public int hashCode() {
		return Objects.hash(blockLength, patternPosition, springPosition);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Day12Key other = (Day12Key) obj;
		return Objects.equals(blockLength, other.blockLength) && Objects.equals(patternPosition, other.patternPosition)
				&& Objects.equals(springPosition, other.springPosition);
	}

	@Override
	public String toString() {
		return "Day12Key [springPosition=" + springPosition + ", patternPosition=" + patternPosition + ", blockLength="
				+ blockLength + "]";
	}

	public Integer getSpringPosition() {
		return springPosition;
	}

	public Integer getPatternPosition() {
		return patternPosition;
	}

	public Integer getBlockLength() {
		return blockLength;
	}



}
