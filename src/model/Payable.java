package model;

public abstract interface Payable {
    
    public String generatePay();

    public boolean isDefaulter();
}
