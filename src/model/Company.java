package model;
import java.util.Calendar;

public class Company {
    //attributes
    private String name;
    private String nit;
    private String address;
    private int userIDs;

    //constants
    public static final double PAYMONTH = 5; // USD
    public static final int MAX_USERS = 15; // USD

    //relations
    private User[] users;

    public Company(String name, String nit, String address) {
        this.name = name;
        this.nit = nit;
        this.address = address;
        users = new User[MAX_USERS];
        userIDs = 1;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNit() {
        return nit;
    }
    public void setNit(String nit) {
        this.nit = nit;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public int getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(int userIDs) {
        this.userIDs = userIDs;
    }

    public boolean addUser(String name, String email) {
        boolean done = false;
        users[userIDs] = new BaseUser(name, email, String.valueOf(userIDs), Calendar.getInstance());
        done = true;
        userIDs += 1;
        return done;
    }

    public String displayUser(String id) {
        var userID = Integer.parseInt(id);
        // early return
        if (userID <= 0 || userID > userIDs)
            return "User not found";
        return users[userID].toString();
    }
}