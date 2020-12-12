package fr.insee.adventofcode.days;

import java.util.List;

import fr.insee.adventofcode.utils.Utils;

public class Day01 extends Day {

    private static final List<Integer> puzzle = Utils.getLineIntegerList("src/main/resources/01.txt");

    private static final int SUM = 2020;

    @Override
    public String part1() {
	for (Integer i : puzzle) {
	    for (Integer j : puzzle) {
		if (i + j == SUM) {
		    return String.valueOf(i * j);
		}
	    }
	}
	return "";
    }

    @Override
    public String part2() {
	for (Integer i : puzzle) {
	    for (Integer j : puzzle) {
		for (Integer k : puzzle) {
		    if (i + j + k == SUM) {
			return String.valueOf(i * j * k);
		    }
		}
	    }
	}
	return "";
    }

}
