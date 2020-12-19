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

	List<String> rules_letter = puzzle_rules.stream().filter(r -> r.contains("\"")).collect(Collectors.toList());
	List<String> rules_multiple = puzzle_rules.stream().filter(r -> r.contains("|")).collect(Collectors.toList());
	List<String> rules_simple = puzzle_rules.stream().filter(r -> !r.contains("|") && !r.contains("\""))
		.collect(Collectors.toList());

	List<Rule> rules = new ArrayList<>();

	for (String rule : rules_letter) {
	    String[] r = rule.split(":");
	    Rule ru = new Rule();
	    ru.number = r[0];
	    ru.type = TypeRule.FINAL;
	    ru.rule = r[1].trim();
	    rules.add(ru);
	}

	for (String rule : rules_simple) {
	    String[] r = rule.split(":");
	    Rule ru = new Rule();
	    ru.number = r[0];
	    ru.type = TypeRule.SIMPLE;
	    ru.rule = r[1].trim();
	    rules.add(ru);
	}
	for (String rule : rules_multiple) {
	    String[] r = rule.split(":");
	    Rule ru = new Rule();
	    ru.number = r[0];
	    ru.type = TypeRule.MULTIPLE;
	    ru.rule = r[1].trim();
	    rules.add(ru);
	}

	Rule zero = rules.stream().filter(ru -> ru.number.equals("0")).findFirst().get();

	Map<Rule,String> regs = new HashMap<>();
	String reg = getRegexp(zero, rules,regs);
	int count = 0;
	for (String message : puzzle_messages) {
	    Pattern pattern = Pattern.compile(reg);
	    Matcher matcher = pattern.matcher(message);
	   if ( matcher.matches()) {
	       count++;
	   }
	}

	return String.valueOf(count);
    }

    private String getRegexp(Rule r, List<Rule> rules, Map<Rule,String> regs) {
	String rule = r.rule;
	if (r.type == TypeRule.FINAL) {
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

	return String.valueOf("");
    }

    enum TypeRule {
	MULTIPLE, SIMPLE, FINAL
    }

    class Rule {
	String number;
	String rule;
	TypeRule type;
    }

}
