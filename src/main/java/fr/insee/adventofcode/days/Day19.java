package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day19 extends Day {

    private static final List<String> puzzle_rules = Utils.getLineStringList("src/main/resources/19_rules.txt");
    private static final List<String> puzzle_messages = Utils.getLineStringList("src/main/resources/19_messages.txt");

    @Override
    public String part1() {

	List<Rule> rules = new ArrayList<>();

	for (String rule : puzzle_rules) {
	    String[] r = rule.split(":");
	    Rule ru = new Rule();
	    ru.number = r[0];
	    ru.rule = r[1].trim();
	    rules.add(ru);
	}

	Rule zero = rules.stream().filter(ru -> ru.number.equals("0")).findFirst().get();

	Map<Rule, String> regs = new HashMap<>();
	String reg = getRegexp(zero, rules, regs);
	int count = 0;
	for (String message : puzzle_messages) {
	    Pattern pattern = Pattern.compile(reg);
	    Matcher matcher = pattern.matcher(message);
	    if (matcher.matches()) {
		count++;
	    }
	}

	return String.valueOf(count);
    }

    private String getRegexp(Rule r, List<Rule> rules, Map<Rule, String> regs) {
	String rule = r.rule;
	if (regs.containsKey(r)) {
	    return regs.get(r);
	}
	if (r.rule.contains("\"")) {
	    regs.put(r, rule.replaceAll("\"", ""));
	    return rule.replaceAll("\"", "");
	}
	StringBuilder regex = new StringBuilder("(");
	String[] parts = rule.split(" ");
	for (String part : parts) {
	    if (part.equals("|")) {
		regex.append("|");
	    } else {
		Rule otherRule = rules.stream().filter(ru -> ru.number.equals(part)).findFirst().get();
		regex.append(getRegexp(otherRule, rules, regs));
	    }
	}
	regex.append(")");
	regs.put(r, regex.toString());
	return regex.toString();
    }

    @Override
    public String part2() {

	List<Rule> rules = new ArrayList<>();

	for (String rule : puzzle_rules) {
	    String[] r = rule.split(":");
	    Rule ru = new Rule();
	    ru.number = r[0];
	    ru.rule = r[1].trim();
	    rules.add(ru);
	}

	int max = rules.stream().map(r -> r.number).mapToInt(r -> Integer.parseInt(r)).max().getAsInt();
	Rule zero = new Rule();
	max = max + 1;
	zero.number = max + ""; // nouveau 0
	zero.rule = (max + 1) + " " + (max + 2);
	rules.add(zero);
	Rule huit= new Rule();
	max = max + 1;
	huit.number = max + ""; // nouveau 8
	huit.rule = "42 | 42 " + max;
	rules.add(huit);
	Rule onze = new Rule();
	max = max + 1;
	onze.number = max + ""; // nouveau 11
	onze.rule = "42 31 | 42 " + max + " 31";
	rules.add(onze);

	Map<Rule, String> regs = new HashMap<>();
	String reg = getRegexp2(zero, rules, regs, huit.number, onze.number,0,0);
	int count = 0;
	for (String message : puzzle_messages) {
	    Pattern pattern = Pattern.compile(reg);
	    Matcher matcher = pattern.matcher(message);
	    if (matcher.matches()) {
		count++;
	    }
	}
	return String.valueOf(count);
    }
    
    private String getRegexp2(Rule r, List<Rule> rules, Map<Rule, String> regs, String index8 , String index11, int countPassage8, int countPassage11) {
	if (r.number.equals(index8)) {
	    countPassage8++;
	    if (countPassage8 >= 20) { // nombre arbitraire pour stopper la boucle
		Rule huit = rules.stream().filter(ru -> ru.number.equals("8")).findFirst().get();
		return regs.get(huit);
	    }
	}
	if (r.number.equals(index11)) {
	    countPassage11++;
	    if (countPassage11 >= 20) {
		Rule onze = rules.stream().filter(ru -> ru.number.equals("11")).findFirst().get();
		return regs.get(onze);
	    }
	}
   	String rule = r.rule;
   	if (regs.containsKey(r)) {
   	    return regs.get(r);
   	}
   	if (r.rule.contains("\"")) {
   	    regs.put(r, rule.replaceAll("\"", ""));
   	    return rule.replaceAll("\"", "");
   	}
   	StringBuilder regex = new StringBuilder("(");
   	String[] parts = rule.split(" ");
   	for (String part : parts) {
   	    if (part.equals("|")) {
   		regex.append("|");
   	    } else {
   		Rule otherRule = rules.stream().filter(ru -> ru.number.equals(part)).findFirst().get();
   		regex.append(getRegexp2(otherRule, rules, regs, index8,index11,countPassage8, countPassage11));
   	    }
   	}
   	regex.append(")");
   	regs.put(r, regex.toString());
   	return regex.toString();
       }


    class Rule {
	String number;
	String rule;
    }

}
