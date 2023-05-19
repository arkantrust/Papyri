package model;

import java.util.Calendar;

public class Book extends BibliographicProduct {
    private String review;
    private BookGenre genre;
    private int copiesSold;
    private int readPages;

    public Book(String id, String name, Calendar publicationDate, int pages, String coverURL, double price,
            String review, BookGenre genre, int copiesSold, int readPages) {
        super(id, name, publicationDate, pages, coverURL, price);
        this.review = review;
        this.genre = genre;
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

    @Override
    public String toString() {
        String info = "";
        info += super.toString();
        info += "Review: " + review + "\n";
        info += "Genre: " + genre.getName() + "\n";
        info += "Copies Sold: " + copiesSold + "\n";
        info += "Read Pages: " + readPages + "\n";
        return info;
    }
}
