package model;

public enum BookGenre {
    SCIENCE_FICTION("Science fiction"),
    FANTASY("Fantasy"),
    HISTORICAL_NOVEL("Historical novel"),
    SCIENTIFIC("Scientific");

    private String name;

    private BookGenre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BookGenre get(int intGenre) {
        return switch (intGenre) {
            case 1 -> BookGenre.SCIENCE_FICTION;
            case 2 -> BookGenre.FANTASY;
            case 3 -> BookGenre.HISTORICAL_NOVEL;
            case 4 -> BookGenre.SCIENTIFIC;
            default -> null;
        };
    }
}
