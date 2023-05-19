package model;

public abstract interface Payable {

    public static String generatePay() {
        String confirmation = "";
        return confirmation;
    }

    public boolean isDefaulter();
}
