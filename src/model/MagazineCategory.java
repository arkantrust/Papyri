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

    public static MagazineCategory get(int intCategory) { //TODO: Move to controller
        return switch (intCategory) {
            case 1 -> MISCELLANY;
            case 2 -> DESIGN;
            case 3 -> SCIENTIFIC;
            default -> null;
        };
    }
}
