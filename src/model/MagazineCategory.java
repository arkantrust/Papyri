package model;

public enum MagazineCategory {
    MISCELLANY, DESIGN, SCIENTIFIC;

    public static MagazineCategory getCategory(int intCategory) {
        MagazineCategory category = null;

        switch (intCategory) {
            case 1:
                category = MISCELLANY;
                break; 
            case 2:
                category = DESIGN;
                break;
            case 3:
                category = SCIENTIFIC;
                break;
        }
        return category;
    }
}
