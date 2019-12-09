package fr.insee.adventofcode.days;

public abstract class Day<T> {
	
	protected T puzzle[];
	
	public abstract String part1(String filepath, Object... params);
	
	public abstract String part2(String filepath, Object... params);

}
