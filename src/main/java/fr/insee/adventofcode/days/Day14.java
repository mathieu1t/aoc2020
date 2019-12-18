package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day14 extends Day {

    @Override
    public String part1(String filepath, Object... params) {
	Map<String, Reaction> reactions = new TreeMap<>();
	List<Reaction> liste = Utils.getLignes(filepath).map(r -> new Reaction(r)).collect(Collectors.toList());
	liste.stream().forEach(r -> reactions.put(r.sortie, r));
	Reaction reactionFUEL = reactions.get("FUEL");
	reactions.remove("FUEL");
	Map<String, Long> entreesFUEL = reactionFUEL.entrees;

	return String.valueOf(getNbFUEL(reactions, entreesFUEL, 1));
    }

    private long getNbFUEL(Map<String, Reaction> reactions, Map<String, Long> entreesFUEL, long nb) {
	entreesFUEL = entreesFUEL.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(),e -> e.getValue() * nb));
	while (entreesFUEL.isEmpty() || !entreesFUEL.entrySet().stream().allMatch(x -> "ORE".equals(x.getKey()))) {
	    List<String> newEntreesFUEL = new ArrayList<>();
	    newEntreesFUEL.addAll(entreesFUEL.keySet());
	    for (String entry : newEntreesFUEL) {

		if (isOnlyInOuput(entry, reactions)) {
		    Reaction r = reactions.get(entry);
		    if (r == null) {
			continue;
		    }
		    long quantite = (long) Math.ceil((double) entreesFUEL.get(entry) / r.quantite);
		    for (Entry<String, Long> entry2 : r.entrees.entrySet()) {
			if (entreesFUEL.containsKey(entry2.getKey())) {
			    entreesFUEL.put(entry2.getKey(), entreesFUEL.get(entry2.getKey()) + entry2.getValue() * quantite);
			} else {
			    entreesFUEL.put(entry2.getKey(), entry2.getValue() * quantite);
			}
		    }
		    entreesFUEL.remove(entry);
		    reactions.remove(entry);
		} else {
		    entreesFUEL.put(entry, entreesFUEL.get(entry));
		}
	    }
	}
	return entreesFUEL.get("ORE");
    }

    private boolean isOnlyInOuput(String r, Map<String, Reaction> reactions) {
	for (Entry<String, Reaction> entry : reactions.entrySet()) {
	    if (entry.getValue().entrees.containsKey(r)) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public String part2(String filepath, Object... params) {
	long fuel = 6320000;
	long ore = 0;
	while (ore <= 1000000000000l) {
	    Map<String, Reaction> reactions = new TreeMap<>();
	    List<Reaction> liste = Utils.getLignes(filepath).map(r -> new Reaction(r)).collect(Collectors.toList());
	    liste.stream().forEach(r -> reactions.put(r.sortie, r));
	    Reaction reactionFUEL = reactions.get("FUEL");
	    reactions.remove("FUEL");
	    Map<String, Long> entreesFUEL = reactionFUEL.entrees;
	    ore = getNbFUEL(reactions, entreesFUEL, ++fuel);
	    //System.out.println(fuel + " fuel pour " + ore + " ore");
	}
	return String.valueOf(fuel-1);
    }

    static class Reaction {

	public String sortie;
	public long quantite;

	public Map<String, Long> entrees;

	public Reaction(String r) {
	    String[] es = r.split(" => ");
	    String[] sort = es[1].split(" ");
	    String[] ent = es[0].split(", ");
	    sortie = sort[1];
	    quantite = Long.parseLong(sort[0]);

	    entrees = new HashMap<>();
	    for (String e : ent) {
		String[] entree = e.split(" ");
		entrees.put(entree[1], Long.parseLong(entree[0]));
	    }
	}

    }

}
