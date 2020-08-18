package utils.enums;

public enum LoginCredentials {
    VALID_USERNAME("guest@example.com"),
    VALID_PASSWORD("Password");

    private final String text;

    private LoginCredentials(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}