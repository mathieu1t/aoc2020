package fr.insee.adventofcode.model;

import java.util.ArrayList;
import java.util.List;


public class Fil {

	List<Ligne> lignes;
	
	/**
	 * @return the lignes
	 */
	public List<Ligne> getLignes() {
		return lignes;
	}

	public Fil(String[] puzzle) {
		lignes = new ArrayList<>();
		Point depart = Point.centre;
        for(String string : puzzle) {
            Ligne ligne = new Ligne(depart, string);
            lignes.add(ligne);
            depart = ligne.getArrivee();
        }
	}
}
