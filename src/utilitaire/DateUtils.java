package utilitaire;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Erreur de format de date : " + e.getMessage());
            return null;
        }
    }

    public static String formatDate(LocalDate date) {
        return CUSTOM_FORMATTER.format(date);
    }
}
