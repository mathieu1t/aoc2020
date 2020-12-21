package fr.insee.adventofcode.days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.insee.adventofcode.utils.Utils;

public class Day21 extends Day {

    private static final List<String> puzzle = Utils.getLineStringList("src/main/resources/21.txt");

    @Override
    public String part1() {	
	Map<String, Set<String>> allergensMap = new HashMap<>();
	Map<String,Integer> ingredientsMap = new HashMap<>();
	for (String line : puzzle) {
	    String[] lineTab = line.replace(")","").split("\\(contains ");
	    String[] ingredientsTab = lineTab[0].split(" ");
	    String[] allergensTab = lineTab[1].split(", ");
	    List<String> ingredients = Arrays.asList(ingredientsTab);
	    for (String s : ingredients) {
		if (!ingredientsMap.containsKey(s)) {
		    ingredientsMap.put(s, 1);
		} else {
		    ingredientsMap.put(s, ingredientsMap.get(s)+1);
		}
	    }
	    List<String> allergens = Arrays.asList(allergensTab);
	    for (String allergen : allergens) {
		if (!allergensMap.containsKey(allergen)) {
		    allergensMap.put(allergen, new HashSet<>(ingredients));
		} else {
		   Set<String> ingredientsInAllergen = allergensMap.get(allergen);
		   ingredientsInAllergen.retainAll(ingredients);
		}
	    }
	}
	for (Set<String> set : allergensMap.values()) {
	    for (String s : set) {
		ingredientsMap.remove(s);
	    }
	}

	int sum = ingredientsMap.entrySet().stream().mapToInt(i -> i.getValue()).sum();
	return String.valueOf(sum);
    }

    @Override
    public String part2() {

	return String.valueOf("");
    }

}
