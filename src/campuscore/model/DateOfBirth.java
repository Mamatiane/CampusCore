package campuscore.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Value Objects: This class creates and store date of birth
 * It also has methods to calculate age and check if the person is a minor.
 * Date of birth is immutable(will never change)
 */
public class DateOfBirth {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final LocalDate date;

    public DateOfBirth(LocalDate date) {
        this.date = date;
    }
    
    public static DateOfBirth of(String dateString){
        try {
            LocalDate parsed = LocalDate.parse(dateString, FORMATTER);
            if (parsed.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Date of birth cannot be in the future.");
            }
            return new DateOfBirth(parsed);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Use format yyyy-MM-dd. Got: " + dateString);
        }
    }
    public int calculateAge() {
        return Period.between(date, LocalDate.now()).getYears();
    }

    public boolean isMinor() {
        return calculateAge() < 18;
    }

    @Override
    public String toString() {
        return date.format(FORMATTER);
    }
}
