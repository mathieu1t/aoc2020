package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.TreeMap;
import java.util.stream.Collectors;

import fr.insee.adventofcode.model.Rule;
import fr.insee.adventofcode.utils.Utils;

public class Day08 extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/08.txt");

    @Override
    public String part1() {
	List<Integer> instructionPassed = new ArrayList<>();
	int acc = 0;
	int i = 0;
	while (!instructionPassed.contains(i)) {
	    instructionPassed.add(i);
	    String instruction = puzzle[i];
	    Pattern pattern = Pattern.compile("(acc|jmp|nop) (\\+|\\-)(\\d+)");
	    Matcher matcher = pattern.matcher(instruction);
	    matcher.find();
	    switch (matcher.group(1)) {
	    case "acc":
		if (matcher.group(2).equals("+")) {
		    acc = acc + Integer.parseInt(matcher.group(3));
		} else {
		    acc = acc - Integer.parseInt(matcher.group(3));
		}
		i = i + 1;
		break;
	    case "jmp":

		if (matcher.group(2).equals("+")) {
		    i = i + Integer.parseInt(matcher.group(3));
		} else {
		    i = i - Integer.parseInt(matcher.group(3));
		}
		break;
	    case "nop":
		i = i + 1;
		break;
	    default:
		// code block
	    }
	}
	return String.valueOf(acc);
    }

    @Override
    public String part2() {
	List<Integer> indexNopJmp = new ArrayList<>();
	for (int j = 0; j < puzzle.length; j++) {
	    if (puzzle[j].contains("jmp") || puzzle[j].contains("nop")) {
		indexNopJmp.add(j);
	    }
	}
	int accOk = 0;
	for (Integer index : indexNopJmp) {
	    List<Integer> instructionPassed = new ArrayList<>();
	    int acc = 0;
	    int i = 0;
	    while (!instructionPassed.contains(i) && i != puzzle.length) {
		instructionPassed.add(i);
		String instruction = puzzle[i];
		if (index == i) {
		    if (instruction.equals("nop")) {
			instruction = instruction.replace("nop", "jmp");
		    } else {
			instruction = instruction.replace("jmp", "nop");
		    }
		}
		
		Pattern pattern = Pattern.compile("(acc|jmp|nop) (\\+|\\-)(\\d+)");
		Matcher matcher = pattern.matcher(instruction);
		matcher.find();
		switch (matcher.group(1)) {
		case "acc":
		    if (matcher.group(2).equals("+")) {
			acc = acc + Integer.parseInt(matcher.group(3));
		    } else {
			acc = acc - Integer.parseInt(matcher.group(3));
		    }
		    i = i + 1;
		    break;
		case "jmp":

		    if (matcher.group(2).equals("+")) {
			i = i + Integer.parseInt(matcher.group(3));
		    } else {
			i = i - Integer.parseInt(matcher.group(3));
		    }
		    break;
		case "nop":
		    i = i + 1;
		    break;
		default:
		    // code block
		}
	    }
	    if (i == puzzle.length) {
		accOk = acc;
		break;
	    }
	}
	return String.valueOf(accOk);
    }

}
