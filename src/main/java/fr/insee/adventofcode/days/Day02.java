package fr.insee.adventofcode.days;

import java.util.Arrays;

import fr.insee.adventofcode.utils.Utils;

public class Day02 extends Day<Integer> {

	@Override
	public String part1(String filepath, Object... params) {
		puzzle = Utils.getTabEntier(filepath,",");
		Integer[] puzzleAModifie = Arrays.copyOf(puzzle, puzzle.length);
		puzzleAModifie[1] = (int) params[0];
		puzzleAModifie[2] = (int) params[1];
		int retour = intCode(puzzleAModifie);
		return String.valueOf(retour);
	}

	@Override
	public String part2(String filepath, Object... params) {
		puzzle = Utils.getTabEntier(filepath,",");
		for(int x = 0; x < 100; x++) {
            for(int y = 0; y < 100; y++) {
            	Integer[] puzzleAModifie = Arrays.copyOf(puzzle, puzzle.length);
            	puzzleAModifie[1] = x;
        		puzzleAModifie[2] = y;
        		if (intCode(puzzleAModifie) == (int) params[0]) {
        			return String.valueOf(100 * x + y);
        		}
            }
		}
		return null;
	}

	private int intCode(Integer[] puzzle) {
		int i = 0;
		while (i < puzzle.length) {
			Integer code = puzzle[i];
			switch (code) {
			case 1:
				puzzle[puzzle[i + 3]] = puzzle[puzzle[i + 2]] + puzzle[puzzle[i + 1]];
				i = i + 4;
				break;
			case 2:
				puzzle[puzzle[i + 3]] = puzzle[puzzle[i + 2]] * puzzle[puzzle[i + 1]];
				i = i + 4;
				break;
			case 99:
				return puzzle[0];			
			}
		}
		return 0;
	}

}
