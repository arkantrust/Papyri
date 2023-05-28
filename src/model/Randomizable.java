package model;

import java.util.concurrent.ThreadLocalRandom;

public interface Randomizable {

    default int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
