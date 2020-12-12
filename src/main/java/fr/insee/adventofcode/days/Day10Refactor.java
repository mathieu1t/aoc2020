package fr.insee.adventofcode.days;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.insee.adventofcode.utils.Utils;

public class Day10Refactor extends Day {

    private static final List<Long> puzzle = Utils.getLineLongList("src/main/resources/10.txt");

    @Override
    public String part1() {
        puzzle.sort(null);
        long nbJolt1 = 1;
        long nbJolt3 = 1;
        for (int i = 1; i < puzzle.size(); i ++ ) {
            if (puzzle.get(i) - puzzle.get(i - 1) == 1) {
        	nbJolt1++ ;            
            } else {
        	nbJolt3++;
            }
        }
        return String.valueOf(nbJolt1 * nbJolt3);
    }

    @Override
    public String part2() {
        puzzle.sort(null);
        Map<Long, Long> nbArrangements = new HashMap<>();
        long count = countArrangement(0, puzzle, nbArrangements);
        return String.valueOf(count);
    }

    private long countArrangement(long jolt, List<Long> list, Map<Long, Long> nbArrangements) {
        long count = 0;
        for (long i = 1; i <= 3; i ++ ) {
            if (list.contains(jolt + i)) {
                if (nbArrangements.containsKey(jolt + i)) {
                    count += nbArrangements.get(jolt + i);
                }
                else {
                    count += countArrangement(jolt + i, list, nbArrangements);
                }
            }
        }

        if ( !list.contains(jolt + 1) && !list.contains(jolt + 2) && !list.contains(jolt + 3)) {
            return 1;
        }
        nbArrangements.put(jolt, count);
        return count;
    }
    

   
}
