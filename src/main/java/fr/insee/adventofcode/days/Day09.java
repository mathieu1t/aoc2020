package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import fr.insee.adventofcode.utils.Utils;

public class Day09 extends Day {

    private static final Long[] puzzle = Utils.getLineLong("src/main/resources/09.txt");

    @Override
    public String part1() {
	long result = 0;
	for (int i=25 ; i < puzzle.length; i++) {
	    Long[] precedent = Arrays.copyOfRange(puzzle, i-25, i);
	    if (!isSumOfPrecedent(precedent,puzzle[i])) {
		result = puzzle[i];
		break;
	    }
	}
	return String.valueOf(result);
    }

    private boolean isSumOfPrecedent(Long[] precedent, Long test) {
	for (Long l : precedent) {
	    Long l2 = test - l;
	    if (Arrays.asList(precedent).contains(l2)) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public String part2() {
	long nb = 25918798;
	long sum = 0;
	int i = 0;
	int depart = 0;
	List<Long> contigu = new ArrayList<>();
	    while(i<puzzle.length) {
		sum = sum + puzzle[i];
		if (sum<nb) {
		    contigu.add(puzzle[i]);
		} else if (sum > nb) {
		    sum=0;
		    depart++;
		    i = depart;
		    contigu = new ArrayList<>();
		} else {
		    break;
		}
		i++;
	    }
	    long max = contigu.stream().mapToLong(v -> v).max().orElseThrow(NoSuchElementException::new);
	    long min = contigu.stream().mapToLong(v -> v).min().orElseThrow(NoSuchElementException::new);
	return String.valueOf(min+max);
    }

}
