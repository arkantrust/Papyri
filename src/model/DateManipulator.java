package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public interface DateManipulator {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    default Calendar stringToDate(String stringedDate) {
        Calendar date = Calendar.getInstance();

        try {
            date.setTime(dateFormat.parse(stringedDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    default String dateToString(Calendar date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Date format can be - or /
        String dateToString = dateFormat.format(date.getTime());
        return dateToString;
    }
}
