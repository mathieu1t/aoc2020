package fr.insee.adventofcode.days;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.insee.adventofcode.utils.Utils;

public class Day14Refactor extends Day {

    private static final List<String> puzzle = Utils.getLineStringList("src/main/resources/14.txt");

    @Override
    public String part1() {
        List<Program> programs = getPrograms();
        Map<Long, Long> memory = new HashMap<>();
        for (Program p : programs) {
            for (Memory m : p.mems) {
                Long result = m.applyMaskValue(p.mask);
                memory.put(m.addressDec, result);
            }
        }
        Long count = memory.values().stream().reduce(Long::sum).get();
        return String.valueOf(count);
    }

    @Override
    public String part2() {
        List<Program> programs = getPrograms();
        Map<Long, Long> memory = new HashMap<>();
        for (Program p : programs) {
            for (Memory m : p.mems) {
                String result = m.applyMaskAdress(p.mask);
                List<String> comb = new ArrayList<>();
                getCombinaison(result, comb);
                comb.stream().map(ma -> new BigInteger(ma, 2).longValue()).forEach(c -> {
                    memory.put(c, m.valueDec);
                });
            }
        }
        Long count = memory.values().stream().reduce(Long::sum).get();
        return String.valueOf(count);
    }

    private void getCombinaison(String mask, List<String> masks) {
        if ( !mask.contains("X")) {
            masks.add(mask);
            return;
        }
        else {
            getCombinaison(mask.replaceFirst("X", "0"), masks);
            getCombinaison(mask.replaceFirst("X", "1"), masks);
        }
    }

    private List<Program> getPrograms() {
        List<Program> programs = new ArrayList<>();
        Program p = null;
        for (String line : puzzle) {
            if (line.contains("mask")) {
                if (p != null) {
                    programs.add(p);
                }
                p = new Program();
                Pattern pattern = Pattern.compile("mask = (\\w+)");
                Matcher matcher = pattern.matcher(line);
                matcher.find();
                p.mask = matcher.group(1);
            }
            else {
                Pattern pattern = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");
                Matcher matcher = pattern.matcher(line);
                matcher.find();
                Memory m = new Memory(matcher.group(1), matcher.group(2));
                p.mems.add(m);
            }
        }
        programs.add(p);
        return programs;
    }

    class Program {
        public String mask;
        public List<Memory> mems = new ArrayList<>();

    }

    class Memory {
        public Long addressDec;
        public String addressBin36;
        public Long valueDec;
        public String valueBin36;

        public Memory(String adress, String value) {
            this.addressDec = Long.parseLong(adress);
            this.valueDec = Long.parseLong(value);
            this.addressBin36 = getBinary36(adress);
            this.valueBin36 = getBinary36(value);

        }

        private String getBinary36(String dec) {
            String s = new BigInteger(dec, 10).toString(2);
            while (s.length() != 36) {
                s = "0" + s;
            }
            return s;
        }

        public Long applyMaskValue(String mask) {
            char[] maskTab = mask.toCharArray();
            for (int i = 0; i < maskTab.length; i ++ ) {
                if (maskTab[i] != 'X') {
                    StringBuilder sb = new StringBuilder(valueBin36);
                    sb.setCharAt(i, maskTab[i]);
                    valueBin36 = sb.toString();
                }
            }
            return new BigInteger(valueBin36, 2).longValue();
        }

        public String applyMaskAdress(String mask) {
            char[] maskTab = mask.toCharArray();
            for (int i = 0; i < maskTab.length; i ++ ) {
                if (maskTab[i] != '0') {
                    StringBuilder sb = new StringBuilder(addressBin36);
                    sb.setCharAt(i, maskTab[i]);
                    addressBin36 = sb.toString();
                }
            }
            return addressBin36;
        }
    }

}
