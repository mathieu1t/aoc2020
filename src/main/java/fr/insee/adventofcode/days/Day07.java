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
		Integer[] sauv = Arrays.copyOf(puzzle, puzzle.length);
		List<Integer> signals = new ArrayList<>();
		List<Integer[]> reglages = reglagesPossibles(0,4);
		for (Integer[] i : reglages) {
			int entree = 0;
			IntCode intCodeA = new IntCode(puzzle, i[0],entree, 0);
			entree = intCodeA.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeB = new IntCode(puzzle, i[1],entree, 0);
			entree = intCodeB.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeC = new IntCode(puzzle, i[2],entree, 0);
			entree = intCodeC.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeD = new IntCode(puzzle, i[3],entree, 0);
			entree = intCodeD.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeE = new IntCode(puzzle, i[4],entree, 0);
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
		List<Integer> signals = new ArrayList<>();
		List<Integer[]> reglages = reglagesPossibles(5, 9);
		for (Integer[] i : reglages) {	    
			int entree = 0;
			IntCode intCodeA = new IntCode(puzzle, i[0],entree, 0);
			int sortieA = intCodeA.lancer();
			IntCode intCodeB = new IntCode(puzzle, i[1],sortieA, intCodeA.getPosition());
			int sortieB = intCodeB.lancer();
			IntCode intCodeC = new IntCode(puzzle, i[2],sortieB, intCodeB.getPosition());
			int sortieC = intCodeC.lancer();
			IntCode intCodeD = new IntCode(puzzle, i[3],sortieC, intCodeC.getPosition());
			int sortieD = intCodeD.lancer();
			IntCode intCodeE = new IntCode(puzzle, i[4],sortieD, intCodeD.getPosition());
			int sortieE = intCodeE.lancer();
			while (!intCodeE.isStop()) {
				intCodeA = new IntCode(puzzle, null,sortieE, intCodeE.getPosition());
				sortieA = intCodeA.lancer();
				intCodeB = new IntCode(puzzle, null,sortieA, intCodeA.getPosition());
				sortieB = intCodeB.lancer();
				intCodeC = new IntCode(puzzle, null,sortieB, intCodeB.getPosition());
				sortieC = intCodeC.lancer();
				intCodeD = new IntCode(puzzle, null,sortieC, intCodeC.getPosition());
				sortieD = intCodeD.lancer();
				intCodeE = new IntCode(puzzle, null,sortieD, intCodeD.getPosition());
				sortieE = intCodeE.lancer();
			}
			signals.add(sortieD);
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
