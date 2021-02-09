package com.epam.jwd.final_project.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Final class that provides static methods for converting a date
 * in the string format to the LocalDate object.
 */
public final class DateConverterUtil {

    /**
     * A constant that represents the format in which the date will be converted.
     */
    private final static String DATE_FORMAT =
            PropertyReaderUtil.getApplicationProperties().getDateFormat();
    /**
     * A DateTimeFormatter object to use in converting string to LocalDate object.
     */
    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private DateConverterUtil() {
    }

    /**
     * Returns LocalDate object that was received after parsing
     * a string taken from the database.
     *
     * @param dateFromDb date in a string format taken from the database
     * @return returns LocalDate object
     */
    public static LocalDate convertToLocalDate(String dateFromDb) {
        return LocalDate.parse(dateFromDb, DATE_FORMATTER);
    }

}
