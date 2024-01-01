package com.james.aoc.year2023;

import org.apache.commons.math3.linear.*;

import java.util.ArrayList;
import java.util.List;

import com.james.aoc.util.Day24Equation;
import com.james.aoc.util.Day24Result;
import com.james.aoc.util.FileConverter;

public class Day24 {

	public static Long getAnswer(String filename, double rangeMin, double rangeMax, boolean part2) {
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filename);
		List<Day24Equation> equations = new ArrayList<>();
		int id = 1;
		for (String row : rows) {
			String[] coordinates = row.split("@")[0].split(",");
			String[] velocities = row.split("@")[1].split(",");
			double x = Double.valueOf(coordinates[0].trim());
			double y = Double.valueOf(coordinates[1].trim());
			double z = Double.valueOf(coordinates[2].trim());
			double vx = Double.valueOf(velocities[0].trim());
			double vy = Double.valueOf(velocities[1].trim());
			double vz = Double.valueOf(velocities[2].trim());
			Day24Equation data = new Day24Equation(id, x, y, z, vx, vy, vz);
			equations.add(data);
			id++;
		}

		long answer = 0;
		if (!part2) {
			int i = 1;
			List<Day24Result> answers = new ArrayList<>();
			for (Day24Equation equation1 : equations) {
				int j = i;
				while (j < equations.size()) {
					Day24Equation equation2 = equations.get(j);

					double[] x_coeff = new double[2];
					double[] y_coeff = new double[2];
					double[] eq = new double[2];
					x_coeff[0] = -(equation1.getM());
					x_coeff[1] = -(equation2.getM());
					y_coeff[0] = 1;
					y_coeff[1] = 1;
					eq[0] = equation1.getC();
					eq[1] = equation2.getC();
					Day24Result result = new Day24Result(equation1, equation2, solveSimultaneous(x_coeff, y_coeff, eq));
					answers.add(result);
					j++;
				}
				i++;
			}

			for (Day24Result result : answers) {
				if (result.crossInFuture()) {
					if (result.inRange(rangeMin, rangeMax, rangeMin, rangeMax)) {
						answer++;
					}
				}
			}
		} else {
			// Get first three stone values
			double x0 = equations.get(0).getX();
			double y0 = equations.get(0).getY();
			double z0 = equations.get(0).getZ();
			double vx0 = equations.get(0).getVx();
			double vy0 = equations.get(0).getVy();
			double vz0 = equations.get(0).getVz();

			double x1 = equations.get(1).getX();
			double y1 = equations.get(1).getY();
			double z1 = equations.get(1).getZ();
			double vx1 = equations.get(1).getVx();
			double vy1 = equations.get(1).getVy();
			double vz1 = equations.get(1).getVz();

			double x2 = equations.get(2).getX();
			double y2 = equations.get(2).getY();
			double z2 = equations.get(2).getZ();
			double vx2 = equations.get(2).getVx();
			double vy2 = equations.get(2).getVy();
			double vz2 = equations.get(2).getVz();

			// Build matrix - 6 unknowns, so need 6 equations...
			double[][] matrix = new double[6][6];
			matrix[0][1] = vz0 - vz1;
			matrix[0][2] = vy1 - vy0;
			matrix[0][4] = z1 - z0;
			matrix[0][5] = y0 - y1;

			matrix[1][0] = vz1 - vz0;
			matrix[1][2] = vx0 - vx1;
			matrix[1][3] = z0 - z1;
			matrix[1][5] = x1 - x0;

			matrix[2][0] = vy0 - vy1;
			matrix[2][1] = vx1 - vx0;
			matrix[2][3] = y1 - y0;
			matrix[2][4] = x0 - x1;

			matrix[3][1] = vz0 - vz2;
			matrix[3][2] = vy2 - vy0;
			matrix[3][4] = z2 - z0;
			matrix[3][5] = y0 - y2;

			matrix[4][0] = vz2 - vz0;
			matrix[4][2] = vx0 - vx2;
			matrix[4][3] = z0 - z2;
			matrix[4][5] = x2 - x0;

			matrix[5][0] = vy0 - vy2;
			matrix[5][1] = vx2 - vx0;
			matrix[5][3] = y2 - y0;
			matrix[5][4] = x0 - x2;

			// build results..
			double indepx0 = y0 * vz0 - vy0 * z0;
			double indepx1 = y1 * vz1 - vy1 * z1;
			double indepx2 = y2 * vz2 - vy2 * z2;

			double indepy0 = z0 * vx0 - vz0 * x0;
			double indepy1 = z1 * vx1 - vz1 * x1;
			double indepy2 = z2 * vx2 - vz2 * x2;

			double indepz0 = x0 * vy0 - vx0 * y0;
			double indepz1 = x1 * vy1 - vx1 * y1;
			double indepz2 = x2 * vy2 - vx2 * y2;
			
			double[] results = new double[6];
			results[0] = indepx0 - indepx1;
			results[1] = indepy0 - indepy1;
			results[2] = indepz0 - indepz1;
			results[3] = indepx0 - indepx2;
			results[4] = indepy0 - indepy2;
			results[5] = indepz0 - indepz2;

			RealMatrix coefficients = new Array2DRowRealMatrix(matrix);
			DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();

			RealVector constants = new ArrayRealVector(results, false);
			RealVector solution = solver.solve(constants);
			final double[] arraySolution = solution.toArray();
			//double xdAnswer = arraySolution[0];
			//double ydAnswer = arraySolution[1];
			//double zdAnswer = arraySolution[2];
			//double test = xdAnswer+ ydAnswer+ zdAnswer;
			long xAnswer = Math.round(arraySolution[0]);
			long yAnswer = Math.round(arraySolution[1]);
			long zAnswer = Math.round(arraySolution[2]);
			answer = xAnswer + yAnswer + zAnswer; //+1 due to rounding issues for answer 2
		}
		return answer;
	}

	public static double[] solveSimultaneous(double[] x_coeff, double[] y_coeff, double[] eq) {
		double[] x_coefficients = new double[] { x_coeff[0], x_coeff[1] };
		double[] y_coefficients = new double[] { y_coeff[0], y_coeff[1] };
		double[] equals = new double[] { eq[0], eq[1] };
		double[][] eliminator = new double[2][2];

		eliminator[0][0] = y_coefficients[1] * x_coefficients[0];
		eliminator[0][1] = y_coefficients[1] * equals[0];
		eliminator[1][0] = y_coefficients[0] * x_coefficients[1];
		eliminator[1][1] = y_coefficients[0] * equals[1];

		double x_variable;
		double y_variable;
		try {
			x_variable = (double) (eliminator[0][1] - eliminator[1][1]) / (eliminator[0][0] - eliminator[1][0]);
			y_variable = (double) (equals[0] - x_coefficients[0] * x_variable) / y_coefficients[0];
		} catch (ArithmeticException e) {
			throw e;
		}
		return new double[] { x_variable, y_variable };
	}

}
