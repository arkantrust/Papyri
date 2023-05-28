package model;

public enum IssuanceFrequency {
    YEARLY("Yearly"),
    QUARTERLY("Quarterly"),
    MONTHLY("Monthly"),
    WEEKLY("Weekly"),
    DAILY("Daily");

    private String name;

    private IssuanceFrequency(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the enum
     * 
     * @return the name of the enum
     */
    public String getName() {
        return name;
    }

    /**
     * Uses {@code switch} to get an enum value and return it
     * 
     * @param intFreq value to search
     * @return enum vale
     */
    public static IssuanceFrequency get(int intFreq) {
        return switch (intFreq) {
            case 1 -> IssuanceFrequency.YEARLY;
            case 2 -> IssuanceFrequency.QUARTERLY;
            case 3 -> IssuanceFrequency.MONTHLY;
            case 4 -> IssuanceFrequency.WEEKLY;
            case 5 -> IssuanceFrequency.DAILY;
            default -> null;
        };
    }
}
