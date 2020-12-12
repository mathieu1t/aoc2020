package fr.insee.adventofcode.days;

import java.util.List;

import fr.insee.adventofcode.utils.Utils;

public class Day01Refactor extends Day {

    private static final List<Integer> puzzle = Utils.getLineIntegerList("src/main/resources/01.txt");

    private static final int SUM = 2020;

    @Override
    public String part1() {
        long e = puzzle.stream().map(p -> SUM - p).filter(p -> puzzle.contains(p)).findFirst().orElse(0);
        return String.valueOf(e * (SUM - e));
    }

    @Override
    public String part2() {
        long result = 0;
        for (int i : puzzle) {
            long e = puzzle.stream().map(p -> SUM - i - p).filter(p -> puzzle.contains(p)).findFirst().orElse(0);
            if (e != 0) {
                result = e * (SUM - i - e) * i;
                break;
            }
        }
        return String.valueOf(result);
    }

}
