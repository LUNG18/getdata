package com.lung.getdata.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTool {

    private static SimpleDateFormat dateFormat;

    public static Long string2long(String dateTime, String format) throws ParseException {
        dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateTime).getTime() / 1000;
    }

    public static String long2string(Long date, String format){
        dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(date * 1000));
    }
}
