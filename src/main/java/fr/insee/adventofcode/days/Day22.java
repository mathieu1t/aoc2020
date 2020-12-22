package fr.insee.adventofcode.days;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import fr.insee.adventofcode.utils.Utils;

public class Day22 extends Day {

    private static final List<Integer> puzzle1 = Utils.getLineIntegerList("src/main/resources/22_player1.txt");
    private static final List<Integer> puzzle2 = Utils.getLineIntegerList("src/main/resources/22_player2.txt");

    @Override
    public String part1() {
	Deque<Integer> player1 = new ArrayDeque<>(puzzle1);
	Deque<Integer> player2 = new ArrayDeque<>(puzzle2);
	int j = 0;
	while (player1.size()!=0 && player2.size()!=0) {
	    System.out.println(j++);
	    Integer card1 = player1.pollFirst();
	    Integer card2 = player2.pollFirst();
	    if (card1 > card2) {
		player1.addLast(card1);
		player1.addLast(card2);
	    } else {
		player2.addLast(card2);
		player2.addLast(card1);
	    }
	}
	int i = 1;
	int count = 0;
	Deque<Integer> gagnant = player1.size() == 0 ? player2:player1;
	while (gagnant.size() != 0) {
	    count = count + i * gagnant.pollLast();
	    i++;
	}
	

	return String.valueOf(count);
    }

    @Override
    public String part2() {
	
	return String.valueOf("");
    }

}
