package com.mybank.service;

import com.mybank.exception.dateNotWellFormattedException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author camara
 */
public class DateManagementService {
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
	public static String format(Date date) {
		return formatter.format(date);
	}

	public static Date convertToDate(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);

		try{return formatter.parse(dateString);}
		catch (ParseException e) {
			throw new dateNotWellFormattedException("Failed to convert value " + dateString + " with date format " + (formatter).toPattern(), e);
		}
	}
}
