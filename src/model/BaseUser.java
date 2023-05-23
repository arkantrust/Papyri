package model;

import java.util.Calendar;

public class BaseUser extends User implements Watchable {

    // attributes
    private int availableBooks;
    private int availableMagazines;

    public BaseUser(String name, String email, String password, String id, int internalID, Calendar initDate) {
        super(name, email, password, id, internalID, initDate, true);
        availableBooks = 5;
        availableMagazines = 2;
    }

    public int getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(int availableBooks) {
        this.availableBooks = availableBooks;
    }

    public int getAvailableMagazines() {
        return availableMagazines;
    }

    public void setAvailableMagazines(int availableMagazines) {
        this.availableMagazines = availableMagazines;
    }

    @Override
    public void watchAds() {
        // Should show an ad everytime a reading session starts
    }

    @Override
    public String surprise(int month, char randLetter) {
        var message = "";
        if (String.valueOf(name.charAt(0)).equalsIgnoreCase(String.valueOf(randLetter))) {
            message += "COngratulations! You will see 0 ads for 24 hours!\n";
        } else {
            message += "Sorry, you didn't get a surprise.";
        }
        return message;
    }
}
