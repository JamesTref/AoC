package com.james.aoc.util;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;


class FileConverterTest {

	@Test
	void test() {
		String filePath = "src/test/resources/Day1_2022/test.txt";
		FileConverter converter = new FileConverter();
		List<String> rows = converter.convertFileToArray(filePath);
		assertTrue(rows.size() == 14, "Size = " + rows.size());
	}

}
