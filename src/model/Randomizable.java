package model;
import java.util.concurrent.ThreadLocalRandom;


public interface Randomizable {

    public static int randInt(int floor, int ceiling) {
        return ThreadLocalRandom.current().nextInt(floor, ceiling);
    }
}
