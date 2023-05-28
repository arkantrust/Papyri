package model;

public enum MagazineCategory {
    MISCELLANY("Micellany", 1),
    DESIGN("Design", 2),
    SCIENTIFIC("Scientific", 3);

    private final String name;

    /**
     * Declares the syntaxis on creating a {@code MagazineCategory} enum
     * 
     * @param name
     * @param value
     */
    private MagazineCategory(String name, int value) {
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
            default -> null;
        };
    }
}
