package model;

public enum BookGenre {
    SCIENCE_FICTION("Science fiction"),
    FANTASY("Fantasy"),
    HISTORICAL_NOVEL("Historical novel");

    private String name;

    private BookGenre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
