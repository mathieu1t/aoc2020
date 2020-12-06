package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import fr.insee.adventofcode.model.Form;
import fr.insee.adventofcode.utils.Utils;

public class Day06 extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/06.txt");

    @Override
    public String part1() {
	List<Form> forms = getForms();
	return String.valueOf(forms.stream().map(f -> f.getQuestions().size()).reduce(0, Integer::sum));
    }

    @Override
    public String part2() {
	List<Form> forms = getForms();
	return String.valueOf(forms.stream().map(f -> f.countEveryoneAnswered()).reduce(0, Integer::sum));
    }    
    
    private List<Form> getForms() {
	List<Form> forms = new ArrayList<>();
	Form f = new Form();
	for (String line : puzzle) {
            if (StringUtils.isBlank(line)) {
                forms.add(f);
        	f = new Form();
            } else {
        	 Map<Character, Integer> questions = f.getQuestions();
        	 for (char q : line.toCharArray()) {           	     
                     if (questions.containsKey(q)) {
                	 questions.put(q, f.getQuestions().get(q) + 1);
                     } else {
                	 questions.put(q, 1); 
                     }                    
                 }
        	 f.setQuestions(questions);
        	 f.setNbPersons(f.getNbPersons() + 1);
            }  
        }
	forms.add(f);
	return forms;
    }

}
