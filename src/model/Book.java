package model;

import java.util.Calendar;

public class Book extends BibliographicProduct {
    private String review;
    private BookGenre genre;
    private int copiesSold;
    private int readPages;

    public Book(String id, String name, Calendar publicationDate, int pages, String coverURL, double cost,
            String review, int genre, int copiesSold, int readPages) {
        super(id, name, publicationDate, pages, coverURL, cost);
        this.review = review;
        this.genre = BookGenre.getGenre(genre);
        this.copiesSold = copiesSold;
        this.readPages = readPages;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public int getCopiesSold() {
        return copiesSold;
    }

    public void setCopiesSold(int copiesSold) {
        this.copiesSold = copiesSold;
    }

    public int getReadPages() {
        return readPages;
    }

    public void setReadPages(int readPages) {
        this.readPages = readPages;
    }
}
