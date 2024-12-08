package com.james.aoc.year2024;

import java.util.ArrayList;
import java.util.List;

import com.james.aoc.util.FileConverter;

public class Day7 {

	public static long getAnswer(String filename, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> input = converter.convertFileToArray(filename);

		long answer = 0;
        
		for (String s : input) {
            String[] data = s.split(": ");
            long target = Long.parseLong(data[0]);
            String numberList = data[1];
            String[] allNumbers = numberList.split(" ");
            List<Integer> allNumbersList = new ArrayList<>();
            for (String i : allNumbers) {
            	allNumbersList.add(Integer.parseInt(i));
            }
            
            List<String> allEquations = createAllEquations(allNumbersList, part2);
            for (String equation : allEquations) {
                if (calculate(equation) == target) {
                    answer += target;
                    break;
                }
            }
        }
		
		return answer;

	}
	
	//Make equations, format 'NNN + NNN * NNN + ...'
	private static List<String> createAllEquations(List<Integer> numbers, boolean part2) {
		List<String> operations = new ArrayList<>();
		operations.add("+");
		operations.add("*");
		if (part2) {
			operations.add("||");
		}

		List<String> equations = new ArrayList<>();
		equations.add(numbers.get(0).toString());
		numbers.remove(0);
		
		for (Integer number : numbers) {
			//System.out.println("********** " + number + " *********");
			List<String> newEquations = new ArrayList<>();
			for (String operation: operations ) {
				for (String currentEquation : equations) {
					String newEquation = currentEquation + " " + operation + " " + number.toString();
					//System.out.println(newEquation);
					newEquations.add(newEquation);
				}
			}
			equations = newEquations;
		}
		return equations;
	}

	private static long calculate(String equation) {
		String[] elements = equation.split(" ");
		long result = Long.parseLong(elements[0]);
		String operation = elements[1];

		for (int i = 2; i < elements.length; i++) {
			if (i % 2 == 0) {
				long newNumber = Long.parseLong(elements[i]);
				switch (operation) {
				case "+":
					result += newNumber;
					break;
				case "*":
					result *= newNumber;
					break;
				case "||":
					result = Long.parseLong(result + "" + newNumber);
					break;
				}
			} else {
				operation = elements[i];
			}
		}

		return result;
	}

}
