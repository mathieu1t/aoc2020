package fr.insee.adventofcode.days;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day14 extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/14.txt");

    @Override
    public String part1() {
	List<Program> programs = new ArrayList<>();
	Program program = null;
	for (int i = 0; i < puzzle.length; i++) {
	    String[] split = puzzle[i].split("=");
	    if ("mask".equals(split[0].trim())) {
		if (program != null) {
		    programs.add(program);
		}
		program = new Program();
		program.mask = split[1].trim();
		program.mems = new ArrayList<Mem>();
	    } else {
		Mem mem = new Mem();
		mem.value = Integer.parseInt(split[1].trim());
		Pattern pattern = Pattern.compile("mem\\[(\\d+)\\]");
		Matcher matcher = pattern.matcher(split[0].trim());
		matcher.find();
		mem.mem = Integer.parseInt(matcher.group(1));
		program.mems.add(mem);
	    }
	}
	programs.add(program);
	Map<Integer, Long> memoire = new HashMap<>();
	for (Program p : programs) {
	    for (Mem m : p.mems) {
		Long result = m.applyMask(p.mask);
		memoire.put(m.mem, result);
	    }
	}
	Long count = memoire.values().stream().reduce(Long::sum).get();
	return String.valueOf(count);
    }

    @Override
    public String part2() {
	List<Program> programs = new ArrayList<>();
	Program program = null;
	for (int i = 0; i < puzzle.length; i++) {
	    String[] split = puzzle[i].split("=");
	    if ("mask".equals(split[0].trim())) {
		if (program != null) {
		    programs.add(program);
		}
		program = new Program();
		program.mask = split[1].trim();
		program.mems = new ArrayList<Mem>();
	    } else {
		Mem mem = new Mem();
		mem.value = Integer.parseInt(split[1].trim());
		Pattern pattern = Pattern.compile("mem\\[(\\d+)\\]");
		Matcher matcher = pattern.matcher(split[0].trim());
		matcher.find();
		mem.mem = Integer.parseInt(matcher.group(1));
		program.mems.add(mem);
	    }
	}
	programs.add(program);
	Map<Long, Long> memoire = new HashMap<>();
	for (Program p : programs) {
	    for (Mem m : p.mems) {
		String result = m.applyMask2(p.mask);
		List<String> comb = new ArrayList<>();
		getCombinaison(result, comb);
		
		List<Long> results = comb.stream().map(ma -> new BigInteger(ma, 2).longValue()).collect(Collectors.toList());
		results.stream().forEach(r -> memoire.put(r, Long.parseLong(m.value+"")));		
	    }
	}
	Long count = memoire.values().stream().reduce(Long::sum).get();
	return String.valueOf(count);
    }

    class Program {
	public String mask;
	public List<Mem> mems;

    }

    class Mem {
	public Integer mem;
	public Integer value;

	public Long applyMask(String mask) {
	    char[] bin36 = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
		    '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };
	    char[] bin = Integer.toBinaryString(value).toCharArray();
	    for (int i = 0; i < bin.length; i++) {
		int index = mask.length() - bin.length + i;
		bin36[index] = bin[i];
	    }

	    char[] maskTab = mask.toCharArray();
	    for (int i = 0; i < maskTab.length; i++) {
		if (maskTab[i] != 'X') {
		    bin36[i] = maskTab[i];
		}
	    }
	    StringBuilder result = new StringBuilder();
	    for (int i = 0; i < bin36.length; i++) {
		result.append(bin36[i]);
	    }
	    return new BigInteger(result.toString(), 2).longValue();
	}
	
	public String applyMask2(String mask) {
	    char[] bin36 = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
		    '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };
	    char[] bin = Integer.toBinaryString(mem).toCharArray();
	    for (int i = 0; i < bin.length; i++) {
		int index = mask.length() - bin.length + i;
		bin36[index] = bin[i];
	    }

	    char[] maskTab = mask.toCharArray();
	    for (int i = 0; i < maskTab.length; i++) {
		if (maskTab[i] != '0') {
		    bin36[i] = maskTab[i];
		}
	    }
	    StringBuilder result = new StringBuilder();
	    for (int i = 0; i < bin36.length; i++) {
		result.append(bin36[i]);
	    }
	    return result.toString();
	}

    }

    public void getCombinaison(String mask, List<String> masks) {
	if (!mask.contains("X")) {
	    masks.add(mask);
	    return;
	} else {
	    getCombinaison(mask.replaceFirst("X", "0"), masks);
	    getCombinaison(mask.replaceFirst("X", "1"), masks);
	}
    }

}
