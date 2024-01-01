package com.james.aoc.util;

public class Day24Result {
	
	private Day24Equation equation1;
	private Day24Equation equation2;
	private double[] answer;
	private boolean crossInFuture = false;
	
	public Day24Result(Day24Equation equation1, Day24Equation equation2, double[] answer) {
		this.equation1 = equation1;
		this.equation2 = equation2;
		this.answer = answer;
		
		double timeWhenCross1 = (answer[0]-equation1.x)/equation1.vx;
		double timeWhenCross2 = (answer[0]-equation2.x)/equation2.vx;
		
		if (timeWhenCross1 >= 0 && timeWhenCross2 > 0) {
			crossInFuture = true;
		}
	}

	public Day24Equation getEquation1() {
		return equation1;
	}

	public Day24Equation getEquation2() {
		return equation2;
	}

	public double[] getAnswer() {
		return answer;
	}
	
	public boolean crossInFuture() {
		return crossInFuture;
	}
	
	public boolean inRange(double xmin, double xmax,double ymin, double ymax) {
		double x = answer[0];
		double y = answer[1];
		boolean inRange = true;
		if (x < xmin || x > xmax) {
			inRange = false;
		}
		if (y < ymin || y > ymax) {
			inRange = false;
		}
		return inRange;
	}
	
	
}
