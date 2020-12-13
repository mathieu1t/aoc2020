package fr.insee.adventofcode.days;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day13Refactor extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/13.txt");

    @Override
    public String part1() {
	long horodatageMin = Long.parseLong(puzzle[0]);
	List<Long> idBus = Arrays.stream(puzzle[1].split(",")).filter(p -> !"x".equals(p)).map(p -> Long.parseLong(p))
		.collect(Collectors.toList());
	List<Long> minDiff= idBus.stream().map(p -> p - (horodatageMin % p)).collect(Collectors.toList());
	int minIndex = minDiff.indexOf(Collections.min(minDiff));
	return String.valueOf(minDiff.get(minIndex) * idBus.get(minIndex));
    }

    @Override
    public String part2() {
	// Théorème des restes chinois
	List<String> idBus = Arrays.stream(puzzle[1].split(",")).collect(Collectors.toList());
	List<Integer> position = idBus.stream().filter(p -> !"x".equals(p)).map(p -> idBus.indexOf(p))
		.collect(Collectors.toList());
	List<BigInteger> horodatages = idBus.stream().filter(p -> !"x".equals(p)).map(p -> new BigInteger(p))
		.collect(Collectors.toList());
	BigInteger M = horodatages.stream().reduce(BigInteger::multiply).get();
	List<BigInteger> mi = horodatages.stream().map(b -> M.divide(b)).collect(Collectors.toList());
	List<BigInteger> yi = new ArrayList<>();
	for (int i = 0; i < mi.size(); i++) {
	    BigInteger y = mi.get(i).modInverse(horodatages.get(i));
	    yi.add(y);
	}
	long t = 0;
	for (int i = 0; i < horodatages.size(); i++) {
	    long reste = position.get(i).longValue();
	    long m = mi.get(i).longValue();
	    long y = yi.get(i).longValue();
	    t = t + reste * m * y;
	}
	return String.valueOf(M.longValue() - (t % M.longValue()));
    }
}
