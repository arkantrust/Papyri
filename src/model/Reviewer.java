package model;

import java.util.Calendar;

public class Reviewer extends PremiumUser {
    private Interest interest; // TODO: Create enum Interest
    private int reviewCount;
    private String blog;

    public Reviewer(String name, String email, String password, String id, int internalID, Calendar initDate,
            String nickname, String avatar, String card, int lastPaidMonth, double[] payments,  Interest interest, int reviewCount, String blog) {
        super(name, email, password, id, internalID, initDate, nickname, avatar, card, lastPaidMonth, payments);
        this.interest = interest;
        this.reviewCount = reviewCount;
        this.blog = blog;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
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
    public surprise(internalID, 0) {} // TODO: Implement surprise method
}
