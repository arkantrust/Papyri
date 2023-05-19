package model;

public abstract interface Payable {

    public String generatePay(int value);

    public boolean isDefaulter(int debt);
}
