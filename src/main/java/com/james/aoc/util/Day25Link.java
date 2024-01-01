package com.james.aoc.util;

import java.util.Objects;

public class Day25Link {

	private String node1;
	private String node2;
	
	public Day25Link (String nodeA, String nodeB) throws Exception {
		if (nodeA.compareTo(nodeB) == 0) {
			//Bad Link
			throw new Exception("Bad what are you doing?");
		} else if (nodeA.compareTo(nodeB) < 0) {
			this.node1 = nodeA;
			this.node2 = nodeB;
		} else {
			this.node1 = nodeB;
			this.node2 = nodeA;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + node1 + ":" + node2 + ")";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(node1, node2);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Day25Link other = (Day25Link) obj;
		return Objects.equals(node1, other.node1) && Objects.equals(node2, other.node2);
	}
	
	
}
