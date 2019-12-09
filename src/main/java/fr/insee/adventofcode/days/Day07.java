package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.insee.adventofcode.model.IntCode;
import fr.insee.adventofcode.utils.Utils;

public class Day07 extends Day<Integer> {

	@Override
	public String part1(String filepath, Object... params) {
		puzzle = Utils.getTabEntier(filepath, ",");
		Integer sauv[] = Arrays.copyOf(puzzle, puzzle.length);
		List<Integer> signals = new ArrayList<>();
		List<Integer[]> reglages = reglagesPossibles(0,4);
		for (Integer[] i : reglages) {
			int entree = 0;
			IntCode intCodeA = new IntCode(puzzle, i[0],entree);
			entree = intCodeA.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeB = new IntCode(puzzle, i[1],entree);
			entree = intCodeB.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeC = new IntCode(puzzle, i[2],entree);
			entree = intCodeC.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeD = new IntCode(puzzle, i[3],entree);
			entree = intCodeD.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeE = new IntCode(puzzle, i[4],entree);
			entree = intCodeE.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			signals.add(entree);
		}

		int retour = signals.stream().mapToInt(x -> x).max().orElse(0);
		return String.valueOf(retour);
	}

	@Override
	public String part2(String filepath, Object... params) {
		puzzle = Utils.getTabEntier(filepath, ",");
		Integer sauv[] = Arrays.copyOf(puzzle, puzzle.length);
		List<Integer> signals = new ArrayList<>();
		List<Integer[]> reglages = reglagesPossibles(5, 9);
		for (Integer[] i : reglages) {
			puzzle = Arrays.copyOf(puzzle, puzzle.length);
			int entree = 0;
			IntCode intCodeA = new IntCode(puzzle, i[0],entree);
			entree = intCodeA.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeB = new IntCode(puzzle, i[1],entree);
			entree = intCodeB.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeC = new IntCode(puzzle, i[2],entree);
			entree = intCodeC.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeD = new IntCode(puzzle, i[3],entree);
			entree = intCodeD.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeE = new IntCode(puzzle, i[4],entree);
			entree = intCodeE.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			while (!intCodeE.isStop()) {
				intCodeA = new IntCode(puzzle, i[0],entree);
				entree = intCodeA.lancer();
				puzzle = Arrays.copyOf(sauv, sauv.length);
				intCodeB = new IntCode(puzzle, i[1],entree);
				entree = intCodeB.lancer();
				puzzle = Arrays.copyOf(sauv, sauv.length);
				intCodeC = new IntCode(puzzle, i[2],entree);
				entree = intCodeC.lancer();
				puzzle = Arrays.copyOf(sauv, sauv.length);
				intCodeD = new IntCode(puzzle, i[3],entree);
				entree = intCodeD.lancer();
				puzzle = Arrays.copyOf(sauv, sauv.length);
				intCodeE = new IntCode(puzzle, i[4],entree);
				entree = intCodeE.lancer();
				puzzle = Arrays.copyOf(sauv, sauv.length);
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

}
