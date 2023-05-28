package model;

public interface Emboldenable {
    default String toBold(String text) {
        return "\u001B[1m" + text + "\u001B[0m";
    }
}
