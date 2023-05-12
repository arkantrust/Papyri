package model;

import java.util.Calendar;

public abstract class BibliographicProduct extends Product {
    int pages;
    String coverURL;

    public BibliographicProduct(String id, String name, Calendar publicationDate, int pages, String coverURL,
            double cost) {
        super(id, name, publicationDate, cost);
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
}
