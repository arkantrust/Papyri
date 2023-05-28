package model;

import java.util.Calendar;
import java.util.ArrayList;

public class BaseUser extends User implements Watchable, Surprisable {

    // attributes
    protected boolean ads; // Should the user watch ads?
    protected ArrayList<String> library;
    private int boughtBooks;
    private int subscribedMagazines;

    public BaseUser(String name, String email, String password, String id, Calendar initDate,
            boolean ads, ArrayList<String> library, int boughtBooks, int subscribedMagazines) {
                
        super(name, email, password, id, initDate);
        this.ads = ads;
        this.library = library;
        this.boughtBooks = boughtBooks;
        this.subscribedMagazines = subscribedMagazines;
    }

    public boolean hasAds() {
        return ads;
    }

    public void setAds(boolean ads) {
        this.ads = ads;
    }

    public ArrayList<String> getLibrary() {
        return library;
    }

    public void setLibrary(ArrayList<String> library) {
        this.library = library;
    }

    public int getBoughtBooks() {
        return boughtBooks;
    }

    public void setBoughtBooks(int availableBooks) {
        this.boughtBooks = availableBooks;
    }

    public int getSubscribedMagazines() {
        return subscribedMagazines;
    }

    public void setSubscribedMagazines(int availableMagazines) {
        this.subscribedMagazines = availableMagazines;
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
