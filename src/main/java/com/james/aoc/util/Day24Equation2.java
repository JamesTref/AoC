package com.james.aoc.util;

public class Day24Equation2 {

	Integer rowId;
	double x;
	double y;
	double z;
	double vx;
	double vy;
	double vz;
	double a;
	double b;
	double c;
	double k;
	double m;
	double n;
	
	public Day24Equation2(Integer rowId, double x, double y, double z, double vx, double vy, double vz) {
		this.rowId = rowId;
		this.x = x;
		this.y = y;
		this.z = z;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
		
		
		
		this.a = y - (m*x);
		this.b = y - (m*x);
		this.c = y - (m*x);
		this.k = vy/vx;
		this.m = vy/vx;
		this.n = vy/vx;
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

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public double getK() {
		return k;
	}

	public double getN() {
		return n;
	}
	
	
}
