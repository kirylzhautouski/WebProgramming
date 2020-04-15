package dev.kirillzhelt.filters.utils;

public class RegistrationValidationInfo {

    private final boolean hasErrors;

    private final String message;

    public RegistrationValidationInfo(boolean hasErrors) {
        this(hasErrors, "");
    }

    public RegistrationValidationInfo(boolean hasErrors, String message) {
        this.hasErrors = hasErrors;
        this.message = message;
    }

    public boolean hasErrorsStatus() {
        return hasErrors;
    }

    public String getMessage() {
        return message;
    }
}
