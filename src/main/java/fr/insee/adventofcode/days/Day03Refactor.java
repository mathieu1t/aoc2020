package fr.insee.adventofcode.days;

import fr.insee.adventofcode.utils.Utils;

public class Day03Refactor extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/03.txt");
    private static final char TREE = '#';

    @Override
    public String part1() {
        return String.valueOf(slopes(3, 1));
    }

    @Override
    public String part2() {
        long s1 = slopes(1, 1);
        long s2 = slopes(3, 1);
        long s3 = slopes(5, 1);
        long s4 = slopes(7, 1);
        long s5 = slopes(1, 2);

        return String.valueOf(s1 * s2 * s3 * s4 * s5);
    }

    private long slopes(int right, int down) {
        long trees = 0;
        int column = 0;
        int maxLength = puzzle[0].length();
        for (int row = 0; row < puzzle.length; row += down) {
            if (puzzle[row].charAt(column) == TREE) {
                trees ++ ;
            }
            column += right;
            if (column >= maxLength) {
                column -= maxLength;
            }
        }
        return trees;
    }

}
