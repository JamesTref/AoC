package com.james.aoc.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileConverter {

	public List<String> convertFileToArray(String filePath) {
		List<String> rows = new ArrayList<String>();
		try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {
			String line = bf.readLine();
	        while (line != null) {
	        	rows.add(line);
	            line = bf.readLine();
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rows;
	}

}
