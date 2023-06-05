package model;

import java.util.Calendar;
import java.util.ArrayList;

public class Reviewer extends PremiumUser {
    private String interest;
    private int reviewCount;
    private String blog;

    public Reviewer(String name, String email, String password, String id, Calendar initDate, ArrayList<String> library,
            String nickname, String avatar,
            String card, int lastPaidMonth, double[] payments, String interest, int reviewCount, String blog) {
        super(name, email, password, id, initDate, library, nickname, avatar, card, lastPaidMonth, payments);
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
        info += "Reviews: " + reviewCount + "\n";
        info += "Blog: " + blog + "\n";
        return info;
    }

    /**
     * The function returns a message congratulating the user on winning a trip to
     * San Andres if their random integer is less than the review count,
     * otherwise it returns a message saying they didn't get a surprise.
     * 
     * @param randInt an integer value that is used to determine if the user will
     *                receive a surprise or not.
     * @return The method is returning a message as a String. The message will
     *         either congratulate the user for winning a trip to San Andres
     *         if the random integer is less than the review count, or apologize for
     *         not getting a surprise if the random integer is greater than or equal
     *         to the review count.
     */
    @Override
    public String surprise(int randInt) {
        var message = "";
        if (randInt < reviewCount) {
            message += "Congratulations! You won a trip to San Andres!";
        } else {
            message = "Sorry, you didn't get a surprise.";
        }
        return message;
    }
}
