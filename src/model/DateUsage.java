package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public interface DateUsage {
    public static String DateToString(Calendar date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Date format can be - or /
        String dateToString = dateFormat.format(date.getTime());
        return dateToString;
    }
}
