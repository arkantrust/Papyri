package model;

import java.util.Calendar;
import java.time.Month;
import java.util.ArrayList;

public class PremiumUser extends BaseUser implements Payable {

    // attributes
    private String nickname;
    private String avatar;
    private String card;
    private Month lastMonthPaid;
    private double[] payments;

    // methods
    public PremiumUser(String name, String email, String password, String id, Calendar initDate, boolean ads,
            ArrayList<String> library, int boughtBooks, int subscribedMagazines, String nickname,
            String avatar, String card, int lastMonthPaid, double[] payments) {

        super(name, email, password, id, initDate, ads, library, boughtBooks, subscribedMagazines);
        this.nickname = nickname;
        this.avatar = avatar;
        this.card = card;
        this.lastMonthPaid = Month.of(lastMonthPaid);
        this.payments = payments; // 12 months in a year
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

    public Month getLastMonthPaid() {
        return lastMonthPaid;
    }

    public void setLastMonthPaid(Month lastMonthPaid) {
        this.lastMonthPaid = lastMonthPaid;
    }

    public double[] getPayments() {
        return payments;
    }

    public void setPayments(double[] payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        String toString = super.toString();
        toString += "Nickname: " + nickname + "\n";
        toString += "Avatar: " + avatar + "\n";
        toString += "Last Payment: " + lastMonthPaid.toString() + "\n";
        return toString;
    }

    @Override
    public String generatePayment(double value) {
        var info = "A receipt by $" + value + " will be sent to your email";
        int month = Calendar.getInstance().get(Calendar.MONTH);
        payments[month] = value;
        return info;
    }

    @Override
    public boolean isDefaulter() {
        boolean isDefaulter;
        
        isDefaulter = (lastMonthPaid.getValue() == Calendar.getInstance().get(Calendar.MONTH)) ? false : true;
        return isDefaulter;
    }

    @Override
    public String surprise(int month, char randLetter) {
        var message = "";
        if (payments[month - 1] != 0) {
            message += "You have won a spotify coupon for 1 month of premium membership. Chcek your email!";
        } else {
            message += "Sorry, you didn't get a surprise.";
        }
        return message;
    }

    @Override
    public void addToLibrary(String productID) {
        super.addToLibrary(productID);
    }
}
