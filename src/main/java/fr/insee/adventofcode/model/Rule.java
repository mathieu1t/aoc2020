package fr.insee.adventofcode.model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {
    private static final String NO_OTHER_BAGS = "no other bags";
    private static final String REGEX_RULES = "([1-9]+) ([a-z ]+) (bags|bag)";

    private String bagColor;
    private Map<String, Long> containsBags = new HashMap<>();

    public Rule(String line) {
        String[] contains = line.replace(".", "").split(" bags contain ");
        this.bagColor = contains[0];
        if ( !NO_OTHER_BAGS.equals(contains[1])) {
            String[] rules = contains[1].split(",");
            for (String rule : rules) {
                Pattern pattern = Pattern.compile(REGEX_RULES);
                Matcher matcher = pattern.matcher(rule);
                matcher.find();
                containsBags.put(matcher.group(2), Long.valueOf(matcher.group(1)));
            }
        }
    }

    public String getBagColor() {
        return bagColor;
    }

    public void setBagColor(String bagColor) {
        this.bagColor = bagColor;
    }

    public Map<String, Long> getContainsBags() {
        return containsBags;
    }

    public void setContainsBags(Map<String, Long> containsBags) {
        this.containsBags = containsBags;
    }
}
