package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day07 extends Day {

    static final int[] puzzle = new int[] { 3, 8, 1001, 8, 10, 8, 105, 1, 0, 0, 21, 38, 47, 64, 85, 106, 187, 268, 349, 430, 99999, 3, 9, 1002, 9, 4, 9, 1001, 9, 4, 9, 1002, 9, 4, 9, 4, 9, 99, 3, 9,
	    1002, 9, 4, 9, 4, 9, 99, 3, 9, 1001, 9, 3, 9, 102, 5, 9, 9, 1001, 9, 5, 9, 4, 9, 99, 3, 9, 101, 3, 9, 9, 102, 5, 9, 9, 1001, 9, 4, 9, 102, 4, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 3, 9, 101, 2,
	    9, 9, 102, 4, 9, 9, 101, 2, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102,
	    2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9,
	    1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9,
	    101, 1, 9, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9,
	    101, 1, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9,
	    1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9,
	    1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9,
	    1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 99 };
    
    @Override
    public String part1(String filepath, Object... params) {
	List<Integer> signals = new ArrayList<>();
	List<Integer[]> reglages = reglagesPossibles(0, 4);
	for (Integer[] phase : reglages) {
	    ArrayList<IntCode> progs = getNewPrograms(phase);
	    int prevResult = 0;
		for (IntCode prog : progs) {
		    prog.addInput(prevResult);
		    prevResult = prog.run();
		    if (prevResult == -1) {
			break;
		    }
		}
		if (prevResult != -1) {
		    signals.add(prevResult);
		}

	}

	int retour = signals.stream().mapToInt(x -> x).max().orElse(0);
	return String.valueOf(retour);
    }

    

    @Override
    public String part2(String filepath, Object... params) {

	List<Integer> signals = new ArrayList<>();
	List<Integer[]> reglages = reglagesPossibles(5, 9);
	for (Integer[] phase : reglages) {
	    ArrayList<IntCode> progs = getNewPrograms(phase);
	    int prevResult = 0;
	    while (prevResult > -1) {
		for (IntCode prog : progs) {
		    prog.addInput(prevResult);
		    prevResult = prog.run();
		    if (prevResult == -1) {
			break;
		    }
		}
		if (prevResult != -1) {
		    signals.add(prevResult);
		}
	    }

	}

	int retour = signals.stream().mapToInt(x -> x).max().orElse(0);
	return String.valueOf(retour);
    }

    private static ArrayList<IntCode> getNewPrograms(Integer[] phase) {
	ArrayList<IntCode> programs = new ArrayList<>();
	IntCode a = new IntCode(puzzle);
	a.addInput(phase[0]);
	programs.add(a);
	IntCode b = new IntCode(puzzle);
	b.addInput(phase[1]);
	programs.add(b);
	IntCode c = new IntCode(puzzle);
	c.addInput(phase[2]);
	programs.add(c);
	IntCode d = new IntCode(puzzle);
	d.addInput(phase[3]);
	programs.add(d);
	IntCode e = new IntCode(puzzle);
	e.addInput(phase[4]);
	programs.add(e);
	return programs;
    }

    private List<Integer[]> reglagesPossibles(int debut, int fin) {
	List<Integer[]> reglages = new ArrayList<>();
	for (int a = debut; a <= fin; a++) {
	    for (int b = debut; b <= fin; b++) {
		if (b != a) {
		    for (int c = debut; c <= fin; c++) {
			if (c != a && c != b) {
			    for (int d = debut; d <= fin; d++) {
				if (d != c && d != b && d != a) {
				    for (int e = debut; e <= fin; e++) {
					if (e != d && e != c && e != b && e != a) {
					    reglages.add(new Integer[] { a, b, c, d, e });
					}
				    }
				}
			    }
			}
		    }
		}
	    }

	}
	return reglages;
    }

    private static class IntCode {

	private int[] tab;

	private LinkedList<Integer> input = new LinkedList<>();

	private Integer position = 0;

	/**
	 * @param tab
	 * @param input
	 * @param position
	 */
	public IntCode(int[] tab) {
	    this.tab = tab.clone();
	}

	public void addInput(int input) {
	    this.input.addLast(input);
	}

	public int run() {
	    while (true) {
		if (this.tab[this.position] == 99) {
		    return -1;
		}
		Instruction inst = new Instruction(this.tab, this.position);
		switch (inst.getOpcode()) {
		case 1:
		    this.tab[inst.getOutPosition()] = inst.getParameterValue1() + inst.getParameterValue2();
		    this.position += inst.getStep();
		    break;
		case 2:
		    this.tab[inst.getOutPosition()] = inst.getParameterValue1() * inst.getParameterValue2();
		    this.position += inst.getStep();
		    break;
		case 3:
		    int inp = this.input.removeFirst();
		    this.tab[inst.getOutPosition()] = inp;
		    this.position += inst.getStep();
		    break;
		case 4:
		    this.position += inst.getStep();
		    return inst.getParameterValue1();
		case 5:
		    if (inst.getParameterValue1() != 0) {
			this.position = inst.getParameterValue2();
		    } else {
			this.position += inst.getStep();
		    }
		    break;
		case 6:
		    if (inst.getParameterValue1() == 0) {
			this.position = inst.getParameterValue2();
		    } else {
			this.position += inst.getStep();
		    }
		    break;
		case 7:
		    if (inst.getParameterValue1() < inst.getParameterValue2()) {
			this.tab[inst.getOutPosition()] = 1;
		    } else {
			this.tab[inst.getOutPosition()] = 0;
		    }
		    this.position += inst.getStep();
		    break;
		case 8:
		    if (inst.getParameterValue1() == inst.getParameterValue2()) {
			this.tab[inst.getOutPosition()] = 1;
		    } else {
			this.tab[inst.getOutPosition()] = 0;
		    }
		    this.position += inst.getStep();
		    break;
		}
	    }
	}
    }

    private static class Instruction {
	private int[] program;
	private int opcode;
	private int step;

	private int parameterMode1 = 0;
	private int parameterMode2 = 0;
	private int parameterMode3 = 0;

	/**
	 * either a literal value or a position, depending on parameter mode being 0 or
	 * 1
	 */
	private int parameterValue1;
	private int parameterValue2;
	private int parameterValue3;

	private int outPosition;

	public Instruction(int[] program, int position) {
	    this.program = program;
	    this.parseInstruction(program[position]);
	    switch (opcode) {
	    case 1:
	    case 2:
	    case 7:
	    case 8:
		step = 4;
		parameterValue1 = program[position + 1];
		parameterValue2 = program[position + 2];
		outPosition = program[position + 3];
		break;
	    case 3:
		step = 2;
		outPosition = program[position + 1];
		break;
	    case 4:
		step = 2;
		parameterValue1 = program[position + 1];
		break;
	    case 5:
	    case 6:
		step = 3;
		parameterValue1 = program[position + 1];
		parameterValue2 = program[position + 2];
		break;
	    }
	}

	public int getOpcode() {
	    return opcode;
	}

	public int getStep() {
	    return step;
	}

	public int getParameterValue1() {
	    if (parameterMode1 == 1) {
		return parameterValue1;
	    }
	    return this.program[parameterValue1];
	}

	public int getParameterValue2() {
	    if (parameterMode2 == 1) {
		return parameterValue2;
	    }
	    return this.program[parameterValue2];
	}

	public int getParameterValue3() {
	    if (parameterMode3 == 1) {
		return parameterValue3;
	    }
	    return this.program[parameterValue3];
	}

	public int getOutPosition() {
	    return outPosition;
	}

	private void parseInstruction(Integer instruction) {
	    if (instruction <= 99) {
		this.opcode = instruction;
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
