package fr.insee.adventofcode.days;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day13 extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/13.txt");

    @Override
    public String part1() {
	long horodatageMin = Long.parseLong(puzzle[0]);
	List<String> idBus = Arrays.stream(puzzle[1].split(",")).filter(p -> !"x".equals(p))
		.collect(Collectors.toList());
	String bus = "";
	long min = Long.MAX_VALUE;
	for (String id : idBus) {
	    long horodatage = 0;
	    while (horodatage <= horodatageMin) {
		horodatage = horodatage + Long.parseLong(id);
	    }
	    if (horodatage < min) {
		min = horodatage;
		bus = id;
	    }
	}
	return String.valueOf((min - horodatageMin) * Long.parseLong(bus));
    }

    @Override
    public String part2() {
	List<String> idBus = Arrays.stream(puzzle[1].split(",")).collect(Collectors.toList());
	long t = 0;
	long depart = 1;
	List<Integer> position = idBus.stream().filter(p -> !"x".equals(p)).map(p -> idBus.indexOf(p))
		.collect(Collectors.toList());
	List<Long> horodatage = idBus.stream().filter(p -> !"x".equals(p)).map(p -> Long.parseLong(p))
		.collect(Collectors.toList());
	for (int i = 0; i < horodatage.size(); i++) {
	    while ((t + position.get(i)) % horodatage.get(i) != 0) {
		t = t + depart;
	    }
	    depart = ppcm(depart, horodatage.get(i));
	}
	return String.valueOf(t);
    }

    private long ppcm(long a, long b) {
	return a * b / pgcd(a, b);
    }

    public long pgcd(long a, long b) {
	long r = 0;
	while (b != 0) {
	    r = a % b;
	    a = b;
	    b = r;
	}
	return a;
    }

}
