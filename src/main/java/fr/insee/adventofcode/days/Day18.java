package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day18 extends Day {

    static List<String> keysCollected = new ArrayList<>();
    static List<String> listeKeys = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
    static List<String> listeDoors = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    @Override
    public String part1(String filepath, Object... params) {
	Maze maze = new Maze(filepath);
	for (Entry<String, Point> key : maze.keys.entrySet()) {
	    Point p = maze.findPath(key.getValue());
	    maze.position = p;
	    keysCollected.add(key.getKey());
	}
	return String.valueOf(maze.position.getStep());
    }

    @Override
    public String part2(String filepath, Object... params) {

	return String.valueOf(0);
    }

    enum Direction {
	UP(0, -1), LEFT(1, 0), DOWN(0, 1), RIGHT(-1, 0);
	int x;
	int y;

	Direction(int x, int y) {
	    this.x = x;
	    this.y = y;
	}
    }

    static class Maze {
	public int width;
	public int height;
	public Point position;
	public String[][] m;
	public Map<String, Point> keys = new HashMap<>();
	public Map<String, Point> doors = new HashMap<>();

	public Maze(String input) {
	    List<String> lignes = Utils.getLignes(input).collect(Collectors.toList());
	    width = lignes.get(0).length();
	    height = lignes.size();
	    m = new String[height][width];
	    for (int y = 0; y < height; y++) {
		for (int x = 0; x < width; x++) {
		    m[y][x] = lignes.get(y).substring(x, x + 1);
		    if ("@".equals(m[y][x])) {
			position = new Point(x, y, null);
		    }
		    if (listeKeys.contains(m[y][x])) {
			keys.put(m[y][x], new Point(x, y, null));
		    }
		    if (listeDoors.contains(m[y][x])) {
			doors.put(m[y][x], new Point(x, y, null));
		    }
		}
	    }
	}

	public Point findPath(Point end) {
	    Queue<Point> inProcess = new LinkedList<Point>();
	    Set<Point> inProcessQueue = new HashSet<Point>();
	    inProcess.add(position);
	    inProcessQueue.add(position);
	    Point current = null;
	    while ((current = inProcess.poll()) != null) {
		if (current.equals(end)) {
		    return current;
		} else {
		    List<Point> nextPoints = getNextPoints(current);
		    for (Point n : nextPoints) {
			inProcess.add(n);
			inProcessQueue.add(n);
		    }
		}
	    }
	    return null;

	}


	public List<Point> getNextPoints(Point current) {
	    List<Point> nextPoints = new ArrayList<>();
	    for (Direction dir : Direction.values()) {
		int nx = current.x + dir.x;
		int ny = current.y + dir.y;
		if (nx < width && ny < height && isValide(m[ny][nx])) {
		    nextPoints.add(new Point(nx, ny, current));
		}
	    }
	    return nextPoints;
	}

	private boolean isValide(String s) {
	    if (".".equals(s) || "@".equals(s))
		return true;
	    if (listeKeys.contains(s))
		return true;
	    if (listeDoors.contains(s) && keysCollected.contains(s.toLowerCase()))
		return true;
	    return false;
	}

    }

    static class Point {

	public int x;
	public int y;
	public Point previous;

	Point(int x, int y, Point previous) {
	    this.x = x;
	    this.y = y;
	    this.previous = previous;
	}

	public int getStep() {
	    Point previous = this.previous;
	    int step = 1;
	    while (previous != null) {
		step++;
	    }
	    return step;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    return "Point [x=" + x + ", y=" + y + ", previous=" + previous + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + x;
	    result = prime * result + y;
	    return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    Point other = (Point) obj;
	    if (x != other.x)
		return false;
	    if (y != other.y)
		return false;
	    return true;
	}

    }

}
