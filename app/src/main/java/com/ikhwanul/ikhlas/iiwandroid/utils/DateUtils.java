package com.ikhwanul.ikhlas.iiwandroid.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String DEFAULT_OUT_FORMAT = "dd/MM/yyyy";
    public static final String DEFAULT_IN_FORMAT = "yyyy-MM-dd";

    public static String format(String format, Date date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return  sdf.format(date);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static String format(String format, String date){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date _date = parse(date);
        return  sdf.format(_date);
    }

    public static String format(Date date){
        return format(DEFAULT_OUT_FORMAT, date);
    }

    public static String format(String date){
        return format(DEFAULT_OUT_FORMAT, date);
    }

    public static Date parse(String date){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_IN_FORMAT);
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static Date parse(String date, String format){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
