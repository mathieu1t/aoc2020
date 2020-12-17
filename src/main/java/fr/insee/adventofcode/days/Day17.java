package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fr.insee.adventofcode.utils.Utils;

public class Day17 extends Day {

    private static final String[][] puzzle = Utils.getLineString("src/main/resources/17.txt", "");

    // Pourquoi c'est plus long??

    @Override
    public String part1() {
	List<Coordinates> activeCoordinates = new ArrayList<>();

	for (int x = 0; x < puzzle.length; x++) {
	    for (int y = 0; y < puzzle.length; y++) {
		if ("#".equals(puzzle[x][y])) {
		    activeCoordinates.add(new Coordinates(x, y, 0));
		}
	    }
	}
	for (int n = 0; n < 6; n++) {
	    for (int x = -(puzzle.length + n) ; x < puzzle.length + n; x++) {
		for (int y = -(puzzle.length + n); y < puzzle.length + n; y++) {
		    for (int z = -(puzzle.length + n); z < puzzle.length + n; z++) {
			List<Coordinates> activeNeightbors = getActiveNeightbors(activeCoordinates, x, y, z);
			System.out.println(x +" " + y + " " + z + " : " +activeNeightbors);
			int active = activeNeightbors.size();
			Coordinates c = new Coordinates(x, y, z);
			if (activeCoordinates.contains(c) && active != 2 && active != 3) {
			    activeCoordinates.remove(c);
			}
			if (!activeCoordinates.contains(c) && active == 3) {
			    activeCoordinates.add(c);
			}
		    }
		}
	    }
	    System.out.println(activeCoordinates.size());
	}
	int count = activeCoordinates.size();

//	do {
//	    String[][][] newGrid = new String[50][50][50];
//	    
//	    for (int x = 0; x < grid.length; x++) {
//		for (int y = 0; y < grid[0].length; y++) {
//		    for (int z = 0; z < grid[0].length; z++) {
//		    newGrid[x][y] = changeSeat(grid, grid[x][y], x, y);
//		}
//	    }
//	    if (Arrays.deepEquals(grid, newGrid)) {
//		break;
//	    }
//	    grid = newGrid;
//	} while (true);
//	long count = 0;
//	for (int x = 0; x < grid.length; x++) {
//	    for (int y = 0; y < grid[0].length; y++) {
//		if ("#".equals(grid[x][y])) {
//		    count++;
//		}
//	    }
//	}
	return String.valueOf(count);
    }

    private List<Coordinates> getActiveNeightbors(List<Coordinates> activeCoordinates, int x, int y, int z) {
	List<Coordinates> active = new ArrayList<>();

	for (int i = -1; i <= 1; i++) {
	    for (int j = -1; j <= 1; j++) {
		for (int k = -1; k <= 1; k++) {
		    if (i == 0 && j == 0 && k == 0) {
			continue;
		    }
		    Coordinates c = new Coordinates(x + i, y + j, z + k);
		    if (activeCoordinates.contains(c)) {
			active.add(c);
		    }
		}
	    }
	}

	return active;
    }

    @Override
    public String part2() {

	return String.valueOf("");
    }

    class Coordinates {

	int x;
	int y;
	int z;

	public Coordinates(int x, int y, int z) {
	    this.x = x;
	    this.y = y;
	    this.z = z;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(x, y, z);
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
	    return x == other.x && y == other.y && z == other.z;
	}

	@Override
	public String toString() {
	    return "Coordinates [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	
    }

}
