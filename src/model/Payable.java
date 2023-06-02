package model;

public abstract interface Payable {

    /**
     * The function generates a payment string for a given value.
     * 
     * @param value The "value" parameter in the method signature represents the
     *              amount of money for
     *              which the payment needs to be generated. It is of type double,
     *              which means it can hold decimal
     *              values.
     * @return A String value is being returned.
     */
    public String generatePayment(double value);

    /**
     * The function checks if a certain condition is met and returns a boolean
     * value.
     * 
     * @return A boolean value is being returned, which indicates whether the object
     *         being referred to
     *         is a defaulter or not.
     */
    public boolean isDefaulter();
}
