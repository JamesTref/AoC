package com.james.aoc.util;

import java.util.ArrayList;
import java.util.List;

public class Brick {

	private int startx;
	private int starty;
	private int startz;
	private int endx;
	private int endy;
	private int endz;
	private List<Integer> supports;
	private List<Integer> supportedBy;
	private boolean dropped = false;

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	public Brick(int startx, int starty, int startz, int endx, int endy, int endz) {
		this.startx = Math.min(startx, endx);
		this.starty = Math.min(starty, endy);
		this.startz = Math.min(startz, endz);
		this.endx = Math.max(startx, endx);
		this.endy = Math.max(starty, endy);
		this.endz = Math.max(startz, endz);
		supportedBy = new ArrayList<Integer>();
		supports = new ArrayList<Integer>();
	}

	public int getStartx() {
		return startx;
	}

	public int getStarty() {
		return starty;
	}

	public int getStartz() {
		return startz;
	}

	public int getEndx() {
		return endx;
	}

	public int getEndy() {
		return endy;
	}

	public int getEndz() {
		return endz;
	}
	
	public void addSupportedBy(Integer index) {
		supportedBy.add(index);
	}

	public boolean isDropped() {
		return dropped;
	}

	public void drop() {
		this.startz = this.startz - 1;
		this.endz = this.endz - 1;
		this.dropped = true;
	}

	public boolean supports(Brick otherBrick) {
		if (otherBrick.startz - this.endz == 1) {
			//It could support...check x and y overlap, as this brick is on the row below
			boolean supportx = false;
			if (this.endx >= otherBrick.startx && this.startx <= otherBrick.endx) {
				supportx = true;
			}
			boolean supporty = false;
			if (this.endy >= otherBrick.starty && this.starty <= otherBrick.endy) {
				supporty = true;
			}
			if (supportx && supporty) {
				return true;
			}
		}
		return false;
	}
	
	public void createSupports(List<Brick> bricks, Integer index) {
		supports = new ArrayList<Integer>();
		for (Brick currentBrick : bricks) {
			if (!currentBrick.equals(this) ) {
				if (supports(currentBrick)) {
					supports.add(bricks.indexOf(currentBrick));
					currentBrick.addSupportedBy(index);
				}
			}
		}
	}
	
	public boolean canDestroy(List<Brick> bricks) {
		boolean canDestroy = true;
		for (Integer supportedBrick : supports) {
			boolean otherSupport = false;
			for (Brick currentBrick : bricks) {
				if (!currentBrick.equals(this) ) {
					if (currentBrick.supports.contains(supportedBrick)) {
						otherSupport = true;
						break;
					}
				}
			}
			if (!otherSupport) {
				canDestroy = false;
			}
		}
		return canDestroy;
	}

	@Override
	public String toString() {
		return "x: (" + getStartx() + "," + getEndx() + ")" + ", y: (" + getStarty() + "," + getEndy() + ")" + ", z: (" + getStartz() + "," + getEndz() + ")";
	}
	

}
