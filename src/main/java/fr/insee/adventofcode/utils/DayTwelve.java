package fr.insee.adventofcode.utils;

import java.math.BigInteger;
import java.util.Arrays;

public class DayTwelve {
    private final static Moon io = new Moon(6, -2, -7);
    private final static Moon europa = new Moon(-6, -7, -4);
    private final static Moon ganymede = new Moon(-9, 11, 0);
    private final static Moon callisto = new Moon(-3, -4, 6);
    
    
    public static void main(String[] args) {
	System.out.println(solvePartOne(""));
	System.out.println(solvePartTwo(""));
    }
    
    public static class Moon {
        private long[] originalPos;
        private long[] pos;
        private long[] vel;

        public Moon(long x, long y, long z) {
            this.originalPos = new long[] { x, y, z };
            this.pos = new long[] { x, y, z };
            this.vel = new long[] { 0, 0, 0 };
        }

        private void applyGravity(Moon other) {
            for (int i = 0; i < 3; i++) {
                if (pos[i] != other.pos[i]) {
                    if (pos[i] < other.pos[i]) {
                        vel[i]++;
                        other.vel[i]--;
                    } else {
                        vel[i]--;
                        other.vel[i]++;
                    }
                }
            }
        }

        private void applyVelocity() {
            for (int i = 0; i < 3; i++) {
                pos[i] += vel[i];
            }
        }

        private long totalEnergy() {
            return (Math.abs(pos[0]) + Math.abs(pos[1]) + Math.abs(pos[2])) * (Math.abs(vel[0]) + Math.abs(vel[1]) + Math.abs(vel[2]));
        }
     }

    public static String solvePartOne(String input) {
        return solvePartOne(new Moon[] {io, europa, ganymede, callisto}, 1000);
    }

    public static String solvePartTwo(String input) {
        return solvePartTwo(new Moon[] {io, europa, ganymede, callisto});
    }

    static String solvePartOne(Moon[] moons, long steps) {
        for (long step = 0; step < steps; step++) {
            performStep(moons);
        }

        return String.valueOf(Arrays.stream(moons)
                .map(Moon::totalEnergy)
                .reduce(Long::sum)
                .orElseThrow(IllegalStateException::new));
    }

    private static void performStep(Moon[] moons) {
        moons[0].applyGravity(moons[1]);
        moons[0].applyGravity(moons[2]);
        moons[0].applyGravity(moons[3]);
        moons[1].applyGravity(moons[2]);
        moons[1].applyGravity(moons[3]);
        moons[2].applyGravity(moons[3]);

        for (Moon moon : moons) {
            moon.applyVelocity();
        }
    }

    static String solvePartTwo(Moon[] moons) {
        long steps = 0;
        long[] cycleSteps = new long[3];

        while (cycleSteps[0] == 0 || cycleSteps[1] == 0 || cycleSteps[2] == 0) {
            steps++;
            performStep(moons);
            for (int axis = 0; axis < cycleSteps.length; axis++) {
                if (cycleSteps[axis] == 0 && allMoonsAreBackToStartOnAxis(moons, axis)) {
                    cycleSteps[axis] = steps + 1;
                }
            }
        }

        return String.valueOf(lcm(cycleSteps[0], lcm(cycleSteps[1], cycleSteps[2])));
    }

    private static boolean allMoonsAreBackToStartOnAxis(Moon[] moons, int axis) {
        for (Moon moon : moons) {
            if (moon.pos[axis] != moon.originalPos[axis]) {
                return false;
            }
        }
        return true;
    }

    private static long lcm(long long1, long long2) {
        BigInteger number1 = BigInteger.valueOf(long1);
        BigInteger number2 = BigInteger.valueOf(long2);
        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd).longValue();
    }
}
