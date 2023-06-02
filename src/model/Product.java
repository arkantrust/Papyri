package model;

import java.util.Calendar;

public abstract class Product implements DateManipulator, Emboldenable {
    protected String id;
    protected String name;
    protected Calendar publicationDate;
    protected double price;
    protected int pages;
    protected String coverURL;
    protected int readPages;

    public Product(String id, String name, Calendar publicationDate, int pages, String coverURL,
            double price) {
        this.id = id;
        this.name = name;
        this.publicationDate = publicationDate;
        this.price = price;
        this.pages = pages;
        this.coverURL = coverURL;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public int getReadPages() {
        return readPages;
    }

    public void setReadPages(int readPages) {
        this.readPages = readPages;
    }

    @Override
    public String toString() {
        String info = "";
        info += toBold("Name: ") + name + "\n";
        info += toBold("ID: ") + id + "\n";
        info += toBold("Publication date: ") + dateToString(publicationDate) + "\n";
        info += toBold("Price: ") + "$" + price + "\n";
        info += toBold("Pages: ") + pages + "\n";
        info += toBold("Cover: ") + coverURL + "\n";
        return info;
    }

    public void incrementReadPages() {
        readPages++;
    }

    public abstract void incrementCount();
}
