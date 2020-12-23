package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day23 extends Day {

    @Override
    public String part1() {
	String cupLabeling = "364289715";

	List<String> clockwise = Arrays.asList(cupLabeling.split(""));
	List<Integer> clockwiseInt = clockwise.stream().map(i -> Integer.parseInt(i)).collect(Collectors.toList());

	for (int m = 0; m < 100; m++) {

	    int one = clockwiseInt.remove(1);
	    int two = clockwiseInt.remove(1);
	    int three = clockwiseInt.remove(1);

	    int next = clockwiseInt.get(0) - 1;
	    if (next == 0) {
		next = 9;
	    }

	    while (next == one || next == two || next == three) {
		next = next - 1;
		if (next == 0) {
		    next = 9;
		}
	    }
	    
	    int index = clockwiseInt.indexOf(next);
	    
	    clockwiseInt.add(index+1, one);
	    clockwiseInt.add(index+2, two);
	    clockwiseInt.add(index+3, three);
	    
	    List<Integer> nextClockwiseInt = new ArrayList<>();
	    for (int i = 1 ; i < clockwiseInt.size(); i++) {
		nextClockwiseInt.add(clockwiseInt.get(i));
	    }
	    nextClockwiseInt.add(clockwiseInt.get(0));
	    clockwiseInt = nextClockwiseInt;
	   
	}

	int index1 = clockwiseInt.indexOf(1);
	String ordre = "";
	 for (int i = 0 ; i < clockwiseInt.size(); i++) {
	     ordre = ordre + clockwiseInt.get(i);
	 }
	 String result = ordre.substring(index1+1)+ordre.substring(0, index1);	 
	 
	return result;

    }
    

    @Override
    public String part2() {

	return String.valueOf("");
    }

}
