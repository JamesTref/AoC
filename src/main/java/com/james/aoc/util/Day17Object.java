package com.james.aoc.util;

import java.util.Objects;

public class Day17Object implements Comparable<Day17Object> {
	
	private int heatLoss;
	private int row;
	private int column;
	private int direction;
	
	
	public Day17Object(int heatLoss, int row, int column, int direction) {
		this.heatLoss = heatLoss;
		this.row = row;
		this.column = column;
		this.direction = direction;
	}


	public int getHeatLoss() {
		return heatLoss;
	}


	public int getRow() {
		return row;
	}


	public int getColumn() {
		return column;
	}


	public int getDirection() {
		return direction;
	}


	@Override
	public int hashCode() {
		return Objects.hash(column, direction, heatLoss, row);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Day17Object other = (Day17Object) obj;
		return column == other.column && direction == other.direction && heatLoss == other.heatLoss && row == other.row;
	}


	@Override
	public int compareTo(Day17Object other) {
		if(this.getHeatLoss() > other.getHeatLoss()) {
            return 1;
        } else if (this.getHeatLoss() < other.getHeatLoss()) {
            return -1;
        } else {
            return 0;
        }
	}

	@Override
	public String toString() {
		return "[(" + getRow() + "," + getColumn() + ")" + " heatLoss: " + getHeatLoss() + " direction: " + getDirection() + "]";
	}
	
}
