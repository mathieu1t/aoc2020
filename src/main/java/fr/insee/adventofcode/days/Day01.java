package fr.insee.adventofcode.days;

import java.util.Arrays;

import fr.insee.adventofcode.utils.Utils;

public class Day01 extends Day<Integer> {

	@Override
	public String part1(String filepath, Object... params) {
		puzzle = Utils.getLignesEntier(filepath);
		int carburant = Arrays.stream(puzzle).map((masse) -> calculBesoinCarburant(masse)).reduce(0, Integer::sum);
		return String.valueOf(carburant);
	}

	@Override
	public String part2(String filepath, Object... params) {
		puzzle = Utils.getLignesEntier(filepath);
		int carburant = Arrays.stream(puzzle).map((masse) -> calculBesoinTotalCarburant(masse)).reduce(0, Integer::sum);
		return String.valueOf(carburant);
	}
	
	private int calculBesoinCarburant(Integer masse) {
		return masse / 3 - 2;
	}
	
	private int calculBesoinTotalCarburant(Integer masse) {
		int carburant = calculBesoinCarburant(masse);
		if(carburant <= 0) return 0;
		return carburant + calculBesoinTotalCarburant(carburant);
	}


}
