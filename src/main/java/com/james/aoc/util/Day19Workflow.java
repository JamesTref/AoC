package com.james.aoc.util;

import java.util.Objects;

public class Day19Workflow {

	private String part;
	private String comparison; //< or >
	private String output; //New map or A or R
	
	public Day19Workflow(String part, String comparison, String output) {
		this.part = part;
		this.comparison = comparison;
		this.output = output;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comparison, output, part);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Day19Workflow other = (Day19Workflow) obj;
		return Objects.equals(comparison, other.comparison) && Objects.equals(output, other.output)
				&& Objects.equals(part, other.part);
	}

	public String getPart() {
		return part;
	}

	public String getComparison() {
		return comparison;
	}

	public String getOutput() {
		return output;
	}
	
}
