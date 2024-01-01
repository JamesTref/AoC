package com.james.aoc.util;

import java.util.List;

public class Day23NodeLocation {
	
	private Location location;
	private int distance = 0;
	private List<Location> visited;
	
	public Day23NodeLocation(Location location, int distance, List<Location> visited) {
		this.location = location;
		this.distance = distance;
		this.visited = visited;
	}

	public List<Location> getVisited() {
		return visited;
	}

	public Location getLocation() {
		return location;
	}
	
	public int getDistance() {
		return distance;
	}
	

	@Override
	public String toString() {
		return "(" + location.getRow() + "," + location.getColumn() + "," + getDistance() + ")";
	}
}
