package model;

import java.util.Calendar;

public abstract class User implements DateUsage {

    protected String name;
    protected int id;
    protected String email;
    protected Calendar initDate; // Date the user signed up
    protected boolean ads;

    public User(String name, String email, int id, Calendar initDate, boolean ads) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.initDate = initDate;
        this.ads = ads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        String info = "";
        info += "Name: " + name + "\n";
        info += "Email: " + email + "\n";
        info += "ID: " + id + "\n";
        info += "Registered: " + DateUsage.DateToString(initDate) + "\n";
        return info;
    }

    public abstract String surprise(int month, char randLetter);
}
