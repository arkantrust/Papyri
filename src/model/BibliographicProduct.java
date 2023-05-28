package model;

import java.util.Calendar;

public abstract class BibliographicProduct extends Product {
    protected int pages;
    protected String coverURL;

    public BibliographicProduct(String id, String name, Calendar publicationDate, int pages, String coverURL,
            double price) {
        super(id, name, publicationDate, price);
        this.pages = pages;
        this.coverURL = coverURL;
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

    @Override
    public String toString() {
        String info = "";
        info += super.toString();
        info += toBold("Pages: ") + pages + "\n";
        info += toBold("Cover: ") + coverURL + "\n";
        return info;
    }
}
