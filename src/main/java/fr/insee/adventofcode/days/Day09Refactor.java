package fr.insee.adventofcode.days;

import java.util.List;

import fr.insee.adventofcode.utils.Utils;

public class Day09Refactor extends Day {

    private static final List<Long> puzzle = Utils.getLineLongList("src/main/resources/09.txt");

    @Override
    public String part1() {
	long result = puzzle.stream().filter(p -> puzzle.indexOf(p) >= 25)
		.filter(p -> !isSumOfPrecedent(puzzle.subList(puzzle.indexOf(p) - 25, puzzle.indexOf(p)), p))
		.findFirst().get();
	return String.valueOf(result);
    }

    private boolean isSumOfPrecedent(List<Long> precedent, Long test) {
	return precedent.stream().map(p -> test - p).anyMatch(p -> precedent.contains(p));
    }

    @Override
    public String part2() {
	long nb = 25918798;
	long sum = 0;
	long min = Long.MAX_VALUE;
	long max = Long.MIN_VALUE;
	int depart = 0;
	int i = 0;
	while (i < puzzle.size()) {	  
	    while (sum < nb) {
		sum = sum + puzzle.get(i);
		min = puzzle.get(i) < min ? puzzle.get(i) : min;
		max = puzzle.get(i) > max ? puzzle.get(i) : max;
		i++;
	    }
	    if (sum == nb) {
		break;
	    } else {
		sum = 0;
		i = ++depart;
		min = Long.MAX_VALUE;
		max = Long.MIN_VALUE;
	    }
	    i++;
	}
	return String.valueOf(max + min);
    }

}
