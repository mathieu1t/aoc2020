package fr.insee.adventofcode.days;

import org.apache.commons.lang3.time.StopWatch;

public abstract class Day {

    public abstract String part1();

    public abstract String part2();

    public void run() {
        StopWatch watch = new StopWatch();
        watch.start();
        String result1 = this.part1();
        watch.stop();
        System.out.println("Result for " + this.getClass().getSimpleName() + " part 1: " + result1 + " in " + watch.getTime() + " ms");
        watch.reset();
        watch.start();
        String result2 = this.part2();
        watch.stop();
        System.out.println("Result for " + this.getClass().getSimpleName() + " part 2: " + result2 + " in " + watch.getTime() + " ms");
    }

}
