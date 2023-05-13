package model;
import java.util.Calendar;

public class Magazine extends BibliographicProduct{
    private MagazineCategory category; //TODO: Create MagazineCategory enum {Variedades, Diseño y Científica}
    private IssuanceFrequency freq; //TODO: Create enum {yearly, monthly, weekly, daily}
    private int subs;

    public Magazine(String id, String name, Calendar publicationDate, int pages, String coverURL,
            double cost, int category, int freq, int subs) {
        super(id, name, publicationDate, pages, coverURL, cost);
        this.category = MagazineCategory.getCategory(category);
        this.freq = IssuanceFrequency.get(freq);
        this.subs = subs;
    }