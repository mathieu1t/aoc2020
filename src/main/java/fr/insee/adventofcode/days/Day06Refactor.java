package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import fr.insee.adventofcode.utils.Utils;

public class Day06Refactor extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/06.txt");

    @Override
    public String part1() {
	List<Form> forms = getForms();
	return String.valueOf(forms.stream().map(f -> f.questions.size()).reduce(0, Integer::sum));
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
		Map<Character, Integer> questions = f.questions;
		for (char q : line.toCharArray()) {
		    if (questions.containsKey(q)) {
			questions.put(q, questions.get(q) + 1);
		    } else {
			questions.put(q, 1);
		    }
		}
		f.questions = questions;
		f.nbPersons++;
	    }
	}
	forms.add(f);
	return forms;
    }

    class Form {
	public int nbPersons = 0;
	public Map<Character, Integer> questions = new HashMap<>();

	public int countEveryoneAnswered() {
	    int count = 0;
	    for (Entry<Character, Integer> entry : questions.entrySet()) {
		if (entry.getValue() == nbPersons) {
		    count++;
		}
	    }
	    return count;
	}

    }

}
