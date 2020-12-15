package fr.insee.adventofcode.days;

import java.util.HashMap;
import java.util.Map;

public class Day15Refactor extends Day {

    private static final Long[] puzzle = {
        1l, 20l, 8l, 12l, 0l, 14l
    };

    @Override
    public String part1() {
        Map<Long, Long> suite = new HashMap<>();
        long i = 0;
        long last = 0;
        for (Long l : puzzle) {
            suite.put(l, i + 1); // on veut l'index du dernier nombre parlé donc la clé c'est le nombre
            i ++ ;
            last = l;
        }

        last = getNumber(2020, i, last, suite);
        return String.valueOf(last);
    }

    @Override
    public String part2() {
        Map<Long, Long> suite = new HashMap<>();
        long i = 0;
        long last = 0;
        for (Long l : puzzle) {
            suite.put(l, i + 1); // on veut l'index du dernier nombre parlé donc la clé c'est le nombre
            i ++ ;
            last = l;
        }

        last = getNumber(30000000, i, last, suite);
        return String.valueOf(last);
    }

    private long getNumber(long n, long i, long last, Map<Long, Long> suite) {
        while (i < n) {
            long next = 0;
            if (suite.containsKey(last)) {
                next = i - suite.get(last);
            }
            suite.put(last, i);
            i ++ ;
            last = next;
        }
        return last;
    }

}
