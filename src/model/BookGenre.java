package model;

public enum BookGenre {
    SCIENCE_FICTION,
    FANTASY,
    HISTORICAL_NOVEL;

    public static BookGenre getGenre(int intGenre) {
        BookGenre genre = null;

        switch (intGenre) {
            case 1:
                genre = SCIENCE_FICTION;
                break;
            case 2:
                genre = FANTASY;
                break;
            case 3:
                genre = HISTORICAL_NOVEL;
                break;
        }
        return genre;
    }
}
