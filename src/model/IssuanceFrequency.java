package model;

public enum IssuanceFrequency {
    YEARLY("Yearly"),
    MONTHLY("Monthly"),
    WEEKLY("Weekly"),
    DAILY("Daily");

    private String name;

    private IssuanceFrequency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
