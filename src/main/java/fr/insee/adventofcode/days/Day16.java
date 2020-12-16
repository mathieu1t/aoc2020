package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day16 extends Day {

    private static final List<String> puzzle = Utils.getLineStringList("src/main/resources/16.txt"); // nearby tickets

    private static final String myTicket = "131,103,109,67,127,97,89,79,163,59,73,83,61,107,53,193,167,101,71,197";

    String[] departure_location = { "37", "594", "615", "952", "departure_location" };
    String[] departure_station = { "50", "562", "573", "968", "departure_station" };
    String[] departure_platform = { "49", "584", "592", "971", "departure_platform" };
    String[] departure_track = { "28", "727", "744", "957", "departure_track" };
    String[] departure_date = { "35", "930", "943", "965", "departure_date" };
    String[] departure_time = { "38", "811", "829", "962", "departure_time" };
    String[] arrival_location = { "35", "446", "467", "950", "arrival_location" };
    String[] arrival_station = { "29", "234", "245", "969", "arrival_station" };
    String[] arrival_platform = { "47", "416", "431", "970", "arrival_platform" };
    String[] arrival_track = { "38", "134", "160", "962", "arrival_track" };
    String[] clazz = { "30", "493", "506", "953", "clazz" };
    String[] duration = { "43", "335", "346", "949", "duration" };
    String[] price = { "33", "635", "654", "953", "price" };
    String[] route = { "43", "399", "410", "974", "route" };
    String[] row = { "32", "848", "854", "951", "row" };
    String[] seat = { "36", "777", "788", "965", "seat" };
    String[] train = { "35", "109", "122", "969", "train" };
    String[] type = { "38", "673", "694", "960", "type" };
    String[] wagon = { "50", "168", "193", "971", "wagon" };
    String[] zone = { "46", "215", "232", "954", "zone" };
    String[][] tabs = { departure_location, departure_station, departure_platform, departure_track, departure_date,
	    departure_time, arrival_location, arrival_station, arrival_platform, arrival_track, clazz, duration, price,
	    route, row, seat, train, type, wagon, zone };

    @Override
    public String part1() {
	long sum = 0;
	for (String s : puzzle) {
	    String[] split = s.split(",");
	    for (String s2 : split) {
		int l = Integer.parseInt(s2);
		// valid ranges of values 28-974
		if (l < 28 || l > 974) {
		    sum = sum + l;
		}
	    }
	}
	return String.valueOf(sum);
    }

    @Override
    public String part2() {
	List<String> validTicket = new ArrayList<>();
	for (String s : puzzle) {
	    String[] split = s.split(",");
	    boolean valid = true;
	    for (String s2 : split) {
		int l = Integer.parseInt(s2);
		// valid ranges of values 28-974
		if (l < 28 || l > 974) {
		    valid = false;
		}
	    }
	    if (valid)
		validTicket.add(s);
	}
	List<Map<Integer, Integer>> tickets = new ArrayList<>();
	validTicket.add(myTicket);
	for (String ticket : validTicket) {
	    String[] split = ticket.split(",");
	    Map<Integer, Integer> oneTicket = new HashMap<>();
	    int i = 1;
	    for (String number : split) {
		int l = Integer.parseInt(number);
		oneTicket.put(i, l);
		i++;
	    }
	    tickets.add(oneTicket);
	}
	Map<Integer, List<String>> champsPositions = new HashMap<>();
	Set<Integer> champs = tickets.get(0).keySet();
	for (Integer c : champs) {
	    List<Integer> champ = tickets.stream().map(t -> t.get(c)).collect(Collectors.toList());
	    for (String[] tab : tabs) {
		long count = champ.stream()
			.filter(ch -> (ch >= Integer.parseInt(tab[0]) && ch <= Integer.parseInt(tab[1]))
				|| (ch >= Integer.parseInt(tab[2]) && ch <= Integer.parseInt(tab[3])))
			.count();
		if (count == champ.size()) {
		    if (champsPositions.containsKey(c)) {
			List<String> list = champsPositions.get(c);
			list.add(tab[4]);
			champsPositions.put(c, list);
		    } else {
			List<String> list = new ArrayList<>();
			list.add(tab[4]);
			champsPositions.put(c, list);
		    }
		}
	    }
	}
	Map<Integer, String> champsPosition = new HashMap<>();

	while (champsPositions.size() != 0) {
	    List<String> nameChamps = new ArrayList<>();
	    for (String[] tab : tabs) {
		nameChamps.add(Arrays.stream(tab).map(t -> tab[4]).findFirst().get());
	    }
	    champsPosition.entrySet().stream().forEach(c -> champsPositions.remove(c.getKey()));
	    for (String name : nameChamps) {
		List<Integer> list = champsPositions.entrySet().stream().filter(c -> c.getValue().contains(name))
			.map(c -> c.getKey()).collect(Collectors.toList());
		if (list.size() == 1) {
		    champsPosition.put(list.get(0), name);
		}
	    }
	}
	List<Integer> departure = champsPosition.entrySet().stream().filter(c -> c.getValue().contains("departure")).map(c -> c.getKey()).collect(Collectors.toList());
	long multi = 1;
	for (int i : departure) {
	    String[] s = myTicket.split(",");
	    multi = multi * Long.parseLong(s[i-1]);
	}
	return String.valueOf(multi);
    }

}
