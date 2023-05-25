package model;

import java.util.Calendar;

public class Admin extends User {

    public Admin(String name, String email, String password, String id, int internalID, Calendar initDate) {
        super(name, email, password, id, internalID, initDate, false);
    }

    @Override
    public String surprise(int month, char randLetter) {
        return "Admin account";
    }    
}
