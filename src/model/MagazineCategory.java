package model;

public enum MagazineCategory {
    MISCELLANY("Micellany"),
    DESIGN("Design"),
    SCIENTIFIC("Scientific");

    private final String name;

    private MagazineCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
