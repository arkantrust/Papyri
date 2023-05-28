package model;

import java.util.Calendar;

public class Receipt implements DateManipulator, Emboldenable {
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
        String toString = toBold("Concept: ") + concept + "\n";
        toString += toBold("Buyer: ") + buyer.getName() + "\n";
        toString += toBold("Date: ") + dateToString(date) + "\n";
        toString += toBold("Amount: $") + amount + "\n";
        return toString;
    }
}
