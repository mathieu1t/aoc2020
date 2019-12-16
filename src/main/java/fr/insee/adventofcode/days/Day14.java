package fr.insee.adventofcode.days;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import fr.insee.adventofcode.model.Reaction;
import fr.insee.adventofcode.utils.Utils;

public class Day14 extends Day {

    @Override
    public String part1(String filepath, Object... params) {
	Map<String,Reaction> reactions = new TreeMap<>();
	List<Reaction> liste = Utils.getLignes(filepath).map(r -> new Reaction(r)).collect(Collectors.toList());
	liste.stream().forEach(r -> reactions.put(r.sortie, r));
	long countFUEL = reactions.get("FUEL").findCountOre(1, reactions, 0);
	
	return String.valueOf(countFUEL);
    }

    @Override
    public String part2(String filepath, Object... params) {
	
	return String.valueOf(0);
    }
    
   

}
