package fr.insee.adventofcode.days;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day22 extends Day {

    private static final List<Integer> puzzle1 = Utils.getLineIntegerList("src/main/resources/22_player1.txt");
    private static final List<Integer> puzzle2 = Utils.getLineIntegerList("src/main/resources/22_player2.txt");

    @Override
    public String part1() {
	Deque<Integer> player1 = new ArrayDeque<>(puzzle1);
	Deque<Integer> player2 = new ArrayDeque<>(puzzle2);
	while (player1.size() != 0 && player2.size() != 0) {
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
	Deque<Integer> gagnant = player1.size() == 0 ? player2 : player1;
	while (gagnant.size() != 0) {
	    count = count + i * gagnant.pollLast();
	    i++;
	}

	return String.valueOf(count);
    }

    @Override
    public String part2() {
	Deque<Integer> player1 = new ArrayDeque<>(puzzle1);
	Deque<Integer> player2 = new ArrayDeque<>(puzzle2);
	Integer gagnant = game(player1, player2);
	int i = 1;
	int count = 0;
	Deque<Integer> g = gagnant == 1 ? player1 : player2;
	while (g.size() != 0) {
	    count = count + i * g.pollLast();
	    i++;
	}

	return String.valueOf(count);
    }

    private Integer game(Deque<Integer> player1, Deque<Integer> player2) {
	Set<String> roundsPlayer = new HashSet<>();
	while (player1.size() != 0 && player2.size() != 0) {
	    if (!roundsPlayer.add((player1.toString() + player2.toString()))) {
		return 1;
	    }
	    Integer card1 = player1.pollFirst();
	    Integer card2 = player2.pollFirst();
	    if (card1 <= player1.size() && card2 <= player2.size()) {
		Deque<Integer> player1Clone = new ArrayDeque<>(player1);
		Deque<Integer> player2Clone = new ArrayDeque<>(player2);
		while (player1Clone.size() > card1) {
		    player1Clone.removeLast();
		}
		while (player2Clone.size() > card2) {
		    player2Clone.removeLast();
		}
		Integer gagnant = game(player1Clone, player2Clone);

		if (gagnant == 1) {
		    player1.addLast(card1);
		    player1.addLast(card2);
		} else {
		    player2.addLast(card2);
		    player2.addLast(card1);
		}
	    } else {
		if (card1 > card2) {
		    player1.addLast(card1);
		    player1.addLast(card2);
		} else {
		    player2.addLast(card2);
		    player2.addLast(card1);
		}
	    }
	}
	if (player1.size() == 0) {
	    return 2;
	} else {
	    return 1;
	}

    }

}
