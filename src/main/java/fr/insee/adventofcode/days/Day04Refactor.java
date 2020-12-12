package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import fr.insee.adventofcode.utils.Utils;

public class Day04Refactor extends Day {

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
                    p.values.put(keyAndValue[0], keyAndValue[1]);
                }
            }
	}
	passports.add(p);
	return passports;
    }
    
    class Passport {
	    
	    public Map<String,String> values = new HashMap<>();
	    

	    public boolean isAllPresentRequired() {
		return values.get("byr") != null && values.get("iyr") != null && values.get("eyr") != null && values.get("hgt") != null && values.get("hcl") != null && values.get("ecl") != null && values.get("pid") != null;
	    }

	    public boolean isValid() {
		return isAllPresentRequired() && isValidBirthYear() && isValidIssueYear() && isValidExpirationYear()
			&& isValidHeight() && isValidHairColor() && isValidEyeColor() && isValidPassportId();
	    }
	    

	    private boolean isValidBirthYear() {
		int birthYear = Integer.parseInt(values.get("byr"));
		return birthYear >= 1920 && birthYear <= 2002;
	    }

	    private boolean isValidIssueYear() {
		int issueYear = Integer.parseInt(values.get("iyr"));
		return issueYear >= 2010 && issueYear <= 2020;
	    }

	    private boolean isValidExpirationYear() {
		int expirationYear = Integer.parseInt(values.get("eyr"));
		return expirationYear >= 2020 && expirationYear <= 2030;
	    }

	    private boolean isValidHeight() {
		Pattern pattern = Pattern.compile("(\\d+)(in|cm)");
		Matcher matcher = pattern.matcher(values.get("hgt"));

		if (!matcher.find()) {
		    return false;
		}

		int heightValue = Integer.parseInt(matcher.group(1));
		String heightUnit = matcher.group(2);

		if (heightUnit.equalsIgnoreCase("in")) {
		    return heightValue >= 59 && heightValue <= 76;
		} else if (heightUnit.equalsIgnoreCase("cm")) {
		    return heightValue >= 150 && heightValue <= 193;
		}

		return false;
	    }

	    private boolean isValidHairColor() {
		return values.get("hcl").matches("#[0-9a-f]{6}");
	    }

	    private boolean isValidEyeColor() {
		return values.get("ecl").matches("(amb|blu|brn|gry|grn|hzl|oth)");
	    }

	    private boolean isValidPassportId() {
		return values.get("pid").matches("\\d{9}");
	    }
	}

}
