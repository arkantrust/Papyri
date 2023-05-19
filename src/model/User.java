package model;

import java.util.ArrayList;
import java.util.Calendar;

public abstract class User implements DateUsage {

    protected String name;
    protected int internalID;
    protected String email;
    protected Calendar initDate; // Date the user signed up
    protected boolean ads;
    protected ArrayList<Product> productsOwned;
    protected int productsOwnedCount;
    protected String productsOwnedList;
    protected String password;
    protected String id;


    public User(String name, String email, String password, String id, int internalID, Calendar initDate, boolean ads) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
        this.internalID = internalID;
        this.initDate = initDate;
        this.ads = ads;
        productsOwned = new ArrayList<>();
        this.productsOwnedCount = 0;
        this.productsOwnedList = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInternalID() {
        return internalID;
    }

    public void setInternalID(int internalID) {
        this.internalID = internalID;
    }

    public Calendar getInitDate() {
        return initDate;
    }

    public void setInitDate(Calendar initDate) {
        this.initDate = initDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAds() {
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

    public int getProductsOwnedCount() {
        return productsOwnedCount;
    }

    public void setProductsOwnedCount(int productsOwnedCount) {
        this.productsOwnedCount = productsOwnedCount;
    }

    public String getProductsOwnedList() {
        return productsOwnedList;
    }

    public void setProductsOwnedList(String productsOwnedList) {
        this.productsOwnedList = productsOwnedList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void addProduct(Product bought) {
        productsOwned.add(bought);
        productsOwnedList += bought.toString();
        productsOwnedCount++;
    }

    @Override
    public String toString() {
        String info = "";
        info += "Name: " + name + "\n";
        info += "Email: " + email + "\n";
        info += "ID: " + internalID + "\n";
        info += "Registered: " + DateUsage.DateToString(initDate) + "\n";
        return info;
    }

    public abstract String surprise(int month, char randLetter);
}
