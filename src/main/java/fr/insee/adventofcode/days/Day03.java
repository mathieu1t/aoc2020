package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import fr.insee.adventofcode.model.Fil;
import fr.insee.adventofcode.model.Ligne;
import fr.insee.adventofcode.model.Point;
import fr.insee.adventofcode.utils.Utils;

public class Day03 extends Day {

	@Override
	public String part1(String filepath, Object... params) {
	    String[] puzzle = Utils.getTab(filepath)[0];
		Fil fil1 = new Fil(puzzle);
		puzzle = Utils.getTab(filepath)[1];
		Fil fil2 = new Fil(puzzle);
		List<Point> communs = getPointsCommuns(fil1,fil2);
		int min = communs.stream().mapToInt(p -> p.manhattan()).min().orElseThrow(NoSuchElementException::new);
		return String.valueOf(min);
	}

	@Override
	public String part2(String filepath, Object... params) {
	    String[] puzzle = Utils.getTab(filepath)[0];
		Fil fil1 = new Fil(puzzle);
		puzzle = Utils.getTab(filepath)[1];
		Fil fil2 = new Fil(puzzle);
		List<Point> communs = getPointsCommuns(fil1,fil2);
		int min = communs.stream().mapToInt(p -> distanceTotal(fil1,fil2,p)).min().orElseThrow(NoSuchElementException::new);
		return String.valueOf(min);
	}

	private List<Point> getPointsCommuns(Fil fil1, Fil fil2) {
		List<Point> retour = new ArrayList<>();	
		fil1.getLignes().stream().map(l -> l.listeDesPoints()).forEach(l1 -> {
			fil2.getLignes().stream().map(l -> l.listeDesPoints()).forEach(l2 -> {
				List<Point> communParLigne = l1.stream().filter(l2::contains).filter(p -> !p.equals(Point.centre)).collect(Collectors.toList());
				retour.addAll(communParLigne);
			});
		});
		return retour;
	}
	
	private int distanceTotal(Fil fil1, Fil fil2, Point p) {
		return distanceFil(fil1,p) + distanceFil(fil2,p);
	}

	private int distanceFil(Fil fil, Point p) {
		int distance = 0;
		for (Ligne l : fil.getLignes()) {
			List<Point> points = l.listeDesPoints();
			if (points.contains(p)) {
				return distance + p.distanceEntre(l.getDepart());
			} else {
				distance = distance + l.getDistance();
			}
		}
		return 0;
	}

}
