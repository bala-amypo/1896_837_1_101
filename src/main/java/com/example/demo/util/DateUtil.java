package com.example.demo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility methods for working with dates and times.
 */
public class DateUtil {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** Returns the current timestamp as LocalDateTime. */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /** Returns today's date as LocalDate. */
    public static LocalDate today() {
        return LocalDate.now();
    }

    /** Formats a LocalDate into a string (yyyy-MM-dd). */
    public static String formatDate(LocalDate date) {
        return date != null ? date.format(DATE_FORMATTER) : null;
    }

    /** Formats a LocalDateTime into a string (yyyy-MM-dd HH:mm:ss). */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DATE_TIME_FORMATTER) : null;
    }

    /** Parses a string (yyyy-MM-dd) into a LocalDate. */
    public static LocalDate parseDate(String dateStr) {
        return dateStr != null ? LocalDate.parse(dateStr, DATE_FORMATTER) : null;
    }

    /** Parses a string (yyyy-MM-dd HH:mm:ss) into a LocalDateTime. */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        return dateTimeStr != null ? LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER) : null;
    }
}
