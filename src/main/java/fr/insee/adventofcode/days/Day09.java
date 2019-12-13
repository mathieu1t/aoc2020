package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class Day09 extends Day {

    static final long[] puzzle = new long[] { 1102, 34463338, 34463338, 63, 1007, 63, 34463338, 63, 1005, 63, 53, 1101, 3, 0, 1000, 109, 988, 209, 12, 9, 1000, 209, 6, 209, 3, 203, 0, 1008, 1000, 1,
	    63, 1005, 63, 65, 1008, 1000, 2, 63, 1005, 63, 904, 1008, 1000, 0, 63, 1005, 63, 58, 4, 25, 104, 0, 99, 4, 0, 104, 0, 99, 4, 17, 104, 0, 99, 0, 0, 1101, 35, 0, 1007, 1102, 30, 1, 1013,
	    1102, 37, 1, 1017, 1101, 23, 0, 1006, 1101, 0, 32, 1008, 1102, 1, 29, 1000, 1101, 0, 38, 1010, 1101, 0, 24, 1002, 1101, 33, 0, 1003, 1101, 1, 0, 1021, 1102, 31, 1, 1019, 1101, 27, 0, 1014,
	    1102, 20, 1, 1005, 1101, 0, 0, 1020, 1102, 1, 892, 1027, 1101, 895, 0, 1026, 1102, 39, 1, 1015, 1102, 1, 370, 1029, 1102, 1, 28, 1001, 1102, 34, 1, 1012, 1101, 25, 0, 1016, 1101, 0, 375,
	    1028, 1101, 36, 0, 1018, 1101, 0, 21, 1004, 1102, 1, 26, 1009, 1101, 0, 249, 1022, 1101, 0, 660, 1025, 1101, 0, 665, 1024, 1102, 1, 22, 1011, 1102, 242, 1, 1023, 109, 5, 2102, 1, 3, 63,
	    1008, 63, 31, 63, 1005, 63, 205, 1001, 64, 1, 64, 1105, 1, 207, 4, 187, 1002, 64, 2, 64, 109, 8, 21102, 40, 1, 5, 1008, 1018, 37, 63, 1005, 63, 227, 1105, 1, 233, 4, 213, 1001, 64, 1, 64,
	    1002, 64, 2, 64, 109, 7, 2105, 1, 3, 1001, 64, 1, 64, 1106, 0, 251, 4, 239, 1002, 64, 2, 64, 109, -7, 1201, -7, 0, 63, 1008, 63, 20, 63, 1005, 63, 271, 1106, 0, 277, 4, 257, 1001, 64, 1,
	    64, 1002, 64, 2, 64, 109, -10, 1208, 0, 33, 63, 1005, 63, 295, 4, 283, 1106, 0, 299, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -6, 1207, 4, 27, 63, 1005, 63, 319, 1001, 64, 1, 64, 1105, 1,
	    321, 4, 305, 1002, 64, 2, 64, 109, 12, 1207, -1, 33, 63, 1005, 63, 339, 4, 327, 1105, 1, 343, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 6, 1206, 6, 355, 1106, 0, 361, 4, 349, 1001, 64, 1, 64,
	    1002, 64, 2, 64, 109, 21, 2106, 0, -8, 4, 367, 1106, 0, 379, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -29, 1202, 0, 1, 63, 1008, 63, 36, 63, 1005, 63, 403, 1001, 64, 1, 64, 1105, 1, 405, 4,
	    385, 1002, 64, 2, 64, 109, 11, 21107, 41, 40, -6, 1005, 1012, 421, 1105, 1, 427, 4, 411, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -11, 2101, 0, -4, 63, 1008, 63, 33, 63, 1005, 63, 453, 4,
	    433, 1001, 64, 1, 64, 1106, 0, 453, 1002, 64, 2, 64, 109, -7, 21108, 42, 40, 10, 1005, 1010, 469, 1105, 1, 475, 4, 459, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 1, 1201, 4, 0, 63, 1008, 63,
	    20, 63, 1005, 63, 497, 4, 481, 1105, 1, 501, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 5, 21107, 43, 44, 5, 1005, 1011, 523, 4, 507, 1001, 64, 1, 64, 1106, 0, 523, 1002, 64, 2, 64, 109, 20,
	    21108, 44, 44, -7, 1005, 1019, 541, 4, 529, 1106, 0, 545, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 2, 1205, -8, 561, 1001, 64, 1, 64, 1106, 0, 563, 4, 551, 1002, 64, 2, 64, 109, -23, 2108,
	    22, 0, 63, 1005, 63, 583, 1001, 64, 1, 64, 1105, 1, 585, 4, 569, 1002, 64, 2, 64, 109, -6, 2107, 30, 1, 63, 1005, 63, 605, 1001, 64, 1, 64, 1105, 1, 607, 4, 591, 1002, 64, 2, 64, 109, 23,
	    1205, -1, 621, 4, 613, 1105, 1, 625, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -19, 2102, 1, -3, 63, 1008, 63, 29, 63, 1005, 63, 647, 4, 631, 1106, 0, 651, 1001, 64, 1, 64, 1002, 64, 2, 64,
	    109, 28, 2105, 1, -7, 4, 657, 1106, 0, 669, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -17, 1206, 6, 687, 4, 675, 1001, 64, 1, 64, 1105, 1, 687, 1002, 64, 2, 64, 109, 2, 21101, 45, 0, 1, 1008,
	    1017, 42, 63, 1005, 63, 707, 1106, 0, 713, 4, 693, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -6, 2101, 0, -3, 63, 1008, 63, 34, 63, 1005, 63, 733, 1105, 1, 739, 4, 719, 1001, 64, 1, 64, 1002,
	    64, 2, 64, 109, 3, 21101, 46, 0, 1, 1008, 1014, 46, 63, 1005, 63, 761, 4, 745, 1106, 0, 765, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 5, 21102, 47, 1, -7, 1008, 1011, 47, 63, 1005, 63, 787,
	    4, 771, 1105, 1, 791, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -24, 2108, 24, 8, 63, 1005, 63, 813, 4, 797, 1001, 64, 1, 64, 1106, 0, 813, 1002, 64, 2, 64, 109, 5, 1208, 10, 29, 63, 1005,
	    63, 829, 1105, 1, 835, 4, 819, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 7, 2107, 23, -4, 63, 1005, 63, 853, 4, 841, 1105, 1, 857, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, -2, 1202, 0, 1, 63,
	    1008, 63, 21, 63, 1005, 63, 879, 4, 863, 1105, 1, 883, 1001, 64, 1, 64, 1002, 64, 2, 64, 109, 15, 2106, 0, 8, 1106, 0, 901, 4, 889, 1001, 64, 1, 64, 4, 64, 99, 21102, 1, 27, 1, 21102, 915,
	    1, 0, 1105, 1, 922, 21201, 1, 51839, 1, 204, 1, 99, 109, 3, 1207, -2, 3, 63, 1005, 63, 964, 21201, -2, -1, 1, 21101, 942, 0, 0, 1106, 0, 922, 21201, 1, 0, -1, 21201, -2, -3, 1, 21101, 957,
	    0, 0, 1105, 1, 922, 22201, 1, -1, -2, 1105, 1, 968, 21201, -2, 0, -2, 109, -3, 2106, 0, 0 };

    @Override
    public String part1(String filepath, Object... params) {
	IntCode test = new IntCode(puzzle);
	test.addInput(1);

	long output = 0;
	while (output != -1) {
	    output = test.run();
	    System.out.println(output);
	}
	return String.valueOf(output);
    }

    @Override
    public String part2(String filepath, Object... params) {
	IntCode test = new IntCode(puzzle);
	test.addInput(2);

	long output = 0;
	while (output != -1) {
	    output = test.run();
	    System.out.println(output);
	}
	return String.valueOf(output);
    }

    private static class IntCode {

	private LinkedHashMap<Long, Long> tab = new LinkedHashMap<>();

	private LinkedList<Integer> input = new LinkedList<>();

	private long position = 0l;

	private long relativeBase = 0l;

	public IntCode(long[] tab) {
	    long pos = 0L;
            for (long instruction: tab) {
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
                     this.tab.put(inst.getOutPosition(relativeBase), inst.getParameterValue1(relativeBase) + inst.getParameterValue2(relativeBase));
                     this.position += inst.getStep();
                     break;
                 case 2:
                     this.tab.put(inst.getOutPosition(relativeBase), inst.getParameterValue1(relativeBase) * inst.getParameterValue2(relativeBase));
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
                return this.program.get(parameterValue1+offset);
            }
            return this.program.get(parameterValue1);
        }

        public long getParameterValue2(long offset) {
            if (parameterMode2 == 1) {
                return parameterValue2;
            }
            if (parameterMode2 == 2) {
                return this.program.get(parameterValue2+offset);
            }
            return this.program.get(parameterValue2);
        }

        public long getParameterValue3(long offset) {
            if (parameterMode3 == 1) {
                return parameterValue3;
            }
            if (parameterMode3 == 2) {
                return this.program.get(parameterValue3+offset);
            }
            return this.program.get(parameterValue3);
        }

        public long getOutPosition(long offset) {
            if (outParameterMode == 2) {
                return outPosition+offset;
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
