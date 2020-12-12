package fr.insee.adventofcode.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public static Stream<String> getLine(String input) {
        Stream<String> stream = null;
        try {
            stream = Files.lines(Paths.get(input));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }

    public static String[] getLineString(String input) {
        return getLine(input).toArray(String[]::new);
    }

    public static Integer[] getLineInteger(String input) {
        return getLine(input).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
    }
    
    public static Long[] getLineLong(String input) {
        return getLine(input).mapToLong(Long::parseLong).boxed().toArray(Long[]::new);
    }
    
    public static List<String> getLineStringList(String input) {
        return getLine(input).collect(Collectors.toList());
    }

    public static List<Integer> getLineIntegerList(String input) {
        return getLine(input).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
    }
    
    public static List<Long> getLineLongList(String input) {
        return getLine(input).mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
    }
    
    public static String[][] getLineString(String input, String separateur) {
    	return getLine(input).map(ligne -> ligne.split(separateur)).toArray(String[][]::new);
    }
    
    public static Integer[][] getLineInteger(String input, String separateur) {
    	return getLine(input).map(ligne -> ligne.split(separateur)).toArray(Integer[][]::new);
    }

}
