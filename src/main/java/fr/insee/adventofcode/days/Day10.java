package fr.insee.adventofcode.days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.insee.adventofcode.utils.Utils;

public class Day10 extends Day {

    private static final Long[] puzzle = Utils.getLineLong("src/main/resources/10.txt");

    @Override
    public String part1() {
	List<Long> puzzleList = Arrays.asList(puzzle);
	puzzleList.sort(null);
	long nbJolt1 = 1;
	long nbJolt3 = 1;
	for (int i = 1 ; i < puzzleList.size() ; i++) {
	    if (puzzleList.get(i) - puzzleList.get(i-1) == 1) {
		nbJolt1++;
	    } else {
		nbJolt3++;
	    }
	}
	return String.valueOf(nbJolt1 * nbJolt3);
    }

    @Override
    public String part2() {
	List<Long> puzzleList = Arrays.asList(puzzle);
	puzzleList.sort(null);
        long[] nb = new long[puzzleList.size()];
        Map<Long,Long> nbArrangements = new HashMap<>();
        countArrangement(puzzleList.get(0) , puzzleList, nbArrangements);
        long result = 0;
        for(long j = 1; j <= 3; j++) {
            if(nbArrangements.containsKey(j)) {
                result += nbArrangements.get(j);
            }
        }
	return String.valueOf(result);
    }
    
    private long countArrangement(long jolt , List<Long> list, Map<Long,Long> nbArrangements) {
	long count = 0;
	long jolt1 = jolt + 1;
        long jolt2 = jolt + 2;
        long jolt3 = jolt + 3;
        if(list.contains(jolt1)) {
            if (nbArrangements.containsKey(jolt1)) {
        	count += nbArrangements.get(jolt1);
            } else {
        	count += countArrangement(jolt1,list, nbArrangements);
            }          
         }
        if(list.contains(jolt2)) {
            if (nbArrangements.containsKey(jolt2)) {
        	count += nbArrangements.get(jolt2);
            } else {
        	count += countArrangement(jolt2,list, nbArrangements);
            }          
         }
        if(list.contains(jolt3)) {
            if (nbArrangements.containsKey(jolt3)) {
        	count += nbArrangements.get(jolt3);
            } else {
        	count += countArrangement(jolt3,list, nbArrangements);
            }          
         }
        
        if(!list.contains(jolt1) && !list.contains(jolt2) && !list.contains(jolt3)) {
            return 1;
         }
        nbArrangements.put(jolt, count);
	return count;
    }
    

   
}
