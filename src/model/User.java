package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class User {

    protected String name;
    protected String id;
    protected String email;
    protected Calendar initDate; // Date the user signed up

    public User(String name, String email, String id, Calendar initDate) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.initDate = initDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
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

    public String DateToString(Calendar date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateToString = dateFormat.format(date.getTime());
        return dateToString;
    }

    @Override
    public String toString() {
        String info = "";
        info += "Name: " + name + "\n";
        info += "Email: " + email + "\n";
        info += "ID: " + id + "\n";
        info += "Registered: " + DateToString(initDate) + "\n";
        return info;
    }

    public abstract String surprise(int month, char randLetter);
}
