package model;

import java.util.Calendar;

public class PremiumUser extends User {

    // attributes
    private String nickname;
    private String avatar;
    private String card;
    private int lastMonthPaid;
    private double[] payments;

    // methods
    public PremiumUser(String name, String email, String id, Calendar initDate, String nickname, String avatar, String card,
            int lastMonthPaid,
            double[] payments) {
        super(name, email, id, initDate);
        this.nickname = nickname;
        this.avatar = avatar;
        this.card = card;
        this.lastMonthPaid = lastMonthPaid;
        this.payments = payments;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getLastMonthPaid() {
        return lastMonthPaid;
    }

    public void setLastMonthPaid(int lastMonthPaid) {
        this.lastMonthPaid = lastMonthPaid;
    }

    public double[] getPayments() {
        return payments;
    }

    public void setPayments(double[] payments) {
        this.payments = payments;
    }

    @Override
    public String surprise(int month, char randLetter) {
        var info = "";
        // TODO: implement
        return info;
    }

}