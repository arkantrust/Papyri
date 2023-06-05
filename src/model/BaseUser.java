package model;

import java.util.Calendar;
import java.util.ArrayList;

public class BaseUser extends User {

    // attributes
    protected boolean ads; // Should the user watch ads?
    protected int boughtBooks;
    protected int subscribedMagazines;

    public BaseUser(String name, String email, String password, String id, Calendar initDate,
            boolean ads, ArrayList<String> library, int boughtBooks, int subscribedMagazines) {
        super(name, email, password, id, initDate, library);
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
    public String toString() {
        String toString = super.toString();
        toString += toBold("Books: ") + boughtBooks + "\n";
        toString += toBold("Magazines: ") + subscribedMagazines + "\n";
        return toString;
    }

    /**
     * The function checks if the first letter of a given name matches a randomly
     * generated letter and
     * returns a message accordingly.
     * 
     * @param randLetter a randomly generated letter that is passed as a parameter
     *                   to the method.
     * @return The method is returning a message string that either congratulates
     *         the user for matching the first letter of their name with a randomly
     *         generated letter and promises them no ads for 24 hours, or apologizes
     *         for not having a surprise for them.
     */
    @Override
    public String surprise(char randLetter) {
        var message = "";
        if (String.valueOf(name.charAt(0)).equalsIgnoreCase(String.valueOf(randLetter))) {
            message += "Congratulations! You will see 0 ads for 24 hours!\n";
        } else {
            message += "Sorry, you didn't get a surprise.";
        }
        return message;
    }
}
