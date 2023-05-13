package model;

public enum IssuanceFrequency {
    YEARLY, MONTHLY, WEEKLY, DAILY;

    public static IssuanceFrequency get(int intFreq) {
        IssuanceFrequency freq = null;

        switch(intFreq) {
            case 1:
                freq = YEARLY;
                break; 
            case 2:
                freq = MONTHLY;
                break;
            case 3:
                freq = WEEKLY;
                break;
            case 4:
                freq = DAILY;
                break;
        }
        return freq;
    }
}
