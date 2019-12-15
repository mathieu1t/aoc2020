package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
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
		if (!a.equals(b) && isVisible(a, b)) {
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
	map.load(puzzle);
	Asteroid station = new Asteroid(22, 25);
	Asteroid zero = new Asteroid(22, 0);
	List<Asteroid> autresAsteroids = new ArrayList<>(map.getAsteroids());
	autresAsteroids.remove(station);
	List<Asteroid> autresAsteroidVisible = autresAsteroids.stream().filter(a -> isVisible(station,a)).collect(Collectors.toList());
	HashMap<Asteroid, Double> angleStationAutre = new HashMap<>();
        for (Asteroid autre : autresAsteroidVisible) {
            double angle = calcRotationAngleInDegrees(station,autre);
         
        	 angleStationAutre.put(autre, angle);   
        }
        
        List<Asteroid> destroy = new ArrayList<>();
        LinkedHashMap<Asteroid, Double> angleStationAutreSorted = angleStationAutre.entrySet().stream().sorted(Entry.comparingByValue()).collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1,  e2) -> e1, LinkedHashMap::new));
        angleStationAutreSorted.entrySet().stream().forEach((x) -> {
	    autresAsteroids.remove(x.getKey());
	    destroy.add(x.getKey());
	});
        //       	Set<Double> keySet = angleStationAutre.keySet();
//       	double minPositif = keySet.stream().filter(x -> x >= 0).mapToDouble(x -> x).min().orElseThrow(null);
//       	double maxPositif = keySet.stream().filter(x -> x >= 0).mapToDouble(x -> x).max().orElseThrow(null);
//       	double minNegatif = keySet.stream().filter(x -> x < 0).mapToDouble(x -> x).min().orElseThrow(null);
//       	double maxNegatif = keySet.stream().filter(x -> x < 0).mapToDouble(x -> x).max().orElseThrow(null);
//       	SortedMap<Double, Asteroid> anglePositif = angleStationAutre.subMap(minPositif, maxPositif);
//	SortedMap<Double, Asteroid> angleNegatif = angleStationAutre.subMap(minNegatif, maxNegatif);
//	anglePositif.entrySet().stream().forEach((x) -> {
//	    autresAsteroids.remove(x.getValue());
//	    destroy.add(x.getValue());
//	});
//	angleNegatif.entrySet().stream().forEach((x) -> {
//	    autresAsteroids.remove(x.getValue());
//	    destroy.add(x.getValue());
//	});
	return String.valueOf(destroy.get(199).getX() *100 + destroy.get(199).getY());
    }

    public boolean isOnThePath(Asteroid test, Asteroid d1, Asteroid d2) {
	int x1 = d1.getX() < d2.getX() ? d1.getX() : d2.getX();
	int x2 = d1.getX() < d2.getX() ? d2.getX() : d1.getX();
	int y1 = d1.getY() < d2.getY() ? d1.getY() : d2.getY();
	int y2 = d1.getY() < d2.getY() ? d2.getY() : d1.getY();
	if (test.getX() > x2 || test.getX() < x1 || test.getY() > y2 || test.getY() < y1) {
	    return false;
	}
	double ang = angle(d1, test, d2);
	if (ang == 0d) {
	    return true;
	}
	return false;
    }

    public double angle(Asteroid a, Asteroid test, Asteroid b) {
	double P1X = (double) a.getX();
	double P1Y = (double) a.getY();
	double P2X = (double) test.getX();
	double P2Y = (double) test.getY();
	double P3X = (double) b.getX();
	double P3Y = (double) b.getY();
	double numerator = P2Y * (P1X - P3X) + P1Y * (P3X - P2X) + P3Y * (P2X - P1X);
	double denominator = (P2X - P1X) * (P1X - P3X) + (P2Y - P1Y) * (P1Y - P3Y);
	double ratio = numerator / denominator;

	double angleRad = Math.atan(ratio);
	double angleDeg = (angleRad * 180) / Math.PI;

	if (angleDeg < 0) {
	    angleDeg = 180 + angleDeg;
	}

	return angleDeg;
    }
    
    private double calcRotationAngleInDegrees(Asteroid centerPt, Asteroid targetPt) {
	    double theta = Math.atan2(targetPt.getY() - centerPt.getY(), targetPt.getX() - centerPt.getX()) + Math.PI/2.0;
	    double angle = Math.toDegrees(theta);
	    return angle < 0 ? angle + 360 : angle;
	}

}
