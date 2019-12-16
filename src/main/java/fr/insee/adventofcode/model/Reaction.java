package fr.insee.adventofcode.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Reaction {

    public String sortie;
    public long quantite;

    public TreeMap<String, Long> entrees;

    public Reaction(String r) {
	String[] es = r.split(" => ");
	String[] sort = es[1].split(" ");
	String[] ent = es[0].split(", ");
	sortie = sort[1];
	quantite = Long.parseLong(sort[0]);

	entrees = new TreeMap<>();
	for (String e : ent) {
	    String[] entree = e.split(" ");
	    entrees.put(entree[1], Long.parseLong(entree[0]));
	}
    }

//    public Map<String, Long> findBase(long quantite, Map<String, Reaction> reactions, Map<String, Long> base) {
//	for (String x : this.entrees.keySet()) {
//	    if (x.equals("ORE")) {
//		if (base.containsKey(sortie)) {
//		    base.put(sortie,base.get(sortie)+quantite);
//		} else {
//		    base.put(sortie,quantite);
//		}
//	    } else {
//		reactions.get(x).findBase(this.entrees.get(x) * (long)Math.ceil((double)quantite / this.quantite), reactions, base);
//	    }
//	}
//	return base;
//    }
//
//    public long findCountOre(long quantite, Map<String, Reaction> reactions) {
//	long retour = 0;
//	Map<String, Long> base = findBase(quantite, reactions, new HashMap<String, Long>());
//	for (Entry<String,Long> entry : base.entrySet()) {
//	    long qOre = reactions.get(entry.getKey()).entrees.get("ORE").longValue();
//	    retour += Math.ceil((double)entry.getValue() / reactions.get(entry.getKey()).quantite) * qOre;
//	}
//	return retour;
//    }

    public long findCountOre(long quantite, Map<String, Reaction> reactions, long oreEnTrop) {

	long retour = 0;

	long q = (long) Math.ceil((double) quantite / this.quantite);
	
	for (String x : this.entrees.keySet()) {
	    if (x.equals("ORE")) {
		long nbOre = this.entrees.get(x);
		retour += nbOre * q;
		oreEnTrop += nbOre * q - (nbOre * quantite / this.quantite);
		System.out.println(oreEnTrop);
	    } else {
		retour += reactions.get(x).findCountOre(
			this.entrees.get(x) * (long) Math.ceil((double) quantite / this.quantite), reactions, oreEnTrop);
	    }
	}

	return retour;
    }

}
