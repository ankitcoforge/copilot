package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class CalendarUtils {
    
    /**
     * Get current date in specified format
     */
    public static String getCurrentDate(String format) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return currentDate.format(formatter);
    }
    
    /**
     * Get current date in default format (MM/dd/yyyy)
     */
    public static String getCurrentDate() {
        return getCurrentDate("MM/dd/yyyy");
    }
    
    /**
     * Get current date in long format (Month dd, yyyy)
     */
    public static String getCurrentDateLong() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.ENGLISH);
        return currentDate.format(formatter);
    }
    
    /**
     * Get future date by adding days
     */
    public static String getFutureDate(int daysToAdd, String format) {
        LocalDate futureDate = LocalDate.now().plusDays(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return futureDate.format(formatter);
    }
    
    /**
     * Get future date by adding days (default format)
     */
    public static String getFutureDate(int daysToAdd) {
        return getFutureDate(daysToAdd, "MM/dd/yyyy");
    }
    
    /**
     * Get past date by subtracting days
     */
    public static String getPastDate(int daysToSubtract, String format) {
        LocalDate pastDate = LocalDate.now().minusDays(daysToSubtract);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return pastDate.format(formatter);
    }
    
    /**
     * Get past date by subtracting days (default format)
     */
    public static String getPastDate(int daysToSubtract) {
        return getPastDate(daysToSubtract, "MM/dd/yyyy");
    }
    
    /**
     * Get current timestamp
     */
    public static String getCurrentTimestamp() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }
    
    /**
     * Get timestamp for file naming
     */
    public static String getTimestampForFile() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return currentDateTime.format(formatter);
    }
    
    /**
     * Calculate days between two dates
     */
    public static long getDaysBetween(String startDate, String endDate, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return ChronoUnit.DAYS.between(start, end);
    }
    
    /**
     * Check if date is in the future
     */
    public static boolean isFutureDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate inputDate = LocalDate.parse(date, formatter);
        return inputDate.isAfter(LocalDate.now());
    }
    
    /**
     * Check if date is in the past
     */
    public static boolean isPastDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate inputDate = LocalDate.parse(date, formatter);
        return inputDate.isBefore(LocalDate.now());
    }
    
    /**
     * Get first day of current month
     */
    public static String getFirstDayOfMonth() {
        LocalDate firstDay = LocalDate.now().withDayOfMonth(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return firstDay.format(formatter);
    }
    
    /**
     * Get last day of current month
     */
    public static String getLastDayOfMonth() {
        LocalDate lastDay = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return lastDay.format(formatter);
    }
    
    /**
     * Get date for specific day of week in current week
     */
    public static String getDateForDayOfWeek(int dayOfWeek) {
        LocalDate currentDate = LocalDate.now();
        LocalDate targetDate = currentDate.plusDays(dayOfWeek - currentDate.getDayOfWeek().getValue());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return targetDate.format(formatter);
    }
}