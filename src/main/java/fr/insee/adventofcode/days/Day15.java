package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day15 extends Day {

    private static final Long[] puzzle = { 1l, 20l, 8l ,12l,0l,14l};

    @Override
    public String part1() {
	List<Long> suite = Arrays.stream(puzzle).collect(Collectors.toList());
	int i = puzzle.length - 1;
	long newNumber = suite.get(i);
	while (i <= 2020) {
	    List<Integer> indexs = new ArrayList<>();
	    for (int j = 0; j < suite.size(); j++) {
		if (suite.get(j) == newNumber) {
		    indexs.add(j);
		}
	    }
	    if (indexs.size() == 1) {
		newNumber = 0;
	    } else {
		newNumber = indexs.get(indexs.size() - 1) - indexs.get(indexs.size() - 2);
	    }
	    suite.add(newNumber);
	    i++;
	}
	return String.valueOf(suite.get(2019));
    }

    @Override
    public String part2() {
	Map<Long, Long> suite = new HashMap<>();
	long  i = 0;
	long last = 0;
	for (Long l : puzzle) {
	    suite.put(l,i + 1); // on veut l'index du dernier nombre parlé donc la clé c'est le nombre
	    i++;
	    last = l;
	}

	while (i < 30000000) {	    
	    long next = 0;
	    if (suite.containsKey(last)) {
		next = i - suite.get(last);
	    }
	    suite.put(last, i);
	    i++;
	    last = next;	    
	}
	return String.valueOf(last);
    }

   

}
