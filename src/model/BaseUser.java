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
    public void watchAds() {}

    @Override
    public String surprise(int month, char randLetter) { // TODO: implement
        var info = "";
        return info;
    }
}
