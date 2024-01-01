package com.james.aoc.util;

public class Day24Equation {

	Integer rowId;
	double x;
	double y;
	double z;
	double vx;
	double vy;
	double vz;
	double m;
	double c;
	
	public Day24Equation(Integer rowId, double x, double y, double z, double vx, double vy, double vz) {
		this.rowId = rowId;
		this.x = x;
		this.y = y;
		this.z = z;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
		this.m = vy/vx;
		this.c = y - (m*x);
	}
	
	public Integer getRowId() {
		return rowId;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public double getVz() {
		return vz;
	}

	public double getM() {
		return m;
	}

	public double getC() {
		return c;
	}
	
	
}
