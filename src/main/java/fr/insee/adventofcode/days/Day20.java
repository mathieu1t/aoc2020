package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import fr.insee.adventofcode.utils.Utils;

public class Day20 extends Day {

    private static final List<String> puzzle = Utils.getLineStringList("src/main/resources/20.txt");

    @Override
    public String part1() {
	List<Tile> tiles = new ArrayList<>();
	Tile tile = new Tile();
	for (String line : puzzle) {
	    if (line.contains("Tile")) {
		tile = new Tile();
		tile.number = Long.parseLong(line.substring(5, line.indexOf(":")));
	    } else if (StringUtils.isBlank(line)) {
		tile.getBorders();
		tiles.add(tile);
	    } else {
		tile.lines.add(line);
	    }
	}
	tile.getBorders();
	tiles.add(tile);

	Map<Tile, List<Tile>> tilesMap = new HashMap<>();
	tiles.stream().forEach(t -> tilesMap.put(t, new ArrayList<>()));
	for (Tile t : tilesMap.keySet()) {
	    System.out.println(t.number);
	    List<Tile> list = tiles.stream().filter(f -> f.adjacent(t)).collect(Collectors.toList());
	    tilesMap.put(t, list);
	}
	
	long count = tilesMap.entrySet().stream().filter(e -> e.getValue().size() == 2).mapToLong(e -> e.getKey().number).reduce(1, (a, b) -> a * b);

	return String.valueOf(count);

    }

    @Override
    public String part2() {

	return String.valueOf("");
    }

    class Tile {
	long number;
	List<String> lines = new ArrayList<>();
	String top = "";
	String bottom = "";
	String left = "";
	String right = "";

	public void getBorders() {
	    this.top = lines.get(0);
	    this.bottom = lines.get(lines.size() - 1);
	    for (String line : lines) {
		left = left + line.charAt(0);
		right = right + line.charAt(line.length() - 1);
	    }
	}

	public List<Tile> getArrangements() {
	    List<Tile> arrangements = new ArrayList<>();
	    arrangements.add(this);
	    for (int i = 1; i <= 3; i++) {
		arrangements.add(rotation(i));
	    }
	    Tile tileX = returnX();
	    arrangements.add(tileX);
	    for (int i = 1; i <= 3; i++) {
		arrangements.add(tileX.rotation(i));
	    }
	    Tile tileY = returnY();
	    arrangements.add(tileY);
	    for (int i = 1; i <= 3; i++) {
		arrangements.add(tileY.rotation(i));
	    }
	    return arrangements;
	}

	private Tile returnX() {
	    Tile returnX = new Tile();
	    returnX.number = this.number;
	    returnX.right = (new StringBuffer(this.right)).reverse().toString();
	    returnX.bottom = this.top;
	    returnX.left = (new StringBuffer(this.left)).reverse().toString();
	    returnX.top = this.bottom;
	    return returnX;
	}

	private Tile returnY() {
	    Tile returnY = new Tile();
	    returnY.number = this.number;
	    returnY.right = this.left;
	    returnY.bottom = (new StringBuffer(this.bottom)).reverse().toString();
	    returnY.left = this.right;
	    returnY.top = (new StringBuffer(this.top)).reverse().toString();
	    return returnY;
	}

	public Tile rotation(int nbRotation) {
	    Tile tileRotation = new Tile();
	    tileRotation.number = this.number;
	    int i = 1;
	    while (i <= nbRotation) {
		tileRotation.right = this.top;
		tileRotation.bottom = (new StringBuffer(this.right)).reverse().toString();
		tileRotation.left = this.bottom;
		tileRotation.top = (new StringBuffer(this.left)).reverse().toString();
		i++;
	    }
	    return tileRotation;
	}

	public boolean adjacentLeft(Tile tile) {
	    if (this.number == tile.number) {
		return false;
	    }
	    for (Tile t : tile.getArrangements()) {
		for (Tile t1 : this.getArrangements()) {
		    if (t.left.equals(t1.right)) {
			return true;
		    }
		}
	    }
	    return false;
	}

	public boolean adjacentTop(Tile tile) {
	    if (this.number == tile.number) {
		return false;
	    }
	    for (Tile t : tile.getArrangements()) {
		for (Tile t1 : this.getArrangements()) {
		    if (t.top.equals(t1.bottom)) {
			return true;
		    }
		}
	    }
	    return false;
	}

	public boolean adjacentRight(Tile tile) {
	    if (this.number == tile.number) {
		return false;
	    }
	    for (Tile t : tile.getArrangements()) {
		for (Tile t1 : this.getArrangements()) {
		    if (t.right.equals(t1.left)) {
			return true;
		    }
		}
	    }
	    return false;
	}

	public boolean adjacentBottom(Tile tile) {
	    if (this.number == tile.number) {
		return false;
	    }
	    for (Tile t : tile.getArrangements()) {
		for (Tile t1 : this.getArrangements()) {
		    if (t.bottom.equals(t1.top)) {
			return true;
		    }
		}
	    }
	    return false;
	}

	public boolean adjacent(Tile tile) {
	    return adjacentBottom(tile) || adjacentTop(tile) || adjacentLeft(tile) || adjacentRight(tile);
	}
    }

}
