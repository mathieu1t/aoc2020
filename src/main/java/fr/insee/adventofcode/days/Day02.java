package fr.insee.adventofcode.days;

import java.util.Arrays;

import fr.insee.adventofcode.model.PasswordPolicy;
import fr.insee.adventofcode.model.PasswordPolicy.Policy;
import fr.insee.adventofcode.utils.Utils;

public class Day02 extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/02.txt");

    @Override
    public String part1() {
        long nbGood = Arrays.stream(puzzle).map(p -> new PasswordPolicy(p, Policy.NUMBER_OF_TIMES)).filter(p -> p.isValid()).count();
        return String.valueOf(nbGood);
    }

    @Override
    public String part2() {
        long nbGood = Arrays.stream(puzzle).map(p -> new PasswordPolicy(p, Policy.POSITION)).filter(p -> p.isValid()).count();
        return String.valueOf(nbGood);
    }

}
