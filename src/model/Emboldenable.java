package model;

public interface Emboldenable {
    /**
     * The function adds bold formatting to a given text string in Java.
     * 
     * @param text The text that needs to be formatted in bold.
     * @return A string with the input text formatted to be bold using ANSI escape
     *         codes.
     */
    default String toBold(String text) {
        return "\u001B[1m" + text + "\u001B[0m";
    }
}
