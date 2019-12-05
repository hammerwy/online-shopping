package com.wy.shopping.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author wy
 * @date 2019/10/12
 * @description
 */
public class TestDate {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        final Date parse1 = sdf1.parse("Fri Oct 11 11:30:00 CST 2019");
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        final String format = sdf.format(parse1);
        System.out.println(format);
    }
}
