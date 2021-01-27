package com.epam.jwd.final_project.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateConverterUtil {

    private final static String DATE_FORMAT =
            PropertyReaderUtil.getApplicationProperties().getDateFormat();
    private final static DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT);

    private DateConverterUtil() {
    }

    public static LocalDate convertToLocalDate(String dateFromDb) {
        return LocalDate.parse(dateFromDb, DATE_FORMATTER);
    }

}
