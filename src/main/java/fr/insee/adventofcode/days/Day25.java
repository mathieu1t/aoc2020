package fr.insee.adventofcode.days;

public class Day25 extends Day {

    private Integer num1 = 3248366;
    private Integer num2 = 4738476;

    @Override
    public String part1() {
	int loop1 = 0;
	int card_public_key = 1;
	while (card_public_key != num1) {
	    card_public_key = (card_public_key * 7) % 20201227;
	    loop1++;
	}
	int loop2 = 0;
	int door_public_key = 1;
	while (door_public_key != num2) {
	    door_public_key = (door_public_key * 7) % 20201227;
	    loop2++;
	}
	int count = 1;
	long encryption_key = 1;
	while (count <= loop1) {
	    encryption_key = (encryption_key * num2) % 20201227;
	    count++;
	}
	
	
	
	return String.valueOf(encryption_key);
    }

    @Override
    public String part2() {
	
	return String.valueOf("");
    }

}
