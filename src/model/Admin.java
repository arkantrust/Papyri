package model;

import java.util.Calendar;

public class Admin extends User {

    public Admin(String name, String email, String password, String id, Calendar initDate) {
        super(name, email, password, id, initDate);
    }

    @Override
    public void addToLibrary(String productID) {
        throw new UnsupportedOperationException("Unimplemented method 'addToLibrary'");
    }
}
