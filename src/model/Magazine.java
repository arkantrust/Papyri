package model;

import java.util.Calendar;

public class Magazine extends BibliographicProduct {
    private MagazineCategory category;
    private IssuanceFrequency freq;
    private int subs;

    public Magazine(String id, String name, Calendar publicationDate, int pages, String coverURL,
            double cost, MagazineCategory category, IssuanceFrequency freq, int subs) {
        super(id, name, publicationDate, pages, coverURL, cost);
        this.category = category;
        this.freq = freq;
        this.subs = subs;
    }

    public MagazineCategory getCategory() {
        return category;
    }

    public void setCategory(MagazineCategory category) {
        this.category = category;
    }

    public IssuanceFrequency getFreq() {
        return freq;
    }

    public void setFreq(IssuanceFrequency freq) {
        this.freq = freq;
    }

    public int getSubs() {
        return subs;
    }

    public void setSubs(int subs) {
        this.subs = subs;
    }
}