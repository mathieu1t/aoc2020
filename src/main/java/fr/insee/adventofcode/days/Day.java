package fr.insee.adventofcode.days;

import org.apache.commons.lang3.time.StopWatch;

public abstract class Day {
    
    private String result1;
    private String time1;
    private String result2;
    private String time2;

    public abstract String part1();

    public abstract String part2();

    public void run() {
        StopWatch watch = new StopWatch();
        watch.start();
        result1 = this.part1();
        watch.stop();
        time1 = watch.getTime()+" ms";
        watch.reset();
        watch.start();
        result2 = this.part2();
        watch.stop();
        time2 = watch.getTime() + " ms";
    }

    public String getResult1() {
        return result1;
    }

    public String getTime1() {
        return time1;
    }

    public String getResult2() {
        return result2;
    }

    public String getTime2() {
        return time2;
    }

}
