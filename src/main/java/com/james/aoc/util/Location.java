package com.james.aoc.util;

public class Location implements Comparable<Location>  {
	
	@Override
	public int compareTo(Location other) {
		if(this.getRow() > other.getRow()) {
            return 1;
        } else if (this.getRow() < other.getRow()) {
            return -1;
        } else if (this.getColumn() > other.getColumn()) {
            return 1;
        } else if (this.getColumn() < other.getColumn()) {
            return -1;
        } else {
            return 0;
        }
	}

	private Integer row;
	
	private Integer column;
	
	public Location(Integer row, Integer column) {
		this.row = row;
		this.column = column;
	}

	public Integer getRow() {
		return row;
	}

	public Integer getColumn() {
		return column;
	}

	@Override
	public int hashCode() {
		return row.hashCode() * column.hashCode();
	}
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + getRow() + "," + getColumn() + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
	        return true;
	    if (!(obj instanceof Location))
	        return false;
	    Location other = (Location) obj;
	    return (this.row.equals(other.getRow()) && this.column.equals(other.getColumn()));
	}
	
	

}
