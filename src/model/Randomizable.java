package model;

import java.util.concurrent.ThreadLocalRandom;

public interface Randomizable {

    /**
     * This function returns a random integer between a minimum and maximum value.
     * 
     * @param min The minimum value that the random integer can take.
     * @param max The maximum value that the random integer can take (inclusive).
     * @return The method `randInt` returns a random integer between the `min` and `max` values
     * (inclusive). It uses the `ThreadLocalRandom` class to generate the random number.
     */
    default int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }
}
