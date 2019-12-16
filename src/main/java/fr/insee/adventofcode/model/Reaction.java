package fr.insee.adventofcode.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Reaction implements Comparable<Reaction>{

    public String sortie;
    public long quantite;

    public Map<String, Long> entrees;
    
    public int complexity;

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
	complexity = entrees.size();
    }

    public Map<String, Long> findBase(long quantite, List<Reaction> reactions, Map<String, Long> base) {
	for (String x : this.entrees.keySet()) {
	    if (x.equals("ORE")) {
		if (base.containsKey(sortie)) {
		    base.put(sortie, base.get(sortie) + quantite);
		} else {
		    base.put(sortie, quantite);
		}
	    } else {
		findReaction(x,reactions).findBase(this.entrees.get(x) * (long) Math.ceil((double) quantite / this.quantite), reactions, base);
	    }
	}
	return base;
    }

    public long findCountOre(long quantite, List<Reaction> reactions) {
	long retour = 0;
	Map<String, Long> base = findBase(quantite, reactions, new HashMap<String, Long>());
	for (Entry<String, Long> entry : base.entrySet()) {
	    long qOre = findReaction(entry.getKey(), reactions).entrees.get("ORE").longValue();
	    retour += Math.ceil((double) entry.getValue() / findReaction(entry.getKey(),reactions).quantite) * qOre;
	}
	return retour;
    }

    @Override
    public int compareTo(Reaction o) {
	if (this.complexity > o.complexity) return -1;
	if (this.complexity < o.complexity) return 1;
	return 0;
    }
    
    private Reaction findReaction(String s, List<Reaction> reactions) {
	return reactions.stream().filter(x -> x.sortie .equals(s)).findFirst().orElse(null);
    }

    // public long findCountOre(long quantite, Map<String, Reaction> reactions, long
    // oreEnTrop) {
    //
    // long retour = 0;
    //
    // long q = (long) Math.ceil((double) quantite / this.quantite);
    //
    // for (String x : this.entrees.keySet()) {
    // if (x.equals("ORE")) {
    // long nbOre = this.entrees.get(x);
    // retour += nbOre * q;
    // oreEnTrop += nbOre * q - (nbOre * quantite / this.quantite);
    // System.out.println(oreEnTrop);
    // } else {
    // retour += reactions.get(x).findCountOre(
    // this.entrees.get(x) * (long) Math.ceil((double) quantite / this.quantite),
    // reactions, oreEnTrop);
    // }
    // }
    //
    // return retour;
    // }

}
