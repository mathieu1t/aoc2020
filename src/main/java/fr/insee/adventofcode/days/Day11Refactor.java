package fr.insee.adventofcode.days;

import java.util.Arrays;

import fr.insee.adventofcode.utils.Utils;

public class Day11Refactor extends Day {

    private static final String[][] puzzle = Utils.getLineString("src/main/resources/11.txt", "");

    // Pourquoi c'est plus long??

    @Override
    public String part1() {
	String[][] grid = puzzle.clone();
	do {
	    String[][] newGrid = new String[grid.length][grid[0].length];
	    for (int x = 0; x < grid.length; x++) {
		for (int y = 0; y < grid[0].length; y++) {
		    newGrid[x][y] = changeSeat(grid, grid[x][y], x, y);
		}
	    }
	    if (Arrays.deepEquals(grid, newGrid)) {
		break;
	    }
	    grid = newGrid;
	} while (true);
	long count = 0;
	for (int x = 0; x < grid.length; x++) {
	    for (int y = 0; y < grid[0].length; y++) {
		if ("#".equals(grid[x][y])) {
		    count++;
		}
	    }
	}
	return String.valueOf(count);
    }

    private String changeSeat(String[][] grid, String layout, int x, int y) {
	long countOccupied = countAdjacents(grid, x, y);
	if ("L".equals(layout) && countOccupied == 0l) {
	    return "#";
	} else if ("#".equals(layout) && countOccupied >= 4l) {
	    return "L";
	} else {
	    return layout;
	}
    }

    private long countAdjacents(String[][] grid, int x, int y) {
	long count = 0;
	for (int px = -1; px <= 1; px++) {
	    for (int py = -1; py <= 1; py++) {
		if (px == 0l && py == 0l) {
		    continue;
		}
		if (px + x < 0 || px + x > grid.length - 1) {
		    continue;
		}
		if (py + y < 0 || py + y > grid[0].length - 1) {
		    continue;
		}
		if ("#".equals(grid[px + x][py + y])) {
		    count++;
		}
	    }
	}
	return count;
    }

    @Override
    public String part2() {
	String[][] grid = puzzle.clone();
	do {
	    String[][] newGrid = new String[grid.length][grid[0].length];
	    for (int x = 0; x < grid.length; x++) {
		for (int y = 0; y < grid[0].length; y++) {
		    newGrid[x][y] = changeSeat2(grid, grid[x][y], x, y);
		}
	    }
	    if (Arrays.deepEquals(grid, newGrid)) {
		break;
	    }
	    grid = newGrid;
	} while (true);
	long count = 0;
	for (int x = 0; x < grid.length; x++) {
	    for (int y = 0; y < grid[0].length; y++) {
		if ("#".equals(grid[x][y])) {
		    count++;
		}
	    }
	}
	return String.valueOf(count);
    }

    public String changeSeat2(String[][] grid, String layout, int x, int y) {
	long countOccupied = countVisibles(grid, x, y);
	if ("L".equals(layout) && countOccupied == 0l) {
	    return "#";
	} else if ("#".equals(layout) && countOccupied >= 5l) {
	    return "L";
	} else {
	    return layout;
	}
    }

    private long countVisibles(String[][] grid, int x, int y) {
	long count = 0;
	for (int px = -1; px <= 1; px++) {
	    for (int py = -1; py <= 1; py++) {
		if (px == 0l && py == 0l) {
		    continue;
		}
		try {
		    String layout = grid[px + x][py + y];
		    if ("#".equals(layout)) {
			count++;
		    } else {
			int i = 1;
			layout = grid[px * i + x][py * i + y];
			while (".".equals(layout)) {			    
			    i++;
			    layout = grid[px * i + x][py * i + y];
			}
			 if ("#".equals(layout)) {
				count++;
			 }
		    }
		} catch (Exception e) {
		    continue;
		}
	    }
	}
	return count;
    }

}
