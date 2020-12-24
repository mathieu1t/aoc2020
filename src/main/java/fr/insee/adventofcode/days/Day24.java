package fr.insee.adventofcode.days;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day24 extends Day {

    private static final List<String> puzzle = Utils.getLineStringList("src/main/resources/24.txt");
    private Map<double[], Integer> listHexagone = new HashMap<>();

    @Override
    public String part1() {

	for (String s : puzzle) {
	    double[] hexagone = new double[] { 0.0, 0.0 };
	    Deque<String> instructions = new ArrayDeque<>(Arrays.stream(s.split("")).collect(Collectors.toList()));
	    while (instructions.size() != 0) {
		String inst = instructions.pollFirst();
		Direction d = null;
		if ("n".equals(inst) || "s".equals(inst)) {
		    inst = inst + instructions.pollFirst();
		}
		d = Direction.valueOf(inst);
		hexagone[0] = hexagone[0] + d.x;
		hexagone[1] = hexagone[1] + d.y;
	    }
	    boolean isPresent = false;
	    for (double[] d : listHexagone.keySet()) {
		if (d[0] == hexagone[0] && d[1] == hexagone[1]) {
		    Integer bin = listHexagone.get(d) == 1 ? 0 : 1;
		    listHexagone.put(d, bin);
		    isPresent = true;
		    break;
		}
	    }
	    if (!isPresent) {
		listHexagone.put(hexagone, 1);
	    }
	}
	int count = listHexagone.values().stream().mapToInt(i -> i).sum();
	return String.valueOf(count);
    }

    enum Direction {
	e(0.0, 1.0), w(0.0, -1.0), nw(-0.5, -0.5), sw(0.5, -0.5), ne(-0.5, 0.5), se(0.5, 0.5);

	double x;
	double y;

	Direction(double d, double e2) {
	    this.x = d;
	    this.y = e2;
	}
    }

    @Override
    public String part2() {
	List<Coordinates> coordinates = new ArrayList<>();
	for (double[] d : listHexagone.keySet()) {
	    if (listHexagone.get(d) == 1) {
		coordinates.add(new Coordinates(d[0], d[1], true, null));
	    } else {
		coordinates.add(new Coordinates(d[0], d[1], false, null));
	    }
	}

	for (int n = 0; n < 100; n++) {
	    List<Coordinates> listToAdd = new ArrayList<>();
	    for (Coordinates c : coordinates) {
		List<Coordinates> neightbors = findNeightbors(c);
		for (Coordinates ne : neightbors) {
		    if (!coordinates.contains(ne) && !listToAdd.contains(ne)) {
			int count = countActiveNeightbors(coordinates, ne.x, ne.y);
			if (count == 2) {  // pas besoin d'ajouter un hexagone qui ne va pas être modifier
			    listToAdd.add(ne);
			}
		    }
		}
	    }
	    coordinates.addAll(listToAdd);
	    for (Coordinates c : coordinates) {
		int count = countActiveNeightbors(coordinates, c.x, c.y);
		if (c.active && (count == 0l || count > 2l)) {
		    c.activeInNext = false;
		}
		if (!c.active && count == 2l) {
		    c.activeInNext = true;
		}

	    }
	    for (Coordinates c : coordinates) {
		if (c.activeInNext != null && c.activeInNext) {
		    c.active = true;
		    c.activeInNext = null;
		}
		if (c.activeInNext != null && !c.activeInNext) {
		    c.active = false;
		    c.activeInNext = null;
		}
	    }
	}

	long count = coordinates.stream().filter(c -> c.active).count();
	return String.valueOf(count);
    }

    private Coordinates findCoordinates(List<Coordinates> coordinates, double x, double y) {
	return coordinates.stream().filter(c -> c.x == x && c.y == y).findFirst().orElse(null);
    }

    private Integer countActiveNeightbors(List<Coordinates> coordinates, double i, double j) {
	Direction[] directions = Direction.values();
	int countBlack = 0;
	for (Direction d : directions) {
	    double x = d.x + i;
	    double y = d.y + j;
	    Coordinates c = findCoordinates(coordinates, x, y);
	    if (c != null && c.active) {
		countBlack++;
	    }
	}
	return countBlack;
    }

    private List<Coordinates> findNeightbors(Coordinates c) {
	List<Coordinates> list = new ArrayList<>();
	Direction[] directions = Direction.values();
	for (Direction d : directions) {
	    double x = d.x + c.x;
	    double y = d.y + c.y;
	    Coordinates newC = new Coordinates(x, y, false, null);
	    list.add(newC);
	}
	return list;
    }

    class Coordinates {

	double x;
	double y;
	Boolean active;
	Boolean activeInNext;

	public Coordinates(double x, double y, Boolean active, Boolean activeInNext) {
	    this.x = x;
	    this.y = y;
	    this.active = active;
	    this.activeInNext = activeInNext;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    Coordinates other = (Coordinates) obj;
	    return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
	    return "Coordinates [x=" + x + ", y=" + y + ", active=" + active + ", activeInNext=" + activeInNext + "]";
	}

    }

}
