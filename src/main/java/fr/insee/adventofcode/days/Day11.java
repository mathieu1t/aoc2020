package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day11 extends Day {

    private static final String[][] puzzle = Utils.getLineString("src/main/resources/11.txt", "");

    @Override
    public String part1() {
	String[][] initPuzzle = getClonePuzzle(puzzle);
	String[][] newPuzzle = new String[puzzle.length][puzzle[0].length];
	boolean change;
	do {
	    change = false;
	    for (int i = 0; i < initPuzzle.length; i++) {
		String[] line = new String[initPuzzle[0].length];
		for (int j = 0; j < initPuzzle[0].length; j++) {
		    List<String> adjacents = findAdjacents(initPuzzle, i, j);
		    if (initPuzzle[i][j].equals("L")) {
			if (adjacents.stream().filter(a -> "#".equals(a)).count() == 0l) {
			    line[j] = "#";
			    change = true;
			} else {
			    line[j] = "L";
			}
		    } else if (initPuzzle[i][j].equals("#")) {
			if (adjacents.stream().filter(a -> "#".equals(a)).count() >= 4l) {
			    line[j] = "L";
			    change = true;
			} else {
			    line[j] = "#";
			}
		    } else {
			line[j] = ".";
		    }
		}
		newPuzzle[i] = line;
	    }
	    initPuzzle = getClonePuzzle(newPuzzle);
	    newPuzzle = new String[puzzle.length][puzzle[0].length];
	} while (change);
	long count = 0;
	for (int i = 0; i < initPuzzle.length; i++) {
	    for (int j = 0; j < initPuzzle[0].length; j++) {
		if (initPuzzle[i][j].equals("#")) {
		    count++;
		}
	    }
	}
	return String.valueOf(count);
    }

    private String[][] getClonePuzzle(String[][] puzzle) {
	String[][] clonePuzzle = new String[puzzle.length][puzzle[0].length];
	for (int i = 0; i < puzzle.length; i++) {
	    for (int j = 0; j < puzzle[0].length; j++) {
		clonePuzzle[i][j] = String.valueOf(puzzle[i][j]);
	    }
	}
	return clonePuzzle;
    }

    private List<String> findAdjacents(String[][] puzzle, int i, int j) {
	List<String> adjacents = new ArrayList<>();
	int width = puzzle[0].length - 1;
	int height = puzzle.length - 1;
	int widthMin = j - 1 < 0 ? 0 : j - 1;
	int widthMax = j + 1 > width ? width : j + 1;
	int heightMin = i - 1 < 0 ? 0 : i - 1;
	int heightMax = i + 1 > height ? height : i + 1;
	for (int h = heightMin; h <= heightMax; h++) {
	    for (int w = widthMin; w <= widthMax; w++) {
		if (i != h || j != w) {
		    adjacents.add(puzzle[h][w]);
		}
	    }
	}

	return adjacents;
    }

    @Override
    public String part2() {
	String[][] initPuzzle = getClonePuzzle(puzzle);
	String[][] newPuzzle = new String[puzzle.length][puzzle[0].length];
	boolean change;
	do {
	    change = false;
	    for (int i = 0; i < initPuzzle.length; i++) {
		String[] line = new String[initPuzzle[0].length];
		for (int j = 0; j < initPuzzle[0].length; j++) {
		    List<String> adjacents = findAdjacents2(initPuzzle, i, j);
		    long count = adjacents.stream().filter(a -> "#".equals(a)).count();
		    if (initPuzzle[i][j].equals("L")) {
			if (count == 0l) {
			    line[j] = "#";
			    change = true;
			} else {
			    line[j] = "L";
			}
		    } else if (initPuzzle[i][j].equals("#")) {
			if (count >= 5l) {
			    line[j] = "L";
			    change = true;
			} else {
			    line[j] = "#";
			}
		    } else {
			line[j] = ".";
		    }
		}
		newPuzzle[i] = line;
	    }
	    initPuzzle = getClonePuzzle(newPuzzle);
	    newPuzzle = new String[puzzle.length][puzzle[0].length];
	} while (change);
	long count = 0;
	for (int i = 0; i < initPuzzle.length; i++) {
	    for (int j = 0; j < initPuzzle[0].length; j++) {
		if (initPuzzle[i][j].equals("#")) {
		    count++;
		}
	    }
	}
	return String.valueOf(count);
    }

    private List<String> findAdjacents2(String[][] puzzle, int i, int j) {
	List<String> adjacents = new ArrayList<>();
	String left = findFirstSeatOnTheLeft(puzzle, i, j);
	if (left != null) {
	    adjacents.add(left);
	}
	String right = findFirstSeatOnTheRight(puzzle, i, j);
	if (right != null) {
	    adjacents.add(right);
	}
	
	String top = findFirstSeatOnTheTop(puzzle, i, j);
	if (top != null) {
	    adjacents.add(top);
	}
	String bottom = findFirstSeatOnTheBottom(puzzle, i, j);
	if (bottom != null) {
	    adjacents.add(bottom);
	}
	
	String leftTop = findFirstSeatOnTheLeftTop(puzzle, i, j);
	if (leftTop != null) {
	    adjacents.add(leftTop);
	}
	String rightTop = findFirstSeatOnTheRightTop(puzzle, i, j);
	if (rightTop != null) {
	    adjacents.add(rightTop);
	}
	
	String leftBottom = findFirstSeatOnTheLeftBottom(puzzle, i, j);
	if (leftBottom != null) {
	    adjacents.add(leftBottom);
	}
	String rightBottom = findFirstSeatOnTheRightBottom(puzzle, i, j);
	if (rightBottom != null) {
	    adjacents.add(rightBottom);
	}
	return adjacents;
    }

    private String findFirstSeatOnTheTop(String[][] puzzle, int i, int j) {
	int height = i - 1;
	while (height >= 0) {
		 if (!puzzle[height][j].equals(".")) {
			return puzzle[height][j];
		 } 
		 height--;
	}
	return null;
    }

    private String findFirstSeatOnTheBottom(String[][] puzzle, int i, int j) {
	int height = i + 1;
	while (height <= puzzle.length - 1) {
		 if (!puzzle[height][j].equals(".")) {
			return puzzle[height][j];
		 } 
		 height++;
	}
	return null;
    }

    private String findFirstSeatOnTheLeft(String[][] puzzle, int i, int j) {
	int width = j - 1;
	while (width >= 0) {
		 if (!puzzle[i][width].equals(".")) {
			return puzzle[i][width];
		 } 
		 width--;
	}
	return null;
    }

    private String findFirstSeatOnTheRight(String[][] puzzle, int i, int j) {
	int width = j + 1;
	while (width <= puzzle[0].length - 1) {
		 if (!puzzle[i][width].equals(".")) {
			return puzzle[i][width];
		 } 
		 width++;
	}
	return null;
    }
    
    private String findFirstSeatOnTheLeftTop(String[][] puzzle, int i, int j) {
	int width = j - 1;
	int height = i - 1;
	while (height >= 0 && width >= 0) {
		 if (!puzzle[height][width].equals(".")) {
			return puzzle[height][width];
		 } 
		 height--;
		 width--;
	}
	return null;
    }

    private String findFirstSeatOnTheRightTop(String[][] puzzle, int i, int j) {
	int width = j + 1;
	int height = i - 1;
	while (width <= puzzle[0].length - 1 && height >= 0) {
		 if (!puzzle[height][width].equals(".")) {
			return puzzle[height][width];
		 } 
		 height--;
		 width++;
	}
	return null;
    }
    
    private String findFirstSeatOnTheLeftBottom(String[][] puzzle, int i, int j) {
	int width = j - 1;
	int height = i + 1;
	while (height <= puzzle.length - 1 && width >= 0) {
		 if (!puzzle[height][width].equals(".")) {
			return puzzle[height][width];
		 } 
		 height++;
		 width--;
	}
	return null;
    }

    private String findFirstSeatOnTheRightBottom(String[][] puzzle, int i, int j) {
	int width = j + 1;
	int height = i + 1;
	while (height <= puzzle.length - 1 && width <= puzzle[0].length - 1) {
		 if (!puzzle[height][width].equals(".")) {
			return puzzle[height][width];
		 } 
		 height++;
		 width++;
	}
	return null;
    }

}
