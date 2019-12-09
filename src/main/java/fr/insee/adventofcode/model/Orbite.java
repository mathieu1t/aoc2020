package fr.insee.adventofcode.model;

import java.util.ArrayList;
import java.util.List;

public class Orbite {

	public static final Orbite COM = new Orbite("COM", null);
	
	private Orbite precedent;
	private String axe;
	private String objet;

	public Orbite() {}
	
	public Orbite(String axe, String objet) {
		this.axe = axe;
		this.objet = objet;
	}
	
	 public int nombreOrbites() {
         if(precedent == null) return 0;
         return 1 + precedent.nombreOrbites();
     }
	 
	 public List<Orbite> precedents() {
         List<Orbite> precedents = new ArrayList<>();
         Orbite p = precedent;
         while(p!= null) {
             precedents.add(p);
             p = p.getPrecedent();
         }
         return precedents;
     }

	/**
	 * @return the precedent
	 */
	public Orbite getPrecedent() {
		return precedent;
	}

	/**
	 * @param precedent the precedent to set
	 */
	public void setPrecedent(Orbite precedent) {
		this.precedent = precedent;
	}

	/**
	 * @return the axe
	 */
	public String getAxe() {
		return axe;
	}

	/**
	 * @param axe the axe to set
	 */
	public void setAxe(String axe) {
		this.axe = axe;
	}

	/**
	 * @return the objet
	 */
	public String getObjet() {
		return objet;
	}

	/**
	 * @param objet the objet to set
	 */
	public void setObjet(String objet) {
		this.objet = objet;
	}
	
	
}
