package fr.insee.adventofcode.days;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import fr.insee.adventofcode.utils.Utils;

public class Day17 extends Day {

    private static final String[][] puzzle = Utils.getLineString("src/main/resources/17.txt", "");

    @Override
    public String part1() {
        List<Coordinates> coordinates = new ArrayList<>();

        for (int x = 0; x < puzzle.length; x ++ ) {
            for (int y = 0; y < puzzle.length; y ++ ) {
                if ("#".equals(puzzle[x][y])) {
                    coordinates.add(new Coordinates(x, y, 0, true, null));
                }
                else {
                    coordinates.add(new Coordinates(x, y, 0, false, null));
                }
            }
        }

        for (int n = 0; n < 6; n ++ ) {
            int minX = getMinX(coordinates);
            int maxX = getMaxX(coordinates);
            int minY = getMinY(coordinates);
            int maxY = getMaxY(coordinates);
            int minZ = getMinZ(coordinates);
            int maxZ = getMaxZ(coordinates);
            for (int x = minX; x <= maxX; x ++ ) {
                for (int y = minY; y <= maxY; y ++ ) {
                    for (int z = minZ; z <= maxZ; z ++ ) {
                        List<Coordinates> activeNeightbors = getActiveNeightbors(coordinates, x, y, z);
                        long active = activeNeightbors.size();
                        Coordinates c = findCoordinates(coordinates, x, y, z);
                        if (c == null) {
                            c = new Coordinates(x, y, z, false, null);
                            coordinates.add(c);
                        }
                        if (c.active && active != 2l && active != 3l) {
                            c.activeInNext = false;
                        }
                        if ( !c.active && active == 3l) {
                            c.activeInNext = true;
                        }
                    }
                }
            }
            for (int i = 0; i < coordinates.size(); i ++ ) {
                Coordinates c = coordinates.get(i);
                if (c.activeInNext != null && c.activeInNext) {
                    c.active = true;
                    c.activeInNext = null;
                }
                if (c.activeInNext != null && !c.activeInNext) {
                    c.active = false;
                    c.activeInNext = null;
                }
            }
        }

        return String.valueOf(coordinates.stream().filter(c -> c.active != null && c.active).count());
    }

    @Override
    public String part2() {

        List<Coordinates4D> activeCoordinates = new ArrayList<>();

        for (int x = 0; x < puzzle.length; x ++ ) {
            for (int y = 0; y < puzzle.length; y ++ ) {
                if ("#".equals(puzzle[x][y])) {
                    activeCoordinates.add(new Coordinates4D(x, y, 0, 0, null, null));
                }

            }
        }

        for (int n = 0; n < 6; n ++ ) {
            int minX = getMinX4D(activeCoordinates);
            int maxX = getMaxX4D(activeCoordinates);
            int minY = getMinY4D(activeCoordinates);
            int maxY = getMaxY4D(activeCoordinates);
            int minZ = getMinZ4D(activeCoordinates);
            int maxZ = getMaxZ4D(activeCoordinates);
            int minW = getMinW4D(activeCoordinates);
            int maxW = getMaxW4D(activeCoordinates);
            for (int x = minX; x <= maxX; x ++ ) {
                for (int y = minY; y <= maxY; y ++ ) {
                    for (int z = minZ; z <= maxZ; z ++ ) {
                        for (int w = minW; w <= maxW; w ++ ) {
                            List<Coordinates4D> activeNeightbors = getActiveNeightbors4D(activeCoordinates, x, y, z, w);
                            long active = activeNeightbors.size();
                            Coordinates4D c = new Coordinates4D(x, y, z, w, null, null);
                            if (activeCoordinates.contains(c) && active != 2l && active != 3l) {
                                activeCoordinates.get(activeCoordinates.indexOf(c)).desactiveInNext = true;
                            }
                            if ( !activeCoordinates.contains(c) && active == 3l) {
                                c.activeInNext = true;
                                activeCoordinates.add(c);
                            }
                        }
                    }
                }
            }
            Iterator<Coordinates4D> itr = activeCoordinates.iterator();
            while (itr.hasNext()) {
                Coordinates4D c = itr.next();
                if (c.activeInNext != null && c.activeInNext) {
                    c.activeInNext = null;
                }
                if (c.desactiveInNext != null && c.desactiveInNext) {
                    itr.remove();
                }
            }

        }

        return String.valueOf(activeCoordinates.size());
    }

    private int getMinX(List<Coordinates> coordinates) {
        return coordinates.stream().mapToInt(c -> c.x).min().getAsInt() - 1;
    }

    private int getMaxX(List<Coordinates> coordinates) {
        return coordinates.stream().mapToInt(c -> c.x).max().getAsInt() + 1;
    }

    private int getMinY(List<Coordinates> coordinates) {
        return coordinates.stream().mapToInt(c -> c.y).min().getAsInt() - 1;
    }

    private int getMaxY(List<Coordinates> coordinates) {
        return coordinates.stream().mapToInt(c -> c.y).max().getAsInt() + 1;
    }

    private int getMinZ(List<Coordinates> coordinates) {
        return coordinates.stream().mapToInt(c -> c.z).min().getAsInt() - 1;
    }

    private int getMaxZ(List<Coordinates> coordinates) {
        return coordinates.stream().mapToInt(c -> c.z).max().getAsInt() + 1;
    }

    private int getMinX4D(List<Coordinates4D> coordinates) {
        return coordinates.stream().mapToInt(c -> c.x).min().getAsInt() - 1;
    }

    private int getMaxX4D(List<Coordinates4D> coordinates) {
        return coordinates.stream().mapToInt(c -> c.x).max().getAsInt() + 1;
    }

    private int getMinY4D(List<Coordinates4D> coordinates) {
        return coordinates.stream().mapToInt(c -> c.y).min().getAsInt() - 1;
    }

    private int getMaxY4D(List<Coordinates4D> coordinates) {
        return coordinates.stream().mapToInt(c -> c.y).max().getAsInt() + 1;
    }

    private int getMinZ4D(List<Coordinates4D> coordinates) {
        return coordinates.stream().mapToInt(c -> c.z).min().getAsInt() - 1;
    }

    private int getMaxZ4D(List<Coordinates4D> coordinates) {
        return coordinates.stream().mapToInt(c -> c.z).max().getAsInt() + 1;
    }

    private int getMinW4D(List<Coordinates4D> coordinates) {
        return coordinates.stream().mapToInt(c -> c.w).min().getAsInt() - 1;
    }

    private int getMaxW4D(List<Coordinates4D> coordinates) {
        return coordinates.stream().mapToInt(c -> c.w).max().getAsInt() + 1;
    }

    private Coordinates findCoordinates(List<Coordinates> coordinates, int x, int y, int z) {
        return coordinates.stream().filter(c -> c.x == x && c.y == y && c.z == z).findFirst().orElse(null);
    }

    private Coordinates4D findCoordinates4D(List<Coordinates4D> coordinates, int x, int y, int z, int w) {
        return coordinates.stream().filter(c -> c.x == x && c.y == y && c.z == z && c.w == w).findFirst().orElse(null);
    }

    private List<Coordinates> getActiveNeightbors(List<Coordinates> coordinates, int x, int y, int z) {
        List<Coordinates> active = new ArrayList<>();

        for (int i = -1; i <= 1; i ++ ) {
            for (int j = -1; j <= 1; j ++ ) {
                for (int k = -1; k <= 1; k ++ ) {
                    if (i == 0 && j == 0 && k == 0) {
                        continue;
                    }
                    Coordinates c = findCoordinates(coordinates, x + i, y + j, z + k);
                    if (c != null && c.active != null && c.active) {
                        active.add(c);
                    }
                }
            }
        }

        return active;
    }

    private List<Coordinates4D> getActiveNeightbors4D(List<Coordinates4D> coordinates, int x, int y, int z, int w) {
        List<Coordinates4D> active = new ArrayList<>();

        for (int i = -1; i <= 1; i ++ ) {
            for (int j = -1; j <= 1; j ++ ) {
                for (int k = -1; k <= 1; k ++ ) {
                    for (int l = -1; l <= 1; l ++ ) {

                        if (i == 0 && j == 0 && k == 0 && l == 0) {
                            continue;
                        }
                        Coordinates4D c = findCoordinates4D(coordinates, x + i, y + j, z + k, w + l);
                        if (c != null && c.activeInNext == null) {
                            active.add(c);
                        }
                    }
                }
            }
        }

        return active;
    }

    class Coordinates {

        int x;
        int y;
        int z;
        Boolean active;
        Boolean activeInNext;

        public Coordinates(int x, int y, int z, Boolean active, Boolean activeInNext) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.active = active;
            this.activeInNext = activeInNext;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Coordinates other = (Coordinates) obj;
            return x == other.x && y == other.y && z == other.z;
        }

        @Override
        public String toString() {
            return "Coordinates [x=" + x + ", y=" + y + ", z=" + z + ", active=" + active + ", activeInNext=" + activeInNext + "]";
        }

    }

    class Coordinates4D {

        int x;
        int y;
        int z;
        int w;
        Boolean activeInNext;
        Boolean desactiveInNext;

        public Coordinates4D(int x, int y, int z, int w, Boolean activeInNext, Boolean desactiveInNext) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
            this.desactiveInNext = desactiveInNext;
            this.activeInNext = activeInNext;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z, w);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Coordinates4D other = (Coordinates4D) obj;
            return x == other.x && y == other.y && z == other.z && w == other.w;
        }

    }

}
