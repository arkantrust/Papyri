package model;

import java.util.Calendar;

public abstract class User {

    protected String name;
    protected String email;
    protected String id;
    protected String password;
    protected Calendar initDate; // Date the user signed up
    protected int internalID;

    public User(String name, String email, String password, String id, int internalID, Calendar initDate) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.password = password;
        this.initDate = initDate;
        this.internalID = internalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getInitDate() {
        return initDate;
    }

    public void setInitDate(Calendar initDate) {
        this.initDate = initDate;
    }

    public int getInternalID() {
        return internalID;
    }

    public void setInternalID(int internalID) {
        this.internalID = internalID;
    }

    @Override
    public String toString() {
        String info = "";
        info += "Name: " + name + "\n";
        info += "Email: " + email + "\n";
        info += "ID: " + internalID + "\n";
        info += "Registered: " + DateProcessing.dateToString(initDate) + "\n";
        return info;
    }
}
