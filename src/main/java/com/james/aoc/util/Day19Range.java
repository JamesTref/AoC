package com.james.aoc.util;

public class Day19Range {
	
	long xMin;
	long xMax;
	long mMin;
	long mMax;
	long aMin;
	long aMax;
	long sMin;
	long sMax;
	String nextWorkflow;
	
	public Day19Range(long xMin, long xMax, long mMin, long mMax, long aMin, long aMax, long sMin ,long sMax, String nextWorkflow) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.mMin = mMin;
		this.mMax = mMax;
		this.aMin = aMin;
		this.aMax = aMax;
		this.sMin = sMin;
		this.sMax = sMax;
		this.nextWorkflow = nextWorkflow;
	}

	public long getxMin() {
		return xMin;
	}

	public void setxMin(long xMin) {
		this.xMin = xMin;
	}

	public long getxMax() {
		return xMax;
	}

	public void setxMax(long xMax) {
		this.xMax = xMax;
	}

	public long getmMin() {
		return mMin;
	}

	public void setmMin(long mMin) {
		this.mMin = mMin;
	}

	public long getmMax() {
		return mMax;
	}

	public void setmMax(long mMax) {
		this.mMax = mMax;
	}

	public long getaMin() {
		return aMin;
	}

	public void setaMin(long aMin) {
		this.aMin = aMin;
	}

	public long getaMax() {
		return aMax;
	}

	public void setaMax(long aMax) {
		this.aMax = aMax;
	}

	public long getsMin() {
		return sMin;
	}

	public void setsMin(long sMin) {
		this.sMin = sMin;
	}

	public long getsMax() {
		return sMax;
	}

	public void setsMax(long sMax) {
		this.sMax = sMax;
	}

	public String getNextWorkflow() {
		return nextWorkflow;
	}

	public void setNextWorkflow(String nextWorkflow) {
		this.nextWorkflow = nextWorkflow;
	}
	
	public long getCombinations() {
		return (xMax-xMin+1) * (mMax-mMin+1) * (aMax-aMin+1) * (sMax-sMin+1);
	}

}
