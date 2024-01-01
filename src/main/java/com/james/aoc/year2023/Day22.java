package com.james.aoc.year2023;

import java.util.ArrayList;
import java.util.List;

import com.james.aoc.util.Brick;
import com.james.aoc.util.FileConverter;

public class Day22 {

	public static Long getAnswer(String filename, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		List<Brick> bricks = new ArrayList<>();
		
		for (String row : rows) {
			String start = row.split("~")[0];
			String end = row.split("~")[1];
			String[] startArray = start.split(",");
			String[] endArray = end.split(",");
			Brick newBrick = new Brick(Integer.valueOf(startArray[0]), Integer.valueOf(startArray[1]), Integer.valueOf(startArray[2]), Integer.valueOf(endArray[0]), Integer.valueOf(endArray[1]), Integer.valueOf(endArray[2]));
			bricks.add(newBrick);
		}
		
		//Fall
		//System.out.println("***Start Fall***");
		boolean checkMoreToFall = true;		
		int count = 0;
		while (checkMoreToFall) {
			int fallCount = 0;
			for (Brick currentBrick : bricks) {
				fallCount = fallCount + fallCount(bricks, currentBrick);
			}
			if (fallCount == 0) {
				checkMoreToFall = false;
			}
			System.out.println("Interation: " + count + " Fallcount: " + fallCount);
			count++;
		}
		
		//Add supports
		for (Brick currentBrick : bricks) {
			currentBrick.createSupports(bricks, bricks.indexOf(currentBrick));
		}
		
		//Check if can destroy
		long answer = 0;	
		List<Brick> supportingBricks = new ArrayList<Brick>();
		for (Brick currentBrick : bricks) {
			if (currentBrick.canDestroy(bricks)) {
				answer++;
			} else {
				supportingBricks.add(currentBrick);
			}
		}
		
		if (part2) {
			answer = 0;
			for (Brick brick : supportingBricks) {
				List<Brick> clone = cloneRemovingBrick(bricks, brick);
				checkMoreToFall = true;	
				while (checkMoreToFall) {
					int fallCount = 0;
					for (Brick currentBrick : clone) {
						fallCount = fallCount + fallCount(clone, currentBrick);
					}
					if (fallCount == 0) {
						checkMoreToFall = false;
					}
				}
				
				//Count Dropped Bricks
				int droppedBrickCount = 0;
				for (Brick currentBrick : clone) {
					if (currentBrick.isDropped()) {
						droppedBrickCount++;
					}
				}
				
				answer = answer + droppedBrickCount;
				System.out.println("Brick: " + bricks.indexOf(brick) + " Brick Drop Count: " + droppedBrickCount + " Answer: " + answer);
			}
		}

		return answer;
	}
	
	private static List<Brick> cloneRemovingBrick(List<Brick> bricks, Brick brickToRemove) {
		List<Brick> clone = new ArrayList<Brick>();
		for (Brick brick : bricks) {
			if (!brick.equals(brickToRemove)) {
				Brick newBrick = new Brick(brick.getStartx(), brick.getStarty(), brick.getStartz(), brick.getEndx(), brick.getEndy(), brick.getEndz());
				clone.add(newBrick);
			}
		}
		return clone;
	}

	private static int fallCount(List<Brick> bricks, Brick currentBrick) {
		int fallCount = 0;
		if (currentBrick.getStartz() != 1) {
			boolean supported = false;
			for (Brick compareBrick : bricks) {
				if (!currentBrick.equals(compareBrick) && compareBrick.supports(currentBrick)) {
					supported = true;
				}
			}
			if (!supported) {
				currentBrick.drop();
				fallCount++;
			}
		}
		return fallCount;
	}


}
