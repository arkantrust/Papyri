package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateProcessing {
    public static String dateToString(Calendar date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Date format can be - or /
        String dateToString = dateFormat.format(date.getTime());
        return dateToString;
    }
}
