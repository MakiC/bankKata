package com.mybank.service;

import com.mybank.exceptions.DateNotWellFormattedException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author camara
 */
public class DateManagementService {
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    public static String format(Date date) {
        return formatter.format(date);
    }

    public static Date convertToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);

        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new DateNotWellFormattedException("Failed to convert value " + dateString + " with date format " + (formatter).toPattern());
        } catch (NullPointerException e) {
            throw new DateNotWellFormattedException("Date Cannot be null");
        }
    }
}
