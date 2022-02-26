/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author arquimedes
 */
public class Utils {

    private static Locale locale = new Locale("pt", "BR");

    public static String formatData(Date value) {
        return formatData(value, "dd/MM/yyyy");
    }

    public static String formatData(Date value, String format) {
        if (value == null) {
            return "";
        }
        DateFormat formatter = new SimpleDateFormat(format, locale);
        return formatter.format(value);
    }

}
