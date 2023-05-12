package model;

import java.util.Calendar;

public class Book extends BibliographicProduct {
    private String review;
    private BookGenre genre; // TODO: BookGenre enum {Ciencia Ficción, Fantasía y Novela Histórica}
    private int copiesSold;
    private int readPages;


    public Book(String id, String name, Calendar publicationDate, int pages, String coverURL, double cost, String review, int genre, int copiesSold, int readPages) {
        super(id, name, publicationDate, pages, coverURL, cost);
        this.review = review;
        this.genre = BookGenre.getType(genre); // TODO: create getType switch method in BookGenre 1, 2, 3 respectively
        this.copiesSold = copiesSold;
        this.readPages = readPages;
    }

    
}
