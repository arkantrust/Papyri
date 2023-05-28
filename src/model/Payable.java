package model;

public abstract interface Payable {

    public String generatePayment(double value);

    public boolean isDefaulter();
}
