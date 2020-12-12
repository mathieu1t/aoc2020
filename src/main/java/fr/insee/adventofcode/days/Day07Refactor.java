package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day07Refactor extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/07.txt");

    private static final String SHINY_GOLD = "shiny gold";

    @Override
    public String part1() {
	List<Rule> rules = Arrays.stream(puzzle).map(line -> new Rule(line)).collect(Collectors.toList());
	Map<String, Rule> allRules = new TreeMap<>();
	rules.stream().forEach(r -> allRules.put(r.bagColor, r));
	List<Rule> rulesWithShinyGold = rules.stream().filter(r -> containsShinyGold(rules, r, allRules))
		.collect(Collectors.toList());
	return String.valueOf(rulesWithShinyGold.size());
    }

    private boolean containsShinyGold(List<Rule> rules, Rule rule, Map<String, Rule> allRules) {
	if (rule.containsBags.containsKey(SHINY_GOLD)) {
	    return true;
	} else {
	    List<Rule> otherRules = new ArrayList<>();
	    for (String colorBag : rule.containsBags.keySet()) {
		Rule otherRule = allRules.get(colorBag);
		otherRules.add(otherRule);
	    }
	    return otherRules.stream().anyMatch(r -> containsShinyGold(otherRules, r, allRules));
	}
    }

    @Override
    public String part2() {
	List<Rule> rules = Arrays.stream(puzzle).map(line -> new Rule(line)).collect(Collectors.toList());
	Map<String, Rule> allRules = new TreeMap<>();
	rules.stream().forEach(r -> allRules.put(r.bagColor, r));
	Rule rule = allRules.get(SHINY_GOLD);
	long count = countBagsInsideShinyGold(allRules, rule.containsBags, 0);
	return String.valueOf(count);
    }

    private long countBagsInsideShinyGold(Map<String, Rule> allRules, Map<String, Long> contains, long count) {
	for (Entry<String, Long> entry : contains.entrySet()) {
	    Rule r = allRules.get(entry.getKey());
	    count = count + entry.getValue() * countBagsInsideShinyGold(allRules, r.containsBags, 1);
	}
	return count;
    }

    class Rule {
	private static final String NO_OTHER_BAGS = "no other bags";
	private static final String REGEX_RULES = "(\\d+) ([a-z ]+) (bags|bag)";

	public String bagColor;
	public Map<String, Long> containsBags = new HashMap<>();

	public Rule(String line) {
	    String[] contains = line.replace(".", "").split(" bags contain ");
	    this.bagColor = contains[0];
	    if (!NO_OTHER_BAGS.equals(contains[1])) {
		String[] rules = contains[1].split(",");
		for (String rule : rules) {
		    Pattern pattern = Pattern.compile(REGEX_RULES);
		    Matcher matcher = pattern.matcher(rule);
		    matcher.find();
		    containsBags.put(matcher.group(2), Long.valueOf(matcher.group(1)));
		}
	    }
	}
    }

}
