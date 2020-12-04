package fr.insee.adventofcode.model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passport {
    
    private Map<String,String> values = new HashMap<>();
    

    public boolean isAllPresentRequired() {
	return values.get("byr") != null && values.get("iyr") != null && values.get("eyr") != null && values.get("hgt") != null && values.get("hcl") != null && values.get("ecl") != null && values.get("pid") != null;
    }

    public boolean isValid() {
	return isAllPresentRequired() && isValidBirthYear() && isValidIssueYear() && isValidExpirationYear()
		&& isValidHeight() && isValidHairColor() && isValidEyeColor() && isValidPassportId();
    }
    

    private boolean isValidBirthYear() {
	final int birthYear = Integer.parseInt(values.get("byr"));
	return birthYear >= 1920 && birthYear <= 2002;
    }

    private boolean isValidIssueYear() {
	final int issueYear = Integer.parseInt(values.get("iyr"));
	return issueYear >= 2010 && issueYear <= 2020;
    }

    private boolean isValidExpirationYear() {
	final int expirationYear = Integer.parseInt(values.get("eyr"));
	return expirationYear >= 2020 && expirationYear <= 2030;
    }

    private boolean isValidHeight() {
	final Pattern pattern = Pattern.compile("(\\d+)(in|cm)");
	final Matcher matcher = pattern.matcher(values.get("hgt"));

	if (!matcher.find()) {
	    return false;
	}

	final int heightValue = Integer.parseInt(matcher.group(1));
	final String heightUnit = matcher.group(2);

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

    public Map<String,String> getValues() {
	return values;
    }

    public void setValues(Map<String,String> values) {
	this.values = values;
    }
}
