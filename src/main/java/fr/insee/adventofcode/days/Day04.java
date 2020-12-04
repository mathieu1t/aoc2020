package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.insee.adventofcode.model.Passport;
import fr.insee.adventofcode.utils.Utils;

public class Day04 extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/04.txt");

    @Override
    public String part1() {
	List<Passport> passports = getPassports();
	return String.valueOf(passports.stream().filter(p -> p.isAllPresentRequired()).count());
    }

    @Override
    public String part2() {
	List<Passport> passports = getPassports();
	return String.valueOf(passports.stream().filter(p -> p.isValid()).count());
    }
    
    private List<Passport> getPassports() {
	List<Passport> passports = new ArrayList<>();
	Passport p = new Passport();
	for (String line : puzzle) {
	    if (StringUtils.isBlank(line)) {
                passports.add(p);
                p = new Passport();
            } else {
        	String[] values = line.split(" ");
        	for (String value : values) {
                    String[] keyAndValue = value.split(":");
                    p.getValues().put(keyAndValue[0], keyAndValue[1]);
                }
            }
	}
	passports.add(p);
	return passports;
    }

}
