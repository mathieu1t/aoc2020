package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import fr.insee.adventofcode.model.Direction;
import fr.insee.adventofcode.model.Point;

public class Day11 extends Day {

    static final long[] puzzle = new long[] { 3, 8, 1005, 8, 328, 1106, 0, 11, 0, 0, 0, 104, 1, 104, 0, 3, 8, 1002, 8,
	    -1, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 1001, 8, 0, 29, 1, 104, 7, 10, 3, 8, 1002, 8, -1, 10,
	    101, 1, 10, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 1001, 8, 0, 55, 1, 2, 7, 10, 1006, 0, 23, 3, 8, 102, -1, 8,
	    10, 1001, 10, 1, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 1001, 8, 0, 84, 1006, 0, 40, 1, 1103, 14, 10, 1, 1006,
	    16, 10, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 108, 1, 8, 10, 4, 10, 1002, 8, 1, 116, 1006, 0, 53, 1,
	    1104, 16, 10, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 102, 1, 8, 146, 2, 1104,
	    9, 10, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 1001, 8, 0, 172, 1006, 0, 65, 1,
	    1005, 8, 10, 1, 1002, 16, 10, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 108, 0, 8, 10, 4, 10, 102, 1, 8,
	    204, 2, 1104, 9, 10, 1006, 0, 30, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 108, 0, 8, 10, 4, 10, 102, 1,
	    8, 233, 2, 1109, 6, 10, 1006, 0, 17, 1, 2, 6, 10, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 108, 1, 8,
	    10, 4, 10, 102, 1, 8, 266, 1, 106, 7, 10, 2, 109, 2, 10, 2, 9, 8, 10, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10,
	    4, 10, 1008, 8, 1, 10, 4, 10, 1001, 8, 0, 301, 1, 109, 9, 10, 1006, 0, 14, 101, 1, 9, 9, 1007, 9, 1083, 10,
	    1005, 10, 15, 99, 109, 650, 104, 0, 104, 1, 21102, 1, 837548789788l, 1, 21101, 0, 345, 0, 1106, 0, 449,
	    21101, 0, 846801511180l, 1, 21101, 0, 356, 0, 1106, 0, 449, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3,
	    10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 1, 21101,
	    235244981271l, 0, 1, 21101, 403, 0, 0, 1105, 1, 449, 21102, 1, 206182744295l, 1, 21101, 0, 414, 0, 1105, 1,
	    449, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 0, 21102, 837896937832l, 1, 1, 21101, 0, 437, 0, 1106, 0,
	    449, 21101, 867965862668l, 0, 1, 21102, 448, 1, 0, 1106, 0, 449, 99, 109, 2, 22102, 1, -1, 1, 21101, 40, 0,
	    2, 21102, 1, 480, 3, 21101, 0, 470, 0, 1106, 0, 513, 109, -2, 2106, 0, 0, 0, 1, 0, 0, 1, 109, 2, 3, 10, 204,
	    -1, 1001, 475, 476, 491, 4, 0, 1001, 475, 1, 475, 108, 4, 475, 10, 1006, 10, 507, 1101, 0, 0, 475, 109, -2,
	    2106, 0, 0, 0, 109, 4, 1201, -1, 0, 512, 1207, -3, 0, 10, 1006, 10, 530, 21102, 1, 0, -3, 22102, 1, -3, 1,
	    21201, -2, 0, 2, 21102, 1, 1, 3, 21102, 549, 1, 0, 1106, 0, 554, 109, -4, 2105, 1, 0, 109, 5, 1207, -3, 1,
	    10, 1006, 10, 577, 2207, -4, -2, 10, 1006, 10, 577, 21202, -4, 1, -4, 1106, 0, 645, 21202, -4, 1, 1, 21201,
	    -3, -1, 2, 21202, -2, 2, 3, 21101, 596, 0, 0, 1106, 0, 554, 21201, 1, 0, -4, 21102, 1, 1, -1, 2207, -4, -2,
	    10, 1006, 10, 615, 21101, 0, 0, -1, 22202, -2, -1, -2, 2107, 0, -3, 10, 1006, 10, 637, 22102, 1, -1, 1,
	    21101, 637, 0, 0, 105, 1, 512, 21202, -2, -1, -2, 22201, -4, -2, -4, 109, -5, 2106, 0, 0 };

    @Override
    public String part1(String filepath, Object... params) {
	IntCode test = new IntCode(puzzle);
	Point currentLocation = new Point(0, 0);
	Direction dir = Direction.U;
	final Set<Point> paintedOnce = new HashSet<>();
	final Set<Point> whitePlaces = new HashSet<>();
	while (true) {
	    test.addInput(whitePlaces.contains(currentLocation) ? 1 : 0);
	    long paintColor = test.run();
	    if (paintColor == -1)
		break;
	    long turn = test.run();
	    paintedOnce.add(currentLocation);
	    if (paintColor == 1) {
		whitePlaces.add(currentLocation);
	    } else if (paintColor == 0) {
		whitePlaces.remove(currentLocation);
	    }

	    dir = turn(dir, turn == 1);
	    currentLocation = move(currentLocation, dir);
	}
	return String.valueOf(0);
    }

    @Override
    public String part2(String filepath, Object... params) {

	return String.valueOf(0);
    }

    public Direction turn(Direction dir, boolean right) {
	int cur = dir.ordinal() + (right ? 1 : -1);
	if (cur == Direction.values().length)
	    cur = 0;
	else if (cur == -1)
	    cur = 3;
	return Direction.values()[cur];
    }

    private Point move(Point currentLocation, Direction dir2) {
	switch (dir2) {
	case U:
	    return new Point(currentLocation.getX(), currentLocation.getY() - 1);
	case D:
	    return new Point(currentLocation.getX(), currentLocation.getY() + 1);
	case R:
	    return new Point(currentLocation.getX() + 1, currentLocation.getY());
	case L:
	    return new Point(currentLocation.getX() - 1, currentLocation.getY());
	}
	return null;
    }

    private static class IntCode {

	private LinkedHashMap<Long, Long> tab = new LinkedHashMap<>();

	private LinkedList<Integer> input = new LinkedList<>();

	private long position = 0l;

	private long relativeBase = 0l;

	public IntCode(long[] tab) {
	    long pos = 0L;
	    for (long instruction : tab) {
		this.tab.put(pos++, instruction);
	    }
	}

	public void addInput(int input) {
	    this.input.addLast(input);
	}

	public long run() {
	    while (true) {
		if (this.tab.get(this.position) == 99) {
		    return -1;
		}
		Instruction inst = new Instruction(this.tab, this.position);
		switch (inst.getOpcode()) {
		case 1:
		    this.tab.put(inst.getOutPosition(relativeBase),
			    inst.getParameterValue1(relativeBase) + inst.getParameterValue2(relativeBase));
		    this.position += inst.getStep();
		    break;
		case 2:
		    this.tab.put(inst.getOutPosition(relativeBase),
			    inst.getParameterValue1(relativeBase) * inst.getParameterValue2(relativeBase));
		    this.position += inst.getStep();
		    break;
		case 3:
		    long inp = this.input.removeFirst();
		    this.tab.put(inst.getOutPosition(relativeBase), inp);
		    this.position += inst.getStep();
		    break;
		case 4:
		    this.position += inst.getStep();
		    return inst.getParameterValue1(relativeBase);
		case 5:
		    if (inst.getParameterValue1(relativeBase) != 0) {
			this.position = inst.getParameterValue2(relativeBase);
		    } else {
			this.position += inst.getStep();
		    }
		    break;
		case 6:
		    if (inst.getParameterValue1(relativeBase) == 0) {
			this.position = inst.getParameterValue2(relativeBase);
		    } else {
			this.position += inst.getStep();
		    }
		    break;
		case 7:
		    if (inst.getParameterValue1(relativeBase) < inst.getParameterValue2(relativeBase)) {
			this.tab.put(inst.getOutPosition(relativeBase), 1L);
		    } else {
			this.tab.put(inst.getOutPosition(relativeBase), 0L);
		    }
		    this.position += inst.getStep();
		    break;
		case 8:
		    if (inst.getParameterValue1(relativeBase) == inst.getParameterValue2(relativeBase)) {
			this.tab.put(inst.getOutPosition(relativeBase), 1L);
		    } else {
			this.tab.put(inst.getOutPosition(relativeBase), 0L);
		    }
		    this.position += inst.getStep();
		    break;
		case 9:
		    this.relativeBase += inst.getParameterValue1(relativeBase);
		    this.position += inst.getStep();
		    break;
		}
	    }
	}
    }

    private static class Instruction {
	private LinkedHashMap<Long, Long> program;
	private int opcode;
	private Long step;

	private int parameterMode1 = 0;
	private int parameterMode2 = 0;
	private int parameterMode3 = 0;
	private int outParameterMode = 0;

	private long parameterValue1;
	private long parameterValue2;
	private long parameterValue3;

	private long outPosition;

	public Instruction(LinkedHashMap<Long, Long> tab, Long position) {
	    this.program = tab;
	    this.parseInstruction(program.get(position));
	    switch (opcode) {
	    case 1:
	    case 2:
	    case 7:
	    case 8:
		step = 4L;
		parameterValue1 = program.get(position + 1);
		parameterValue2 = program.get(position + 2);
		outPosition = program.get(position + 3);
		outParameterMode = parameterMode3;
		break;
	    case 3:
		step = 2L;
		outPosition = program.get(position + 1);
		outParameterMode = parameterMode1;
		break;
	    case 4:
	    case 9:
		step = 2L;
		parameterValue1 = program.get(position + 1);
		break;
	    case 5:
	    case 6:
		step = 3L;
		parameterValue1 = program.get(position + 1);
		parameterValue2 = program.get(position + 2);
		outParameterMode = parameterMode2;
		break;
	    }
	}

	public int getOpcode() {
	    return opcode;
	}

	public long getStep() {
	    return step;
	}

	public long getParameterValue1(long offset) {
	    if (parameterMode1 == 1) {
		return parameterValue1;
	    }
	    if (parameterMode1 == 2) {
		return this.program.get(parameterValue1 + offset);
	    }
	    return this.program.get(parameterValue1);
	}

	public long getParameterValue2(long offset) {
	    if (parameterMode2 == 1) {
		return parameterValue2;
	    }
	    if (parameterMode2 == 2) {
		return this.program.get(parameterValue2 + offset);
	    }
	    return this.program.get(parameterValue2);
	}

	public long getParameterValue3(long offset) {
	    if (parameterMode3 == 1) {
		return parameterValue3;
	    }
	    if (parameterMode3 == 2) {
		return this.program.get(parameterValue3 + offset);
	    }
	    return this.program.get(parameterValue3);
	}

	public long getOutPosition(long offset) {
	    if (outParameterMode == 2) {
		return outPosition + offset;
	    }
	    return outPosition;
	}

	private void parseInstruction(Long instruction) {
	    if (instruction <= 99) {
		this.opcode = instruction.intValue();
		return;
	    }

	    String temp = instruction.toString();
	    this.opcode = Integer.parseInt(temp.substring(temp.length() - 2));
	    if (temp.length() > 2) {
		this.parameterMode1 = Integer.parseInt(temp.substring(temp.length() - 3, temp.length() - 2));
	    }
	    if (temp.length() > 3) {
		this.parameterMode2 = Integer.parseInt(temp.substring(temp.length() - 4, temp.length() - 3));
	    }
	    if (temp.length() > 4) {
		this.parameterMode3 = Integer.parseInt(temp.substring(temp.length() - 5, temp.length() - 4));
	    }
	}
    }

}
