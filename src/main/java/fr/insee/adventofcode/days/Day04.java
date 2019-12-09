package fr.insee.adventofcode.days;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import fr.insee.adventofcode.utils.Utils;

public class Day04 extends Day<Integer> {

	@Override
	public String part1(String filepath, Object... params) {
		puzzle = Utils.getTabEntier(filepath, "-");
		int de = puzzle[0];
		int a = puzzle[1];
		long nb = IntStream.range(de, a).mapToObj(i -> transformNumber(i)).filter(m -> regle(m)).count();
		return String.valueOf(nb);
	}

	@Override
	public String part2(String filepath, Object... params) {
		puzzle = Utils.getTabEntier(filepath, "-");
		int de = puzzle[0];
		int a = puzzle[1];
		long nb = IntStream.range(de, a).mapToObj(i -> transformNumber(i)).filter(m -> regle2(m)).count();
		return String.valueOf(nb);
	}
	
	public static int[] transformNumber(int number) {
        int[] retour = new int[6];
        String pattern = "[0-9]{1}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(String.valueOf(number));
        int i = 0;
        while (m.find()) { 
            retour[i] = Integer.parseInt(m.group());
            i++;
        } 
        return retour;
    }

	private static boolean regle(int[] tab) {
		boolean ok = false;
		for (int i = 0 ; i < 5 ; i++) {
			if (tab[i] > tab[i+1]) return false;
			if (tab[i] == tab[i+1]) ok = true;
		}
		return ok;
	}
	
	private static boolean regle2(int[] tab) {
		for (int i = 0 ; i < 5 ; i++) {
			if (tab[i] > tab[i+1]) return false;
		}
		Integer u1 = tab[0];
		Integer u2 = tab[1];
		Integer u3 = tab[2];
		Integer u4 = tab[3];
		Integer u5 = tab[4];
		Integer u6 = tab[5];
		if ((u1 == u2 && u2 != u3) || (u1 != u2 && u2 == u3 && u3 != u4) || (u2 != u3 && u3 == u4 && u4 != u5)
				|| (u3 != u4 && u4 == u5 && u5 != u6) || (u4 != u5 && u5 == u6)) {
			return true;
		}
        
		return false;
	}

}
