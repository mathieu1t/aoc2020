package fr.insee.adventofcode.days;

import java.util.Arrays;

import fr.insee.adventofcode.utils.Utils;

public class Day01 extends Day {

    private static final Integer[] puzzle = Utils.getLineInteger("src/main/resources/01.txt");

    private static final int SUM = 2020;

    @Override
    public String part1() {
        long e = Arrays.stream(puzzle).map(p -> SUM - p).filter(p -> Arrays.asList(puzzle).contains(p)).findFirst().orElse(0);
        return String.valueOf(e * (SUM - e));
    }

    @Override
    public String part2() {
        long result = 0;
        for (int i : puzzle) {
            int e = Arrays.stream(puzzle).map(p -> SUM - i - p).filter(p -> Arrays.asList(puzzle).contains(p)).findFirst().orElse(0);
            if (e != 0) {
                result = e * (SUM - i - e) * i;
                break;
            }
        }
        return String.valueOf(result);
    }

}
