package model;

import java.util.Calendar;

public class Magazine extends Product {
    private MagazineCategory category;
    private IssuanceFrequency freq;
    private int subs;

    public Magazine(String id, String name, Calendar publicationDate, int pages, String coverURL,
            double cost, int category, int freq, int subs) {
        super(id, name, publicationDate, pages, coverURL, cost);
        this.category = MagazineCategory.get(category);
        this.freq = IssuanceFrequency.get(freq);
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

    @Override
    public String toString() {
        String info = "";
        info += super.toString();
        info += toBold("Category: ") + category.getName() + "\n";
        info += toBold("Issuance Frequency: ") + freq.getName() + "\n";
        info += toBold("Subscriptions: ") + subs + "\n";
        return info;
    }

    @Override
    public void incrementCount() {
        subs++;
    }
}