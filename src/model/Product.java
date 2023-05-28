package model;

import java.util.Calendar;

public abstract class Product implements DateManipulator, Emboldenable {
    protected String id;
    protected String name;
    protected Calendar publicationDate;
    protected double price;

    public Product(String id, String name, Calendar publicationDate, double cost) {
        this.id = id;
        this.name = name;
        this.publicationDate = publicationDate;
        this.price = cost;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double cost) {
        this.price = cost;
    }

    @Override
    public String toString() {
        String info = "";
        info += toBold("ID: ") + id + "\n";
        info += toBold("Name: ") + name + "\n";
        info += toBold("Publication date: ") + dateToString(publicationDate) + "\n";
        info += toBold("Price: $") + price + "\n";
        return info;
    }

    public abstract void incrementCount();
}
