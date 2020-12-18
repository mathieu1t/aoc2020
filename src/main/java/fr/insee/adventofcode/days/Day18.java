package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day18 extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/18.txt");

    @Override
    public String part1() {
	long count = 0;
	for (String line : puzzle) {
	    List<String> chars = Arrays.asList(line.split(""));
	    chars = chars.stream().filter(c -> !" ".equals(c)).collect(Collectors.toList());
	    List<String> eval = eval(chars);
	    count += Long.parseLong(eval.get(0));

	}
	return String.valueOf(count);
    }

    private List<String> eval(List<String> chars) {
	if (chars.size() == 3) {
	    long v = 0;
	    if (chars.get(1).equals("+")) {
		v = Long.parseLong(chars.get(0)) + Long.parseLong(chars.get(2));
	    } else {
		v = Long.parseLong(chars.get(0)) * Long.parseLong(chars.get(2));
	    }
	    List<String> retour = new ArrayList<>();
	    retour.add(v+"");
	    return retour;
	}
	int pos1 = 0;
	int pos2 = 0;
	if (chars.contains("(")) {
	    pos1 = chars.lastIndexOf("(");
	    pos2 = chars.subList(pos1, chars.size()).indexOf(")")+pos1;
	    List<String> newChars = new ArrayList<>();
	    newChars.addAll(chars.subList(0, pos1));
	    newChars.addAll(eval(chars.subList(pos1+1, pos2)));	 
	    if (pos2 != chars.size()-1) {
		List<String> c = chars.subList(pos2+1, chars.size());
		newChars.addAll(c);
	    }
	    return eval(newChars);
	}
	while (chars.size() != 1) {
	    List<String> newChars = eval(chars.subList(0, 3));
	    newChars.addAll(chars.subList(3, chars.size()));
	    chars = eval(newChars);
	}
	return chars;
    }

    @Override
    public String part2() {

	return String.valueOf("");
    }

}
