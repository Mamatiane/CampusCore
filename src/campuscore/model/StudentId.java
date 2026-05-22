package campuscore.model;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Student ID is immutable(will never change)
 * This class will generate the stud ID and also validate the stud ID
 * 
 */

public final class StudentId {
    private final String studID;

    public StudentId(String studID) {
        this.studID = studID;
    }
    
    /**
     * Factory Method:
     * Format: 4-digit year plus 6 more random numbers e.g 2026001122
     * 
     * @param enrollmentYear
     * @return 
     */
    
    public static StudentId generateId(int enrollmentYear) {
        if (enrollmentYear < 2000 || enrollmentYear > 2100) {
            throw new IllegalArgumentException("Enrollment Year seems wrong: " + enrollmentYear);
        }
        long randomPart = ThreadLocalRandom.current().nextLong(100_000L, 999_999L);
        return new StudentId(enrollmentYear + String.valueOf(randomPart));
    }
    
    /**
     * Loading existing students
     * @param studID
     * @return 
     */
    public static StudentId of(String studID) {
        if (studID == null || !studID.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid student ID format: " + studID);
        }
        return new StudentId(studID);
    }

    public String getStudID() {
        return studID;
    }

    @Override
    public String toString() {
        return studID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StudentId)) {
            return false;
        }
        return studID.equals(((StudentId)obj).studID);
    }

    @Override
    public int hashCode() {
        return studID.hashCode();
    }
    
}
