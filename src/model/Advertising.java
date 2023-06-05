package model;

public interface Advertising {

    public String[] ads = new String[] {
            "¡Suscríbete al Combo Plus y llévate Disney+ y Star+ a un precio increíble!",
            "Ahora tus mascotas tienen una app favorita: Laika. Los mejores productos para tu peludito.",
            "¡Estamos de aniversario! Visita tu Éxito más cercano y sorpréndete con las mejores ofertas."
    };

    /**
     * The function "showAd" takes in a user's name, a product name, and a count,
     * and returns a string representing an advertisement.
     * 
     * @param user    A String representing the user id of the user who
     *                will see the ad.
     * @param product The "product" parameter is a String that represents the type
     *                of the product the user is viewing.
     * @param count   The parameter "count" is an integer that represents the number
     *                of times the
     *                advertisement for the given product should be shown to the
     *                user.
     * @return The method is returning a String.
     */
    public String showAd(String user, String product, int count);
}
