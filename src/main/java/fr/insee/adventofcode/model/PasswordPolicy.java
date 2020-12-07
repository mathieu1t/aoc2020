package fr.insee.adventofcode.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class PasswordPolicy {

    private static final String REGEX = "(\\d+)\\-(\\d+)\\s(\\D)\\:(\\D+)";

    public enum Policy {
        NUMBER_OF_TIMES, POSITION;
    }

    private int one;
    private int two;
    private char letter;
    private String pwd;
    private Policy pol;

    public PasswordPolicy(String pwdPol, Policy pol) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(pwdPol);
        if (m.matches()) {
            this.one = Integer.valueOf(m.group(1));
            this.two = Integer.valueOf(m.group(2));
            this.letter = m.group(3).charAt(0);
            this.pwd = m.group(4);
        }
        this.pol = pol;
    }

    public boolean isValid() {
        if (pol == Policy.NUMBER_OF_TIMES) {
            int count = StringUtils.countMatches(pwd, letter);
            return count >= one && count <= two;
        }
        else {
            char p1 = pwd.charAt(one);
            char p2 = pwd.charAt(two);
            return (letter == p1 || letter == p2) && p1 != p2;
        }
    }

}
