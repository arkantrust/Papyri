package model;

public abstract interface Payable {

    /**
     * This Java function generates a payment with a specified value.
     * 
     * @param value The value parameter is a double data type that represents the
     *              amount of payment to be generated.
     *              It could be a monetary value or any other numerical value that
     *              needs to be processed by the method.
     */
    public void generatePayment(double value);

    /**
     * The function checks if a certain condition is met and returns a boolean
     * value.
     * 
     * @return A boolean value is being returned, which indicates whether the object
     *         being referred to is a defaulter or not.
     */
    public boolean isDefaulter();
}
