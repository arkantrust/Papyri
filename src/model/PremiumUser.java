package model;

import java.util.Calendar;
import java.time.Month;
import java.util.ArrayList;

public class PremiumUser extends User implements Payable {

    // attributes
    private String nickname;
    private String avatar;
    private String card;
    private Month lastMonthPaid;
    private double[] payments;

    // methods
    public PremiumUser(String name, String email, String password, String id, Calendar initDate,
            ArrayList<String> library, String nickname, String avatar, String card, int lastMonthPaid,
            double[] payments) {

        super(name, email, password, id, initDate, library);
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

    /**
     * This Java function generates a payment for the current month and stores it in
     * an array.
     * 
     * @param value The value parameter is a double type variable that represents
     *              the payment amount to be generated.
     */
    @Override
    public void generatePayment(double value) {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        payments[month] = value;
    }

    /**
     * This Java function checks if a payment was made in the last month and returns
     * a boolean value indicating if the payment is considered late.
     * 
     * @return A boolean value indicating whether the person is a defaulter or not.
     */
    @Override
    public boolean isDefaulter() {
        boolean isDefaulter;

        isDefaulter = (lastMonthPaid.getValue() == Calendar.getInstance().get(Calendar.MONTH)) ? false : true;
        return isDefaulter;
    }

    /**
     * The function returns a message indicating whether the user has won a Spotify
     * coupon for a month of premium membership based on the input month.
     * 
     * @param randMonth an integer representing the month for which the surprise is
     *                  being checked. It is used to access the corresponding
     *                  element in the payments array.
     * @return The method is returning a message string that either informs the user
     *         that they have won a Spotify coupon for 1 month of premium membership
     *         or apologizes for not receiving a surprise.
     */
    @Override
    public String surprise(int randMonth) {
        var message = "";
        if (payments[randMonth - 1] != 0) {
            message += "You have won a spotify coupon for 1 month of premium membership. Chcek your email!";
        } else {
            message += "Sorry, you didn't get a surprise.";
        }
        return message;
    }
}
