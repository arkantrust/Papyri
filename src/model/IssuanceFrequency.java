package model;

public enum IssuanceFrequency {
    YEARLY, MONTHLY, WEEKLY, DAILY;

    public static IssuanceFrequency get(int intFreq) {
        return switch (intFreq) {
            case 1 -> YEARLY;
            case 2 -> MONTHLY;
            case 3 -> WEEKLY;
            case 4 -> DAILY;
            default -> null;
        };
    }
}
