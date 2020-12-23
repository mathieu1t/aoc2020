package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day23 extends Day {

    @Override
    public String part1() {
	String cupLabeling = "364289715";

	List<String> clockwise = Arrays.asList(cupLabeling.split(""));
	List<Integer> clockwiseInt = clockwise.stream().map(i -> Integer.parseInt(i)).collect(Collectors.toList());

	for (int m = 0; m < 100; m++) {

	    int one = clockwiseInt.remove(1);
	    int two = clockwiseInt.remove(1);
	    int three = clockwiseInt.remove(1);

	    int next = clockwiseInt.get(0) - 1;
	    if (next == 0) {
		next = 9;
	    }

	    while (next == one || next == two || next == three) {
		next = next - 1;
		if (next == 0) {
		    next = 9;
		}
	    }

	    int index = clockwiseInt.indexOf(next);

	    clockwiseInt.add(index + 1, one);
	    clockwiseInt.add(index + 2, two);
	    clockwiseInt.add(index + 3, three);

	    List<Integer> nextClockwiseInt = new ArrayList<>();
	    for (int i = 1; i < clockwiseInt.size(); i++) {
		nextClockwiseInt.add(clockwiseInt.get(i));
	    }
	    nextClockwiseInt.add(clockwiseInt.get(0));
	    clockwiseInt = nextClockwiseInt;

	}

	int index1 = clockwiseInt.indexOf(1);
	String ordre = "";
	for (int i = 0; i < clockwiseInt.size(); i++) {
	    ordre = ordre + clockwiseInt.get(i);
	}
	String result = ordre.substring(index1 + 1) + ordre.substring(0, index1);

	return result;

    }

    @Override
    public String part2() {

	String cupLabeling = "364289715";

	List<String> clockwise = Arrays.asList(cupLabeling.split(""));
	List<Integer> clockwiseInt = clockwise.stream().map(i -> Integer.parseInt(i)).collect(Collectors.toList());
	for (int i = 10; i <= 1000000; i++) {
	    clockwiseInt.add(i);
	}

	CircularLinkedList cll = new CircularLinkedList();
	clockwiseInt.stream().forEach(i -> cll.addNode(i));

	Node current = cll.head;

	for (int m = 0; m < 10000000; m++) {
	    List<Integer> threeCups = new ArrayList<>();
	    Node nextPick = current.nextNode;
	    current.nextNode = nextPick.nextNode.nextNode.nextNode;
	    nextPick.nextNode.nextNode.nextNode = null;
	    threeCups.add(nextPick.value);
	    threeCups.add(nextPick.nextNode.value);
	    threeCups.add(nextPick.nextNode.nextNode.value);
	   
	    int next = current.value - 1;
	    if (next == 0) {
		next = 1000000;
	    }

	    while (threeCups.contains(next)) {
		next = next - 1;
		if (next == 0) {
		    next = 1000000;
		}
	    }
	  
	    Node nextNode = cll.searchNode(next);
	    
	    cll.addNodeAfter(threeCups, nextNode);
	 

	    current = current.nextNode;

	}
	 Node one = cll.searchNode(1);
	return String.valueOf((long)one.nextNode.value * one.nextNode.nextNode.value);
    }

    // inspired by https://www.baeldung.com/java-circular-linked-list

    class CircularLinkedList {
	
	Map<Integer, Node> map = new HashMap<>();

	Node head = null;
	Node tail = null;

	public void addNode(int value) {

	    Node newNode = new Node(value);

	    if (head == null) {
		head = newNode;
	    } else {
		tail.nextNode = newNode;
	    }

	    tail = newNode;
	    tail.nextNode = head;
	    
	    map.put(value, newNode);
	}

	public void addNodeAfter(List<Integer> threeCups, Node before) {
	    
	    for (Integer i : threeCups) {
		Node newNode = new Node(i);
		Node after = before.nextNode;
		before.nextNode = newNode;
		newNode.nextNode = after;
		before = newNode;
		 map.put(i, newNode);
	    }
	    
	}

	public Node searchNode(int searchValue) {

	    return map.get(searchValue);

	}

    }

    class Node {

	int value;
	Node nextNode;

	public Node(int value) {
	    this.value = value;
	}

    }

}
