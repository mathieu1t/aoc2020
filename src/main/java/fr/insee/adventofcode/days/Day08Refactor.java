package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import fr.insee.adventofcode.utils.Utils;

public class Day08Refactor extends Day {

    private static final List<String> puzzle = Utils.getLineStringList("src/main/resources/08.txt");

    @Override
    public String part1() {
	List<Instruction> instructions = puzzle.stream().map(p -> new Instruction(p)).collect(Collectors.toList());
	List<Instruction> passed = new ArrayList<>();
	int acc = 0;
	int i = 0;
	Instruction inst = instructions.get(0);
	do {
	    passed.add(inst);
	    switch (inst.operation) {
	    case acc:
		acc = acc + inst.argument;
		i = i + 1;
		break;
	    case jmp:
		i = i + inst.argument;
		break;
	    case nop:
		i = i + 1;
		break;
	    default:
		// code block
	    }
	    inst = instructions.get(i);
	} while (!passed.contains(inst));
	return String.valueOf(acc);
    }

    @Override
    public String part2() {
	List<Instruction> instructions = puzzle.stream().map(p -> new Instruction(p)).collect(Collectors.toList());
	Instruction lastInst = instructions.get(instructions.size() - 1);
	List<Instruction> instructionsNopJmp = instructions.stream()
		.filter(i -> i.operation == Operation.jmp || i.operation == Operation.nop).collect(Collectors.toList());
	int accOk = 0;
	for (Instruction instNJ : instructionsNopJmp) {
	    List<Instruction> passed = new ArrayList<>();
	    int acc = 0;
	    int i = 0;
	    Instruction inst = instructions.get(0);
	    do {
		passed.add(inst);
		if (inst == instNJ) {
		    if (inst.operation == Operation.nop) {
			i = i + inst.argument;
		    } else {
			i = i + 1;
		    }
		} else {
		    switch (inst.operation) {
		    case acc:
			acc = acc + inst.argument;
			i = i + 1;
			break;
		    case jmp:
			i = i + inst.argument;
			break;
		    case nop:
			i = i + 1;
			break;
		    default:
			// code block
		    }
		}
		if (inst == lastInst) {
		    break;
		}
		inst = instructions.get(i);
	    } while (!passed.contains(inst));

	    if (inst == lastInst) {
		accOk = acc;
		break;
	    }
	}
	return String.valueOf(accOk);
    }

    enum Operation {
	acc, jmp, nop
    }

    class Instruction {

	public Operation operation;
	public int argument;

	public Instruction(String instruction) {
	    Pattern pattern = Pattern.compile("(acc|jmp|nop) (\\+|\\-)(\\d+)");
	    Matcher matcher = pattern.matcher(instruction);
	    matcher.find();
	    this.operation = Operation.valueOf(matcher.group(1));
	    this.argument = "+".equals(matcher.group(2)) ? Integer.parseInt(matcher.group(3))
		    : -1 * Integer.parseInt(matcher.group(3));
	}
    }

}
