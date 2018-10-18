package com.ikhwanul.ikhlas.iiwandroid.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatRupiah {
    public static String useFormat(String value){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        return formatRupiah.format(Double.valueOf(value));
    }

}
