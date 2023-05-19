package model;

public abstract interface Payable {

    public String generatePayment(int value);

    public boolean isDefaulter(int debt);
}
