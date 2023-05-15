package model;

import java.util.Calendar;
import java.util.ArrayList;

public class Company {
    // attributes
    private String name;
    private String nit;
    private String address;
    private int userIDs;
    private String userList;

    // constants
    public static final double PAYMONTH = 5; // USD

    // relations
    private ArrayList<User> users;

    public Company(String name, String nit, String address) {
        this.name = name;
        this.nit = nit;
        this.address = address;
        users = new ArrayList<>();
        userIDs = 1;
        userList = "";
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(int userIDs) {
        this.userIDs = userIDs;
    }

    public String getUserList() {
        return userList;
    }

    public void setUserList(String userList) {
        this.userList = userList;
    }

    public boolean userExists(String userID) {
        var id = Integer.parseInt(userID);
        if (id <= 0 || id > userIDs)
            return false;
        return true;
    }

    public void addUserToList() {
        userList += users.get(userIDs).toString() + "\n";
    }

    public boolean addUser(String name, String email) {
        boolean done = false;
        users.set(userIDs, new BaseUser(name, email, String.valueOf(userIDs), Calendar.getInstance()));
        addUserToList();
        done = true;
        userIDs += 1;
        return done;
    }

    public String displayUser(String userID) {
        // early return
        if (!userExists(userID)) {
            return "User not found";
        }
        var id = Integer.parseInt(userID);
        return users.get(id).toString();
    }

    public String displayUserName(String userID) {
        // early return
        if (!userExists(userID)) {
            return "User not found";
        }
        var id = Integer.parseInt(userID);
        return users.get(id).getName();
    }

    public boolean user2Premium(String userID, String nickname, String avatar, String card) {
        var done = false;
        var id = Integer.parseInt(userID);
        var user = users.get(id);
        var newPremiumUser = new PremiumUser(user.getName(), user.getEmail(), user.getID(), user.getInitDate(),
                nickname, avatar, card, Calendar.getInstance().get(Calendar.MONTH), new double[12]);
        users.set(userIDs, newPremiumUser);
        if (users.get(id) instanceof PremiumUser) {
            done = true;
        }
        return done;
    }

    public boolean confirmPurchase(char response) {
        boolean confirmation = false;
        try {
            response = Character.toUpperCase(response);
            switch (response) {
                case 'Y':
                    confirmation = true;
                    break;
                case 'N':
                    confirmation = false;
                    break;
            }
        } catch (Exception e) {
            confirmation = false;
        }
        return confirmation;
    }

    public boolean registerBook() {
        boolean done = false;
        return done;
    }
}