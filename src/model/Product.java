package model;

import java.util.Calendar;

public abstract class Product {
    protected String id;
    protected String name;
    protected Calendar publicationDate;
    protected double cost;

    public Product(String id, String name, Calendar publicationDate, double cost) {
        this.id = id;
        this.name = name;
        this.publicationDate = publicationDate;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Calendar publicationDate) {
        this.publicationDate = publicationDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
