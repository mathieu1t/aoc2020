package fr.insee.adventofcode.days;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import fr.insee.adventofcode.model.Orbite;
import fr.insee.adventofcode.utils.Utils;

public class Day06 extends Day {

	@Override
	public String part1(String filepath, Object... params) {
	    String[] puzzle = Utils.getLignesTab(filepath);
		List<Orbite> orbites = Arrays.stream(puzzle).map(x -> x.split("\\)")).map(y -> new Orbite(y[1],y[0])).collect(Collectors.toList());
		for (Orbite orb : orbites) {
			orb.setPrecedent(orbites.stream().filter(o -> o.getAxe().equals(orb.getObjet())).findFirst().orElse(Orbite.COM));
		}
		int somme = orbites.stream().mapToInt(x -> x.nombreOrbites()).sum();
		return String.valueOf(somme);
	}

	@Override
	public String part2(String filepath, Object... params) {
		String[] puzzle = Utils.getLignesTab(filepath);
		List<Orbite> orbites = Arrays.stream(puzzle).map(x -> x.split("\\)")).map(y -> new Orbite(y[1],y[0])).collect(Collectors.toList());
		for (Orbite orb : orbites) {
			orb.setPrecedent(orbites.stream().filter(o -> o.getAxe().equals(orb.getObjet())).findFirst().orElse(Orbite.COM));
		}
		List<Orbite> precedentsYou = orbites.stream().filter(o -> o.getAxe().equals("YOU")).findFirst().orElseThrow(NoSuchElementException::new).precedents();
		List<Orbite> precedentsSan = orbites.stream().filter(o -> o.getAxe().equals("SAN")).findFirst().orElseThrow(NoSuchElementException::new).precedents();
		Orbite commun = precedentsYou.stream().filter(x -> precedentsSan.contains(x)).findFirst().orElseThrow(NoSuchElementException::new);
		int nombre = precedentsSan.indexOf(commun) + precedentsYou.indexOf(commun);
		return String.valueOf(nombre);
	}

}
