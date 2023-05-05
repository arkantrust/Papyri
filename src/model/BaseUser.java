package model;

import java.util.Calendar;

public class BaseUser extends User {

    //attributes
    private int availableBooks;
    private int availableMagazines;

    public BaseUser(String name, String id, Calendar initDate) {
        super(name, id, initDate);
        availableBooks = 5;
        availableMagazines = 2;
    }

    @Override
    public String surprise(int month, char randLetter) { // TODO: implement
        var info = "";
        return info;
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
}
