package fr.insee.adventofcode.days;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day12Refactor extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/12.txt");

    @Override
    public String part1() {
	List<NavigationInstruction> instructions = Arrays.stream(puzzle).map(p -> new NavigationInstruction(p)).collect(Collectors.toList());
	ShipPosition shipPosition = new ShipPosition(Action.E,0,0);
	instructions.stream().forEach(i -> shipPosition.moveShip(i,false));
	return String.valueOf(Math.abs(shipPosition.x) + Math.abs(shipPosition.y));
    }


    @Override
    public String part2() {
	List<NavigationInstruction> instructions = Arrays.stream(puzzle).map(p -> new NavigationInstruction(p)).collect(Collectors.toList());
	ShipPosition shipPosition = new ShipPosition(0,0,10,-1);
	instructions.stream().forEach(i -> shipPosition.moveShip(i, true));
	return String.valueOf(Math.abs(shipPosition.x) + Math.abs(shipPosition.y));
    }
    
    enum Action {
	    E,W,N,S,L,R,F
	}
    
    class NavigationInstruction {

	    public Action action;
	    public int value;
	    
	    public NavigationInstruction(String instruction) {
		this.action = Action.valueOf(instruction.substring(0, 1));;
		this.value = Integer.parseInt(instruction.substring(1));
	    }
	    
	    
	}
    
    class ShipPosition {

	    public Action shipDirection;
	    public int x; // E-W
	    public int y; // N-S
	    public int wpX; // waypoint E-W
	    public int wpY; // waypoint N-S

	    public ShipPosition(Action direction, int x, int y) {
		this.shipDirection = direction;
		this.x = x;
		this.y = x;
	    }
	    
	    public ShipPosition(int x, int y, int wpX, int wpY) {
		this.x = x;
		this.y = x;
		this.wpX = wpX;
		this.wpY = wpY;
	    }

	    public void moveShip(NavigationInstruction nav, boolean wayPoint) {
		switch (nav.action) {
		case N:
		    if (wayPoint) {
			wpY = wpY - nav.value;
		    } else {
			y = y - nav.value;
		    }
		    break;
		case S:
		    if (wayPoint) {
			wpY = wpY + nav.value;
		    } else {
			y = y + nav.value;
		    }
		    break;
		case E:
		    if (wayPoint) {
			wpX = wpX + nav.value;
		    } else {
			x = x + nav.value;
		    }
		    break;
		case W:
		    if (wayPoint) {
			wpX = wpX - nav.value;
		    } else {
			x = x - nav.value;
		    }
		    break;
		case L:
		case R:
		    changeDirection(nav, wayPoint);
		    break;
		case F:
		    if (wayPoint) {
			x = x + wpX * nav.value;
			y = y + wpY * nav.value;
		    } else {
			switch (shipDirection) {
			case N:
			    y = y - nav.value;
			    break;
			case S:
			    y = y + nav.value;
			    break;
			case E:
			    x = x + nav.value;
			    break;
			case W:
			    x = x - nav.value;
			    break;
			default:
			    // code block
			}
			break;
		    }
		default:
		    // code block
		}
	    }

	    private void changeDirection(NavigationInstruction nav, boolean wayPoint) {
		int turn = nav.value / 90;
		for (int t = 0; t < turn; t++) {
		    if (wayPoint) {
			if (nav.action == Action.R) {
			    int temp = wpY;
			    wpY = wpX;
			    wpX = -temp;
			} else {
			    int temp = wpX;
			    wpX = wpY;
			    wpY = -temp;
			}
		    } else {
			switch (shipDirection) {
			case N:
			    if (nav.action == Action.L) {
				shipDirection = Action.W;
			    } else {
				shipDirection = Action.E;
			    }
			    break;
			case S:
			    if (nav.action == Action.L) {
				shipDirection = Action.E;
			    } else {
				shipDirection = Action.W;
			    }
			    break;
			case E:
			    if (nav.action == Action.L) {
				shipDirection = Action.N;
			    } else {
				shipDirection = Action.S;
			    }
			    break;
			case W:
			    if (nav.action == Action.L) {
				shipDirection = Action.S;
			    } else {
				shipDirection = Action.N;
			    }
			    break;
			default:
			    // code block
			}
		    }
		}
	    }
	}

}
