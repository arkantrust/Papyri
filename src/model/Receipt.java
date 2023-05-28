package model;

import java.util.Calendar;

public class Receipt {
    private String concept;
    private User buyer;
    private Calendar date;
    private double amount;
    
    public Receipt(String concept, User buyer, Calendar date, double amount) {
        this.concept = concept;
        this.buyer = buyer;
        this.date = date;
        this.amount = amount;
    }
    
    public String getConcept() {
        return concept;
    }
    public void setConcept(String concept) {
        this.concept = concept;
    }
    public User getBuyer() {
        return buyer;
    }
    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
    public Calendar getDate() {
        return date;
    }
    public void setDate(Calendar date) {
        this.date = date;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        String toString = "Concept: " + concept + "\n";
        toString += "Buyer: " + buyer + "\n";
        toString += "Date: " + date + "\n";
        toString += "Amount: " + amount + "\n";
        return toString;
    }
}
