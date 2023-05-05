package model;
import java.util.ArrayList;

public class Company {
    //attributes
    private String name;
    private String nit;
    private String address;

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
}