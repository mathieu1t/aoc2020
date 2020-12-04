package fr.insee.adventofcode.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    
    public static String[][] getLineString(String input, String separateur) {
    	return getLine(input).map(ligne -> ligne.split(separateur)).toArray(String[][]::new);
    }
    
    public static Integer[][] getLineInteger(String input, String separateur) {
    	return getLine(input).map(ligne -> ligne.split(separateur)).toArray(Integer[][]::new);
    }

}
