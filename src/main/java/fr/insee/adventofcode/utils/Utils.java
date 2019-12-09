package fr.insee.adventofcode.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Utils {

	public static Stream<String> getLignes(String input) {
		Stream<String> stream = null;
		try {
			stream = Files.lines(Paths.get(input));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream;
	}
	
	public static String[] getLignesTab(String input) {
		return getLignes(input).toArray(String[]::new);
	}

	public static Integer[] getLignesEntier(String input) {
		return getLignes(input).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
	}
	
	public static String[][] getTab(String input) {
		return getLignes(input).map(ligne -> ligne.split(",")).toArray(String[][]::new);
	}
	
	public static Integer[] getTabEntier(String input,String separateur) {
		String[] s = getLignes(input).findFirst().orElse("").split(separateur);
		return Arrays.stream(s).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
	}
	
}
