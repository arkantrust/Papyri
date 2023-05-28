package model;

public enum MagazineCategory {
    MISCELLANY("Miscellany"),
    DESIGN("Design"),
    SCIENTIFIC("Scientific"),
    LIFESTYLE("Lifestyle"),
    TECHNOLOGY("Technology"),
    BUSINESS("Business");

    private final String name;

    /**
     * Declares the syntaxis on creating a {@code MagazineCategory} enum
     * @param name
     */
    private MagazineCategory(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the enum
     * 
     * @return enum name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Uses {@code switch} to get an enum value and return it
     * @param intCategory The value of the enum
     * @return the enum
     */
    public static MagazineCategory get(int intCategory) { //
        return switch (intCategory) {
            case 1 -> MagazineCategory.MISCELLANY;
            case 2 -> MagazineCategory.DESIGN;
            case 3 -> MagazineCategory.SCIENTIFIC;
            case 4 -> MagazineCategory.LIFESTYLE;
            case 5 -> MagazineCategory.TECHNOLOGY;
            case 6 -> MagazineCategory.BUSINESS;
            default -> null;
        };
    }
}
