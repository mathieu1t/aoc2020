package fr.insee.adventofcode.days;

import fr.insee.adventofcode.utils.Utils;

public class Day05 extends Day{

	@Override
	public String part1(String filepath, Object... params) {
	    Integer[]  puzzle = Utils.getTabEntier(filepath, ",");
		int retour = intCode(puzzle, (int) params[0]);
		return String.valueOf(retour);
	}

	@Override
	public String part2(String filepath, Object... params) {
	    Integer[] puzzle = Utils.getTabEntier(filepath, ",");
		int retour = intCode(puzzle, (int) params[0]);
		return String.valueOf(retour);
	}

	private int intCode(Integer[] puzzle, int input) {
		int retour = 0;
		int i = 0;
		while (i < puzzle.length) {
			String digits = String.valueOf(puzzle[i]);
			if (digits.length() < 2) {
				digits = "0" + digits;
			}
			int nbModes = digits.length() - 2;
			Integer[] modes = new Integer[nbModes];
			for (int n = 0; n < nbModes; n++) {
				modes[nbModes - n - 1] = Character.getNumericValue(digits.charAt(n));
			}
			String code = digits.substring(nbModes);
			if ("99".equals(code)) {
				break;
			} else {
				switch (code) {
				case "01":
					puzzle[puzzle[i + 3]] = val(1, i, modes, puzzle) + val(2, i, modes, puzzle);
					i = i + 4;
					break;
				case "02":
					puzzle[puzzle[i + 3]] = val(1, i, modes, puzzle) * val(2, i, modes, puzzle);
					i = i + 4;
					break;
				case "03":
					puzzle[puzzle[i + 1]] = input; // input
					i = i + 2;
					break;
				case "04":
					retour = val(1, i, modes, puzzle); // output
					i = i + 2;
					break;
				case "05":
					if (val(1, i, modes, puzzle) != 0) {
						puzzle[i] = val(2, i, modes, puzzle);
						i = puzzle[i];
					} else {
						i = i + 3;
					}
					break;
				case "06":
					if (val(1, i, modes, puzzle) == 0) {
						puzzle[i] = val(2, i, modes, puzzle);
						i = puzzle[i];
					} else {
						i = i + 3;
					}
					break;
				case "07":
					puzzle[puzzle[i + 3]] = val(1, i, modes, puzzle) < val(2, i, modes, puzzle) ? 1 : 0;
					i = i + 4;
					break;
				case "08":
					puzzle[puzzle[i + 3]] = val(1, i, modes, puzzle) == val(2, i, modes, puzzle) ? 1 : 0;
					i = i + 4;
					break;
				}
			}
		}
		return retour;
	}

	public int val(int numParam, int position, Integer[] modes, Integer[] puzzle) {
		boolean mode = modes.length >= numParam && modes[numParam - 1] == 1;
		return puzzle[mode ? position + numParam : puzzle[position + numParam]];
	}

}
