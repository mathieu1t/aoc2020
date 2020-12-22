package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import fr.insee.adventofcode.utils.Utils;

public class Day20 extends Day {

    private static final List<String> puzzle = Utils.getLineStringList("src/main/resources/20.txt");

    @Override
    public String part1() {
	List<Tile> tiles = new ArrayList<>();
	Tile tile = new Tile();
	int i = 0;
	for (String line : puzzle) {
	    if (line.contains("Tile")) {
		tile = new Tile();
		tile.number = Long.parseLong(line.substring(5, line.indexOf(":")));
		i = 0;
	    } else if (StringUtils.isBlank(line)) {
		tile.getBorders();
		tiles.add(tile);
	    } else {
		tile.lines[i] = line.toCharArray();
		i++;
	    }
	}
	tile.getBorders();
	tiles.add(tile);
	Map<Tile, List<Tile>> tilesMap = new HashMap<>();
	tiles.stream().forEach(t -> tilesMap.put(t, new ArrayList<>()));
	for (Tile t : tilesMap.keySet()) {
	    List<Tile> list = tiles.stream().filter(f -> !f.equals(t) && f.adjacent(t)).collect(Collectors.toList());
	    tilesMap.put(t, list);
	}

	long count = tilesMap.entrySet().stream().filter(e -> e.getValue().size() == 2)
		.mapToLong(e -> e.getKey().number).reduce(1, (a, b) -> a * b);

	return String.valueOf(count);

    }

    @Override
    public String part2() {
	Map<Integer, HashMap<Orientation, char[][]>> orientations = new HashMap<>();
	char[][] normal = new char[10][10];
	int numberTile = -1;
	int i = 0;
	for (String line : puzzle) {
	    if (line.contains("Tile")) {
		numberTile = Integer.parseInt(line.substring(5, line.indexOf(":")));
		orientations.put(numberTile, new HashMap<>());
		i = 0;
	    } else if (StringUtils.isBlank(line)) {
		orientations.get(numberTile).put(Orientation.NORMAL, normal);
		normal = new char[10][10];
	    } else {
		normal[i] = line.toCharArray();
		i++;
	    }
	}
	orientations.get(numberTile).put(Orientation.NORMAL, normal);
	Map<Integer, List<String>> borders = new HashMap<>();
	for (Integer number : orientations.keySet()) {
	    getOrientations(orientations.get(number));
	    List<String> liste = getBorders(orientations.get(number).get(Orientation.NORMAL));
	    borders.put(number, liste);
	}

	Map<Integer, List<Integer>> noeuds = getNoeuds(borders);

	List<Integer> corners = noeuds.entrySet().stream().filter(e -> e.getValue().size() == 2).map(e -> e.getKey())
		.collect(Collectors.toList());

	int length = (int) Math.sqrt(noeuds.size());

	// premier coin
	HashMap<Integer, int[]> placed = new HashMap<>();
	Integer firstCorner = corners.get(0);
	placed.put(firstCorner, new int[] { 0, 0 });
	// et ces 2 cotés
	Integer firstBord = noeuds.get(firstCorner).get(0);
	placed.put(firstBord, new int[] { 0, 1 });
	Integer secondBord = noeuds.get(firstCorner).get(1);
	placed.put(secondBord, new int[] { 1, 0 });

	HashMap<Orientation, char[][]> fcO = orientations.get(firstCorner);
	HashMap<Orientation, char[][]> fbO = orientations.get(firstBord);
	HashMap<Orientation, char[][]> sbO = orientations.get(secondBord);

	Map<Integer, Orientation> tilesOrientation = new HashMap<>();
	boolean isFind = false;
	for (Orientation o1 : fcO.keySet()) {
	    for (Orientation o2 : fbO.keySet()) {
		for (Orientation o3 : sbO.keySet()) {
		    char[][] c1 = fcO.get(o1);
		    char[][] c2 = fbO.get(o2);
		    char[][] c3 = sbO.get(o3);
		    if (getLeft(c1).equals(getRight(c2)) && getBottom(c1).equals(getTop(c3))) {
			tilesOrientation.put(firstCorner, o1);
			tilesOrientation.put(firstBord, o2);
			tilesOrientation.put(secondBord, o3);
			isFind = true;
			break;
		    }
		}
		if (isFind)
		    break;
	    }
	    if (isFind)
		break;
	}

	List<Integer> notPlaced = new ArrayList<>();
	for (Integer id : noeuds.keySet()) {
	    if (!placed.containsKey(id)) {
		notPlaced.add(id);
	    }
	}

	int[] nextPlace = nextPlace(placed, length);
	while (notPlaced.size() != 0) {
	    int countPlaced = placed.size();
	    int count = 0;
	    while (countPlaced == placed.size()) {
		Integer previousId = placed.keySet().stream().collect(Collectors.toList()).get(count++);
		Orientation previousO = tilesOrientation.get(previousId);
		char[][] previous = orientations.get(previousId).get(previousO);
		List<Integer> previousAdj = noeuds.get(previousId);

		for (Integer adj : previousAdj) {
		    isFind = false;
		    if (notPlaced.contains(adj)) {
			HashMap<Orientation, char[][]> os = orientations.get(adj);
			for (Orientation o : os.keySet()) {
			    char[][] c = orientations.get(adj).get(o);
			    if (nextPlace[0] == placed.get(previousId)[0]) {
				if (getLeft(previous).equals(getRight(c))) {
				    tilesOrientation.put(adj, o);
				    placed.put(adj, nextPlace);
				    notPlaced.remove(adj);
				    isFind = true;
				    break;
				}
			    }
			    if (nextPlace[1] == placed.get(previousId)[1]) {
				if (getBottom(previous).equals(getTop(c))) {
				    tilesOrientation.put(adj, o);
				    placed.put(adj, nextPlace);
				    notPlaced.remove(adj);
				    isFind = true;
				    break;
				}
			    }
			}
		    }
		    if (isFind) {
			break;
		    }
		}
	    }
	    nextPlace = nextPlace(placed, length);
	}

	char[][] image = createImage(placed, tilesOrientation, length, orientations);
	HashMap<Orientation, char[][]> orientationsImage = new HashMap<>();
	orientationsImage.put(Orientation.NORMAL, image);
	getOrientations(orientationsImage);
	char[][] goodOrientation = new char[length * 8][length * 8];
	for (Orientation o : orientationsImage.keySet()) {
	    if (findMonster(orientationsImage.get(o))) {
		goodOrientation = orientationsImage.get(o);
		break;
	    }
	}

	markMonster(goodOrientation);
	
	long count = 0;
	for (char[] ct : goodOrientation) {
	    for (char c : ct) {
		if (c == '#') {
		    count++;
		}
	    }
	}

	return String.valueOf(count);
    }

    private void markMonster(char[][] cs) {
	for (int i = 0; i < cs.length - 2; i++) {
	    for (int j = 18; j < cs.length - 1; j++) {
		if (cs[i][j] == '#' && cs[i + 1][j - 18] == '#' && cs[i + 1][j - 13] == '#' && cs[i + 1][j - 12] == '#'
			&& cs[i + 1][j - 7] == '#' && cs[i + 1][j - 6] == '#' && cs[i + 1][j - 1] == '#'
			&& cs[i + 1][j] == '#' && cs[i + 1][j + 1] == '#' && cs[i + 2][j - 17] == '#'
			&& cs[i + 2][j - 14] == '#' && cs[i + 2][j - 11] == '#' && cs[i + 2][j - 8] == '#'
			&& cs[i + 2][j - 5] == '#' && cs[i + 2][j - 2] == '#') {
		    cs[i][j] = 'O';
		    cs[i + 1][j - 18] = 'O';
		    cs[i + 1][j - 13] = 'O';
		    cs[i + 1][j - 12] = 'O';
		    cs[i + 1][j - 7] = 'O';
		    cs[i + 1][j - 6] = 'O';
		    cs[i + 1][j - 1] = 'O';
		    cs[i + 1][j] = 'O';
		    cs[i + 1][j + 1] = 'O';
		    cs[i + 2][j - 17] = 'O';
		    cs[i + 2][j - 14] = 'O';
		    cs[i + 2][j - 11] = 'O';
		    cs[i + 2][j - 8] = 'O';
		    cs[i + 2][j - 5] = 'O';
		    cs[i + 2][j - 2] = 'O';
		}
	    }
	}

    }

    private boolean findMonster(char[][] cs) {
	for (int i = 0; i < cs.length - 2; i++) {
	    for (int j = 18; j < cs.length - 1; j++) {
		if (cs[i][j] == '#') {
		    if (cs[i + 1][j - 18] == '#' && cs[i + 1][j - 13] == '#' && cs[i + 1][j - 12] == '#'
			    && cs[i + 1][j - 7] == '#' && cs[i + 1][j - 6] == '#' && cs[i + 1][j - 1] == '#'
			    && cs[i + 1][j] == '#' && cs[i + 1][j + 1] == '#') {
			if (cs[i + 2][j - 17] == '#' && cs[i + 2][j - 14] == '#' && cs[i + 2][j - 11] == '#'
				&& cs[i + 2][j - 8] == '#' && cs[i + 2][j - 5] == '#' && cs[i + 2][j - 2] == '#') {
			    return true;
			}
		    }
		}
	    }
	}
	return false;
    }

    private char[][] createImage(HashMap<Integer, int[]> placed, Map<Integer, Orientation> tilesOrientation, int length,
	    Map<Integer, HashMap<Orientation, char[][]>> orientations) {
	char[][] image = new char[length * 8][length * 8];
	for (Integer p : placed.keySet()) {
	    int[] place = placed.get(p);
	    Orientation o = tilesOrientation.get(p);
	    char[][] tile = orientations.get(p).get(o);
	    for (int i = 1; i < 9; i++) {
		for (int j = 1; j < 9; j++) {
		    image[i + 8 * place[0] - 1][j + 8 * place[1] - 1] = tile[i][j];
		}
	    }
	}

	return image;
    }

    private String getBottom(char[][] c) {
	String bottom = "";
	for (int i = 0; i < 10; i++) {
	    bottom += c[9][i];
	}
	return bottom;
    }

    private String getTop(char[][] c) {
	String top = "";
	for (int i = 0; i < 10; i++) {
	    top += c[0][i];
	}
	return top;
    }

    private String getLeft(char[][] c) {
	String left = "";
	for (int i = 0; i < 10; i++) {
	    left += c[i][9];
	}
	return left;
    }

    private String getRight(char[][] c) {
	String right = "";
	for (int i = 0; i < 10; i++) {
	    right += c[i][0];
	}
	return right;
    }

    private List<String> getBorders(char[][] c) {
	List<String> borders = new ArrayList<>();
	String top = "";
	String left = "";
	String right = "";
	String bottom = "";
	for (int i = 0; i < 10; i++) {
	    top += c[0][i];
	    left += c[i][0];
	    right += c[i][9];
	    bottom += c[9][i];
	}
	String reverseTop = (new StringBuffer(top)).reverse().toString();
	String reverseBottom = (new StringBuffer(bottom)).reverse().toString();
	String reverseLeft = (new StringBuffer(left)).reverse().toString();
	String reverseRight = (new StringBuffer(right)).reverse().toString();
	borders.add(top);
	borders.add(bottom);
	borders.add(left);
	borders.add(right);
	borders.add(reverseTop);
	borders.add(reverseBottom);
	borders.add(reverseLeft);
	borders.add(reverseRight);
	return borders;
    }

    private void getOrientations(HashMap<Orientation, char[][]> hashMap) {
	char[][] normal = hashMap.get(Orientation.NORMAL);
	char[][] reverseXNormal = getReverseX(normal);
	hashMap.put(Orientation.REVERSEX_NORMAL, reverseXNormal);
	char[][] reverseYNormal = getReverseY(normal);
	hashMap.put(Orientation.REVERSEY_NORMAL, reverseYNormal);
	char[][] angle90 = getRotation(normal);
	hashMap.put(Orientation.ANGLE90, angle90);
	char[][] angle180 = getRotation(angle90);
	hashMap.put(Orientation.ANGLE180, angle180);
	char[][] angle270 = getRotation(angle180);
	hashMap.put(Orientation.ANGLE270, angle270);
	char[][] revXangle90 = getRotation(reverseXNormal);
	hashMap.put(Orientation.REVERSEX_ANGLE90, revXangle90);
	char[][] revXangle180 = getRotation(revXangle90);
	hashMap.put(Orientation.REVERSEX_ANGLE180, revXangle180);
	char[][] revXangle270 = getRotation(revXangle180);
	hashMap.put(Orientation.REVERSEX_ANGLE270, revXangle270);
	char[][] revYangle90 = getRotation(reverseYNormal);
	hashMap.put(Orientation.REVERSEY_ANGLE90, revYangle90);
	char[][] revYangle180 = getRotation(revYangle90);
	hashMap.put(Orientation.REVERSEY_ANGLE180, revYangle180);
	char[][] revYangle270 = getRotation(revYangle180);
	hashMap.put(Orientation.REVERSEY_ANGLE270, revYangle270);
    }

    private char[][] getRotation(char[][] c) {
	int n = c.length;
	char[][] newChar = new char[n][n];
	for (int j = 0; j < n; j++) {
	    for (int k = 0; k < n; k++) {
		newChar[k][n - 1 - j] = c[j][k];
	    }
	}
	return newChar;
    }

    private char[][] getReverseY(char[][] c) {
	int n = c.length;
	char[][] newChar = new char[n][n];
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		newChar[i][n - 1 - j] = c[i][j];
	    }
	}
	return newChar;
    }

    private char[][] getReverseX(char[][] c) {
	int n = c.length;
	char[][] newChar = new char[n][n];
	for (int i = 0; i < n; i++) {
	    newChar[n - 1 - i] = c[i];
	}
	return newChar;
    }

    private int[] nextPlace(HashMap<Integer, int[]> placed, int length) {
	for (int x = 0; x < length; x++) {
	    for (int y = 0; y < length; y++) {
		boolean deja = false;
		for (int[] i : placed.values()) {
		    if (Arrays.equals(i, new int[] { x, y })) {
			deja = true;
			break;
		    }
		}
		if (!deja) {
		    return new int[] { x, y };
		}

	    }
	}

	return null;
    }

    private Map<Integer, List<Integer>> getNoeuds(Map<Integer, List<String>> borders) {
	Map<Integer, List<Integer>> noeuds = new HashMap<>();
	for (Integer i : borders.keySet()) {
	    noeuds.put(i, new ArrayList<>());
	}
	for (Integer t1 : borders.keySet()) {
	    List<String> bordersT1 = borders.get(t1);
	    for (Integer t2 : borders.keySet()) {
		if (t1 == t2) {
		    continue;
		}
		List<String> bordersT2 = borders.get(t2);
		for (int i = 0; i < bordersT1.size(); i++) {
		    if (bordersT2.contains(bordersT1.get(i))) {
			noeuds.get(t1).add(t2);
			break;
		    }
		}
	    }
	}
	return noeuds;
    }

    enum Orientation {
	NORMAL, ANGLE90, ANGLE180, ANGLE270, REVERSEX_NORMAL, REVERSEX_ANGLE90, REVERSEX_ANGLE180, REVERSEX_ANGLE270,
	REVERSEY_NORMAL, REVERSEY_ANGLE90, REVERSEY_ANGLE180, REVERSEY_ANGLE270
    }

    enum Arrangement {
	TOP, RIGHT, BOTTOM, LEFT, REVERSE_TOP, REVERSE_RIGHT, REVERSE_BOTTOM, REVERSE_LEFT
    }

    class Tile {
	long number;
	char[][] lines = new char[10][10];
	String top = "";
	String bottom = "";
	String left = "";
	String right = "";
	String reverseTop = "";
	String reverseBottom = "";
	String reverseLeft = "";
	String reverseRight = "";

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + Objects.hash(number);
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    Tile other = (Tile) obj;
	    return number == other.number;
	}

	HashMap<String, Arrangement> allBorders = new HashMap<>();

	public void getBorders() {
	    for (int i = 0; i < 10; i++) {
		this.top += lines[0][i];
		this.left += lines[i][0];
		this.right += lines[i][9];
		this.bottom += lines[9][i];
	    }
	    this.reverseTop = (new StringBuffer(this.top)).reverse().toString();
	    this.reverseBottom = (new StringBuffer(this.bottom)).reverse().toString();
	    this.reverseLeft = (new StringBuffer(this.left)).reverse().toString();
	    this.reverseRight = (new StringBuffer(this.right)).reverse().toString();
	    allBorders.put(this.top, Arrangement.TOP);
	    allBorders.put(this.bottom, Arrangement.BOTTOM);
	    allBorders.put(this.left, Arrangement.LEFT);
	    allBorders.put(this.right, Arrangement.RIGHT);
	    allBorders.put(this.reverseTop, Arrangement.REVERSE_TOP);
	    allBorders.put(this.reverseBottom, Arrangement.REVERSE_BOTTOM);
	    allBorders.put(this.reverseLeft, Arrangement.REVERSE_LEFT);
	    allBorders.put(this.reverseRight, Arrangement.REVERSE_RIGHT);
	}

	public boolean adjacent(Tile tile) {
	    Set<String> result = this.allBorders.keySet().stream().filter(tile.allBorders.keySet()::contains)
		    .collect(Collectors.toSet());
	    return result.size() >= 1;
	}

    }

}
