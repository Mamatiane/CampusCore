package campuscore.model;

/**
 *Value Objects: This class groups email and phone together
 * contact information is its own concept it has its own rules
 * Contact rules change? Only touch ContactInfo
 * 
 */
public final class ContactInfo {
    private final String email;
    private final String phoneNumber;

    public ContactInfo(String email, String phoneNumber) {
        this.email = validateEmail(email);
        this.phoneNumber = phoneNumber;
    }
    /**
     * method overloading when a student has only email trigger this method
     * @param email 
     */
    public ContactInfo(String email) {
        this(email, null);
    }
    
    public static String validateEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
        return email.trim().toLowerCase();
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean hasPhone() {
        return phoneNumber != null && !phoneNumber.isBlank();
    }
    
    /**
     * Immutable update - returns a NEW ContactInfo with the updated email.
     * @param newEmail
     * @return 
     */
    public ContactInfo withEmail(String newEmail) {
        return new ContactInfo(newEmail, this.phoneNumber);
    }

    @Override
    public String toString() {
        return hasPhone() ? email + " | " + phoneNumber : email;
    }