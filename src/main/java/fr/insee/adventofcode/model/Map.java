package fr.insee.adventofcode.model;

import java.util.ArrayList;
import java.util.List;

public class Map {

    List<Asteroid> asteroids = new ArrayList<>();

    /**
     * 
     */
    public Map() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @param asteroids
     */
    public Map(List<Asteroid> asteroids) {
	super();
	this.asteroids = asteroids;
    }

    public void load(List<String> puzzle) {
	List<Asteroid> liste =new ArrayList<>();
	for (int i = 0; i < puzzle.size(); i++) {
	    String[] ligne = puzzle.get(i).split("");
	    for (int j = 0; j < ligne.length; j++) {
		if (ligne[j].contains("#")) {
		    Asteroid a =  new Asteroid(j, i);
		    liste.add(a);
		}
	    }
	}
	setAsteroids(liste);
    }

    /**
     * @return the asteroids
     */
    public List<Asteroid> getAsteroids() {
	return asteroids;
    }

    /**
     * @param asteroids
     *            the asteroids to set
     */
    public void setAsteroids(List<Asteroid> asteroids) {
	this.asteroids = asteroids;
    }
}
