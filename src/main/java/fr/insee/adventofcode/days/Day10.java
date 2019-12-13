package fr.insee.adventofcode.days;

import java.util.List;
import java.util.stream.Collectors;

import fr.insee.adventofcode.model.Asteroid;
import fr.insee.adventofcode.model.Map;
import fr.insee.adventofcode.utils.Utils;

public class Day10 extends Day {

    static final List<String> puzzle = Utils.getLignes("src/main/resources/10.txt").collect(Collectors.toList());
    static Map map = new Map();

    @Override
    public String part1(String filepath, Object... params) {
	map.load(puzzle);
	int max = Integer.MIN_VALUE;
	for (Asteroid a : map.getAsteroids()) {
	    int nb = 0;
	    for (Asteroid b : map.getAsteroids()) {
		if (!a.equals(b) && isVisible(a,b)) {
		    nb++;
		}
	    }
	    if (nb > max) {
		max = nb;
	    }
	}
	return String.valueOf(max);
    }

    private boolean isVisible(Asteroid a, Asteroid b) {
	for (Asteroid test : map.getAsteroids()) {
	    if (!test.equals(a) && !test.equals(b) && isOnThePath(test, a, b)) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public String part2(String filepath, Object... params) {
	return String.valueOf(0);
    }

    public boolean isOnThePath(Asteroid test, Asteroid d1, Asteroid d2) {
	int x1 = d1.getX() < d2.getX() ? d1.getX() : d2.getX();
	int x2 = d1.getX() < d2.getX() ? d2.getX() : d1.getX();
	int y1 = d1.getY() < d2.getY() ? d1.getY() : d2.getY();
	int y2 = d1.getY() < d2.getY() ? d2.getY() : d1.getY();
	if (test.getX() > x2 || test.getX() < x1 || test.getY() > y2 || test.getY() < y1) {
	    return false;
	}
	if (d1.getX() == d2.getX()) {
	    if (test.getX() == d1.getX()) {
		return true;
	    } else {
		return false;
	    }
	}
	if (d1.getY() == d2.getY()) {
	    if (test.getY() == d1.getY()) {
		return true;
	    } else {
		return false;
	    }
	}
	double a = ((double)d2.getY() - (double)d1.getY()) / ((double)d2.getX() - (double)d1.getX());
	double b = (double)d1.getY() - (double)a * (double)d1.getX();
	if (((double)a * (double)test.getX()) - (double)test.getY() + (double)b == 0D) {
	    return true;
	} else {
	    return false;
	}
    }

}
