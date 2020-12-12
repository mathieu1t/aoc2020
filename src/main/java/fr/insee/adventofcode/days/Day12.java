package fr.insee.adventofcode.days;

import fr.insee.adventofcode.utils.Utils;

public class Day12 extends Day {

    private static final String[] puzzle = Utils.getLineString("src/main/resources/12.txt");

    @Override
    public String part1() {
	char face = 'E';
	char directionEO = 'E';
	int distanceEO = 0;
	char directionNS = ' ';
	int distanceNS = 0;
	for (String s : puzzle) {
	    char direction = s.charAt(0);
	    int distance = Integer.parseInt(s.substring(1));
	    switch (direction) {
	    case 'N':
	    case 'S':
		if (directionNS == direction) {
		    distanceNS = distanceNS + distance;
		} else {
		    directionNS = direction;
		    distanceNS = distance - distanceNS;
		}
		break;
	    case 'E':
	    case 'W':
		if (directionEO == direction) {
		    distanceEO = distanceEO + distance;
		} else {
		    directionEO = direction;
		    distanceEO = distance - distanceEO;
		}
		break;
	    case 'L':
	    case 'R':
		face = getFace(face, direction, distance);
		break;
	    case 'F':
		if (face == 'E' || face == 'W') {
		    if (directionEO == face) {
			distanceEO = distanceEO + distance;
		    } else {
			directionEO = face;
			distanceEO = distance - distanceEO;
		    }
		} else if (face == 'N' || face == 'S') {
		    if (directionNS == face) {
			distanceNS = distanceNS + distance;
		    } else {
			directionNS = face;
			distanceNS = distance - distanceNS;
		    }
		}
		break;
	    default:
		// code block
	    }
	}
	return String.valueOf(Math.abs(distanceEO) + Math.abs(distanceNS));
    }

    private char getFace(char face, char direction, int distance) {
	if (direction == 'R') {
	    if (face == 'E') {
		if (distance == 90)
		    return 'S';
		if (distance == 180)
		    return 'W';
		if (distance == 270)
		    return 'N';
	    } else if (face == 'W') {
		if (distance == 90)
		    return 'N';
		if (distance == 180)
		    return 'E';
		if (distance == 270)
		    return 'S';
	    } else if (face == 'N') {
		if (distance == 90)
		    return 'E';
		if (distance == 180)
		    return 'S';
		if (distance == 270)
		    return 'W';
	    } else if (face == 'S') {
		if (distance == 90)
		    return 'W';
		if (distance == 180)
		    return 'N';
		if (distance == 270)
		    return 'E';
	    }
	} else if (direction == 'L') {
	    if (face == 'E') {
		if (distance == 90)
		    return 'N';
		if (distance == 180)
		    return 'W';
		if (distance == 270)
		    return 'S';
	    } else if (face == 'W') {
		if (distance == 90)
		    return 'S';
		if (distance == 180)
		    return 'E';
		if (distance == 270)
		    return 'N';
	    } else if (face == 'N') {
		if (distance == 90)
		    return 'W';
		if (distance == 180)
		    return 'S';
		if (distance == 270)
		    return 'E';
	    } else if (face == 'S') {
		if (distance == 90)
		    return 'E';
		if (distance == 180)
		    return 'N';
		if (distance == 270)
		    return 'W';
	    }
	}
	return face;
    }

    @Override
    public String part2() {
	// waypoint
	char directionEO = 'E';
	int unitEO = 10;
	char directionNS = 'N';
	int unitNS = 1;

	// position
	char positionEO = 'E';
	int distEO = 0;
	char positionNS = 'N';
	int distNS = 0;

	for (String s : puzzle) {
	    char direction = s.charAt(0);
	    int distance = Integer.parseInt(s.substring(1));
	    switch (direction) {
	    case 'N':
	    case 'S':
		if (directionNS == direction) {
		    unitNS = unitNS + distance;
		} else {
		    directionNS = direction;
		    unitNS = distance - unitNS;
		}
		
		break;
	    case 'E':
	    case 'W':
		if (directionEO == direction) {
		    unitEO = unitEO + distance;
		} else {
		    directionEO = direction;
		    unitEO = distance - unitEO;
		}
		break;
	    case 'L':
	    case 'R':
		char dir1 = getFace(directionEO, direction, distance);
		char dir2 = getFace(directionNS, direction, distance);
		if (dir1 == 'E' || dir1 == 'W') {
		    directionEO = dir1;
		    directionNS = dir2;
		} else {
		    directionEO = dir2;
		    directionNS = dir1;
		}
		if (distance != 180) {
		    int temp = unitEO;
		    unitEO = unitNS;
		    unitNS = temp;
		}
		break;
	    case 'F':
		if (directionEO == positionEO) {
		    distEO = distEO + unitEO * distance;
		} else {
		    positionEO = directionEO;
		    distEO = unitEO * distance - distEO;
		}
		if (directionNS == positionNS) {
		    distNS = distNS + unitNS * distance;
		} else {
		    positionNS = directionNS;
		    distNS = unitNS * distance - distNS;
		}

		break;
	    default:
		// code block
	    }
	}
	return String.valueOf(Math.abs(distEO) + Math.abs(distNS));
    }

}
