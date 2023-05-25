package model;

import java.util.Calendar;
import java.util.ArrayList;

public class Reviewer extends PremiumUser {
    private String interest;
    private int reviewCount;
    private String blog;

    public Reviewer(String name, String email, String password, String id,
            int internalID, Calendar initDate, boolean ads, ArrayList<Product> productsOwned,
            String productsOwnedList, int productsOwnedCount, int boughtBooks, int subscribedMagazines,
            String nickname, String avatar, String card, int lastPaidMonth, double[] payments, String interest,
            int reviewCount, String blog) {
        super(name, email, password, id, internalID, initDate, ads, productsOwned, productsOwnedList,
                productsOwnedCount,
                boughtBooks, subscribedMagazines, nickname, avatar, card, lastPaidMonth, payments);
        this.interest = interest;
        this.reviewCount = reviewCount;
        this.blog = blog;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        var info = "";
        info += super.toString();
        info += "Interest: " + interest + "\n";
        info += "ReviewCount: " + reviewCount + "\n";
        info += "Blog: " + blog + "\n";
        return info;
    }

    @Override
    public String surprise(int randInt, char s) {
        var message = "";
        if (randInt < reviewCount) {
            message += "Congratulations! You won a trip to San Andres!";
        } else {
            message = "Sorry, you didn't get a surprise.";
        }
        return message;
    }
}
