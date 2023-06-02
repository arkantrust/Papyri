package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public interface DateManipulator {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * This function converts a string representation of a date to a Calendar
     * object.
     * 
     * @param stringedDate A string representation of a date in a specific format
     *                     that can be parsed by
     *                     the `dateFormat` object.
     * @return A Calendar object is being returned.
     */
    default Calendar stringToDate(String stringedDate) {
        Calendar date = Calendar.getInstance();

        try {
            date.setTime(dateFormat.parse(stringedDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * The function converts a Calendar object to a String representation of a date
     * in the format
     * "dd-MM-yyyy".
     * 
     * @param date The parameter "date" is a Calendar object representing a specific
     *             date and time.
     * @return A string representation of the input calendar date in the format
     *         "dd-MM-yyyy".
     */
    default String dateToString(Calendar date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Date format can be - or /
        String dateToString = dateFormat.format(date.getTime());
        return dateToString;
    }
}
