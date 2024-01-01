package com.james.aoc.util;

public class Day23MazeLocation {
	
	private int name;
	private Location location;
	private DirectionEnum travelDirection;
	private int stepCount = 0;
	private boolean[][] visited;
	
	public Day23MazeLocation(int name, Location location, DirectionEnum travelDirection, int stepCount, boolean[][] visited) {
		this.name = name;
		this.location = location;
		this.travelDirection = travelDirection;
		this.stepCount = stepCount;
		this.visited = visited;
	}

	public boolean[][] getVisited() {
		return visited;
	}

	public Location getLocation() {
		return location;
	}

	public DirectionEnum getTravelDirection() {
		return travelDirection;
	}
	
	public int getStepsTaken() {
		return stepCount;
	}
	
	public int getName() {
		return name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + ": (" + location.getRow() + "," + location.getColumn() + "," + getStepsTaken() + ")";
	}
}
