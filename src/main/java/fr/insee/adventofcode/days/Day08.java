package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.insee.adventofcode.model.Image;
import fr.insee.adventofcode.model.Layer;
import fr.insee.adventofcode.utils.Utils;

public class Day08 extends Day {

    @Override
    public String part1(String filepath, Object... params) {
	Integer[] puzzle = Utils.getTabEntier(filepath, "");
	Image image = new Image();
	List<Layer> layers = new ArrayList<>();
	for (int i = 0; i < puzzle.length; i += 25 * 6) {
	    List<Integer> pixels = Arrays.asList(puzzle).subList(i, i + 25 * 6);
	    Layer l = new Layer();
	    l.setPixels(pixels);
	    layers.add(l);
	}
	image.setLayers(layers);
	int min = Integer.MAX_VALUE;
	int result = 0;
	for (Layer layer : image.getLayers()) {
	    int count0 = (int) layer.getPixels().stream().filter(n -> n.equals(0)).count();
	    if (count0 < min) {
		min = count0;
		int count1 = (int) layer.getPixels().stream().filter(n -> n.equals(1)).count();
		int count2 = (int) layer.getPixels().stream().filter(n -> n.equals(2)).count();
		result = count1 * count2;
	    }
	}
	return String.valueOf(result);

    }

    @Override
    public String part2(String filepath, Object... params) {
	Integer[] puzzle = Utils.getTabEntier(filepath, "");
	Image image = new Image();
	List<Layer> layers = new ArrayList<>();
	for (int i = 0; i < puzzle.length; i += 25 * 6) {
	    List<Integer> pixels = Arrays.asList(puzzle).subList(i, i + 25 * 6);
	    Layer l = new Layer();
	    l.setPixels(pixels);
	    layers.add(l);
	}
	image.setLayers(layers);
	System.out.println();
	for (int i=0; i < 6; i++) {
		String row = "        ";
		for (int j=0; j < 25; j++) {
			row += image.afficherPixel(i, j);
		}
		System.out.println(row);
	}
	System.out.println();
	return String.valueOf(0);

    }

}
