package model;

public class Company {
    private String name;
    private String nit;
    private String address;

    public Company(String name, String nit, String address) {
        this.name = name;
        this.nit = nit;
        this.address = address;
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
}