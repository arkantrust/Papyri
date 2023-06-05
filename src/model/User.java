package model;

import java.util.ArrayList;
import java.util.Calendar;

public abstract class User implements DateManipulator, Emboldenable, Surprisable {

    protected String name;
    protected String email;
    protected String id;
    protected String password;
    protected Calendar initDate; // Date the user signed up
    protected ArrayList<String> library;

    public User(String name, String email, String password, String id, Calendar initDate, ArrayList<String> library) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.password = password;
        this.initDate = initDate;
        this.library = library;
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

    public ArrayList<String> getLibrary() {
        return library;
    }

    public void setLibrary(ArrayList<String> library) {
        this.library = library;
    }

    @Override
    public String toString() {
        String info = "";
        info += toBold("Name: ") + name + "\n";
        info += toBold("Email: ") + email + "\n";
        info += toBold("ID: ") + id + "\n";
        info += toBold("Registered: ") + dateToString(initDate) + "\n";
        return info;
    }

    @Override
    public String surprise(char randChar) {
        return "";
    }

    @Override
    public String surprise(int randInt) {
        return "";
    }

}
