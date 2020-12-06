package fr.insee.adventofcode.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Form {
    private int nbPersons = 0;
    private Map<Character, Integer> questions = new HashMap<>();
    
    public int countEveryoneAnswered() {
	int count = 0;
	for (Entry<Character, Integer> entry : questions.entrySet()) {
	   if (entry.getValue() == nbPersons) {
	       count++;
	   }
	}
	return count;
    }
    
    public int getNbPersons() {
        return nbPersons;
    }

    public void setNbPersons(int nbPersons) {
        this.nbPersons = nbPersons;
    }

    public Map<Character, Integer> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<Character, Integer> questions) {
        this.questions = questions;
    }
}
