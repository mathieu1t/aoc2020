package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.insee.adventofcode.utils.Utils;

public class Day07 extends Day<Integer> {

    private boolean sortie;

    @Override
    public String part1(String filepath, Object... params) {
	puzzle = Utils.getTabEntier(filepath, ",");
	Integer sauv[] = Arrays.copyOf(puzzle, puzzle.length);
	List<Integer> signals = new ArrayList<>();
	List<Integer[]> reglages = reglagesPossibles(0, 4);
	for (Integer[] i : reglages) {
	    int entree = 0;
	    for (Integer j : i) {
		puzzle[puzzle[1]] = j;
		entree = intCode(puzzle, entree);
		puzzle = Arrays.copyOf(sauv, sauv.length);
	    }
	    signals.add(entree);
	}

	int retour = signals.stream().mapToInt(x -> x).max().orElse(0);
	return String.valueOf(retour);
    }

    @Override
    public String part2(String filepath, Object... params) {
	// puzzle = Utils.getTabEntier(filepath, ",");
	puzzle = new Integer[] { 3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26, 27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5 };
	Integer sauv[] = Arrays.copyOf(puzzle, puzzle.length);
	List<Integer> signals = new ArrayList<>();
	List<Integer[]> reglages = reglagesPossibles(5, 9);
	for (Integer[] i : reglages) {
	    int entree = 0;
	    boolean boucle = true;
	    sortie = false;
	    while (boucle) {
		for (Integer j = 0; j <= 4; j++) {
		    puzzle[puzzle[1]] = i[j];
		    entree = intCode(puzzle, entree);
		    System.out.println(entree);
		    puzzle = Arrays.copyOf(sauv, sauv.length);
		    if (j == 4 && sortie) {
			boucle = false;
		    }
		}
	    }
	    signals.add(entree);
	}

	int retour = signals.stream().mapToInt(x -> x).max().orElse(0);
	return String.valueOf(retour);
    }

    private List<Integer[]> reglagesPossibles(int debut, int fin) {
	List<Integer[]> reglages = new ArrayList<>();
	for (int a = debut; a <= fin; a++) {
	    for (int b = debut; b <= fin; b++) {
		if (b != a) {
		    for (int c = debut; c <= fin; c++) {
			if (c != a && c != b) {
			    for (int d = debut; d <= fin; d++) {
				if (d != c && d != b && d != a) {
				    for (int e = debut; e <= fin; e++) {
					if (e != d && e != c && e != b && e != a) {
					    reglages.add(new Integer[] { a, b, c, d, e });
					}
				    }
				}
			    }
			}
		    }
		}
	    }

	}
	return reglages;
    }

    private int intCode(Integer[] puzzle, int input) {
	int retour = 0;
	int i = 2;
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
		sortie = true;
		return retour;
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
		    return val(1, i, modes, puzzle); // output
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
