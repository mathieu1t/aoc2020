package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import fr.insee.adventofcode.model.Rule;
import fr.insee.adventofcode.utils.Utils;

public class Day07 extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/07.txt");

    private static final String SHINY_GOLD = "shiny gold";

    @Override
    public String part1() {
        List<Rule> rules = Arrays.stream(puzzle).map(line -> new Rule(line)).collect(Collectors.toList());
        Map<String, Rule> allRules = new TreeMap<>();
        rules.stream().forEach(r -> allRules.put(r.getBagColor(), r));
        List<Rule> rulesWithShinyGold = rules.stream().filter(r -> containsShinyGold(rules, r, allRules)).collect(Collectors.toList());
        return String.valueOf(rulesWithShinyGold.size());
    }

    private boolean containsShinyGold(List<Rule> rules, Rule rule, Map<String, Rule> allRules) {
        if (rule.getContainsBags().containsKey(SHINY_GOLD)) {
            return true;
        }
        else {
            List<Rule> otherRules = new ArrayList<>();
            for (String colorBag : rule.getContainsBags().keySet()) {
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
        rules.stream().forEach(r -> allRules.put(r.getBagColor(), r));
        Rule rule = allRules.get(SHINY_GOLD);
        long count = countBagsInsideShinyGold(allRules, rule.getContainsBags(), 0);
        return String.valueOf(count);
    }

    private long countBagsInsideShinyGold(Map<String, Rule> allRules, Map<String, Long> contains, long count) {
        for (Entry<String, Long> entry : contains.entrySet()) {
            Rule r = allRules.get(entry.getKey());
            count = count + entry.getValue() * countBagsInsideShinyGold(allRules, r.getContainsBags(), 1);
        }
        return count;
    }

}
