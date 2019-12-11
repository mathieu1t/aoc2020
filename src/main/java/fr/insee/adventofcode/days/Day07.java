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
		List<Integer[]> reglages = reglagesPossibles(0, 4);
		for (Integer[] i : reglages) {
			int entree = 0;
			IntCode intCodeA = new IntCode(puzzle, i[0], entree, 0);
			intCodeA.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeB = new IntCode(puzzle, i[1], intCodeA.getOutput(), 0);
			intCodeB.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeC = new IntCode(puzzle, i[2], intCodeB.getOutput(), 0);
			intCodeC.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeD = new IntCode(puzzle, i[3], intCodeC.getOutput(), 0);
			intCodeD.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			IntCode intCodeE = new IntCode(puzzle, i[4], intCodeD.getOutput(), 0);
			intCodeE.lancer();
			puzzle = Arrays.copyOf(sauv, sauv.length);
			signals.add(intCodeE.getOutput());
		}

		int retour = signals.stream().mapToInt(x -> x).max().orElse(0);
		return String.valueOf(retour);
	}

	@Override
	public String part2(String filepath, Object... params) {
		//puzzle = Utils.getTabEntier(filepath, ",");
	    	puzzle = new Integer[]{3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5};
		List<Integer> signals = new ArrayList<>();
		List<Integer[]> reglages = reglagesPossibles(5, 9);
		//for (Integer[] i : reglages) {
		Integer[] i = new Integer[] {9,8,7,6,5};
			Integer[] initialPuzzle = Arrays.copyOf(puzzle, puzzle.length);
			IntCode intCodeA = new IntCode(initialPuzzle, i[0], 0, 0);
			intCodeA.lancer();
			System.out.println(intCodeA.getOutput() + " " + intCodeA.getPosition());
			IntCode intCodeB = new IntCode(intCodeA.getTab(), i[1], intCodeA.getOutput(), 0);
			intCodeB.lancer();
			System.out.println(intCodeB.getOutput() + " " + intCodeB.getPosition());
			IntCode intCodeC = new IntCode(intCodeB.getTab(), i[2], intCodeB.getOutput(), 0);
			intCodeC.lancer();
			System.out.println(intCodeC.getOutput() + " " + intCodeC.getPosition());
			IntCode intCodeD = new IntCode(intCodeC.getTab(), i[3], intCodeC.getOutput(), 0);
			intCodeD.lancer();
			System.out.println(intCodeD.getOutput() + " " + intCodeC.getPosition());
			IntCode intCodeE = new IntCode(intCodeD.getTab(), i[4], intCodeD.getOutput(), 0);
			intCodeE.lancer();
			System.out.println(intCodeE.getOutput() + " " + intCodeE.getPosition());
			while (intCodeE.getHasOutput()) {
				intCodeA.setEntree1(intCodeE.getOutput());
				intCodeA.lancer();
				System.out.println(intCodeA.getOutput() + " " + intCodeA.getPosition());
				intCodeB.setEntree1(intCodeA.getOutput());
				intCodeB.lancer();
				System.out.println(intCodeB.getOutput() + " " + intCodeB.getPosition());
				intCodeC.setEntree1(intCodeB.getOutput());
				intCodeC.lancer();
				System.out.println(intCodeC.getOutput() + " " + intCodeC.getPosition());
				intCodeD.setEntree1(intCodeD.getOutput());
				intCodeD.lancer();
				System.out.println(intCodeD.getOutput() + " " + intCodeD.getPosition());
				intCodeE.setEntree1(intCodeD.getOutput());
				intCodeE.lancer();
				System.out.println(intCodeE.getOutput() + " " + intCodeE.getPosition());
			}
			signals.add(intCodeD.getOutput());
		//}

		//int retour = signals.stream().mapToInt(x -> x).max().orElse(0);
		return String.valueOf(0);
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
