package model;

public enum BookGenre {
    SCIENCE_FICTION,
    FANTASY,
    HISTORICAL_NOVEL;

    public static BookGenre get(int intGenre) {
        return switch (intGenre) {
            case 1 -> SCIENCE_FICTION;
            case 2 -> FANTASY;
            case 3 -> HISTORICAL_NOVEL;
            default -> null;
        };
    }
}
