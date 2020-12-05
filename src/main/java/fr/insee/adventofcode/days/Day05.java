package fr.insee.adventofcode.days;

import java.util.HashSet;
import java.util.Set;

import fr.insee.adventofcode.utils.Utils;

public class Day05 extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/05.txt");

    @Override
    public String part1() {
	int max = Integer.MIN_VALUE;
	for (String line : puzzle) {
	    String lineBinary = getBinary(line);
	    int id = getSeatId(lineBinary);
	    if (id > max) {
		max = id;
	    }
	}
	return String.valueOf(max);
    }

    @Override
    public String part2() {
	int myId = 0;
	Set<Integer> ids = new HashSet<>();
	for (int row = 0; row < 128; row++) {
            for (int column = 0; column < 8; column++) {
                ids.add(row * 8 + column);
            }
        }
	int min = Integer.MAX_VALUE;
	int max = Integer.MIN_VALUE;
        for (String line : puzzle) {
            String lineBinary = getBinary(line);
            int id = getSeatId(lineBinary);
	    if (id < min) min = id;
	    if (id > max) max = id;	    
            ids.remove(id);
        }
        
        for (int id : ids) {
            if (id > min && id < max) {
        	myId = id;
        	break;
            }
        }
	
	return String.valueOf(myId);
    }
    
    private String getBinary(String line) {
	line = line.replace('F', '0');
	line = line.replace('B', '1');
	line = line.replace('L', '0');
        line = line.replace('R', '1');
	return line;
    }
    
    private int getSeatId(String lineBinary) {
	String rowSpecify = lineBinary.substring(0, 7);
	String columnSpecify = lineBinary.substring(7, 10);
	int row = Integer.parseInt(rowSpecify,2);
	int column = Integer.parseInt(columnSpecify,2);
	return row * 8 + column;
    }
    
    

}
