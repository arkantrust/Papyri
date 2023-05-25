package model;

import java.util.Calendar;
import java.util.ArrayList;

public class BaseUser extends User implements Watchable, Surprisable {

    // attributes
    protected boolean ads; // Should the user watch ads?
    protected ArrayList<Product> productsOwned;
    protected String productsOwnedList;
    protected int productsOwnedCount;
    private int boughtBooks;
    private int subscribedMagazines;

    public BaseUser(String name, String email, String password, String id, int internalID, Calendar initDate,
            boolean ads, ArrayList<Product> productsOwned, String productsOwnedList, int productsOwnedCount,
            int boughtBooks, int subscribedMagazines) {
        super(name, email, password, id, internalID, initDate);
        this.ads = ads;
        this.productsOwned = productsOwned;
        this.productsOwnedList = productsOwnedList;
        this.productsOwnedCount = productsOwnedCount;
        this.boughtBooks = boughtBooks;
        this.subscribedMagazines = subscribedMagazines;
    }

    public boolean hasAds() {
        return ads;
    }

    public void setAds(boolean ads) {
        this.ads = ads;
    }

    public ArrayList<Product> getProductsOwned() {
        return productsOwned;
    }

    public void setProductsOwned(ArrayList<Product> productsOwned) {
        this.productsOwned = productsOwned;
    }

    public String getProductsOwnedList() {
        return productsOwnedList;
    }

    public void setProductsOwnedList(String productsOwnedList) {
        this.productsOwnedList = productsOwnedList;
    }

    public int getProductsOwnedCount() {
        return productsOwnedCount;
    }

    public void setProductsOwnedCount(int productsOwnedCount) {
        this.productsOwnedCount = productsOwnedCount;
    }

    public void addProduct(Product bought) {
        productsOwned.add(bought);
        productsOwnedList += bought.toString();
        productsOwnedCount++;
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
