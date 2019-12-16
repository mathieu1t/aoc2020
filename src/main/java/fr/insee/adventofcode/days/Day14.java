package fr.insee.adventofcode.days;

import java.util.List;
import java.util.stream.Collectors;

import fr.insee.adventofcode.model.Reaction;
import fr.insee.adventofcode.utils.Utils;

public class Day14 extends Day {

    @Override
    public String part1(String filepath, Object... params) {
	List<Reaction> reactions = Utils.getLignes(filepath).map(r -> new Reaction(r)).sorted().collect(Collectors.toList());
	Reaction fuel = findReaction("FUEL", reactions);
	long countFUEL = fuel.findCountOre(1, reactions);
	
	return String.valueOf(countFUEL);
    }

    private Reaction findReaction(String s, List<Reaction> reactions) {
	return reactions.stream().filter(x -> x.sortie .equals(s)).findFirst().orElse(null);
    }

    @Override
    public String part2(String filepath, Object... params) {
	
	return String.valueOf(0);
    }
    
   

}
