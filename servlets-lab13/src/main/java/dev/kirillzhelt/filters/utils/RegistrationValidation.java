package dev.kirillzhelt.filters.utils;

import dev.kirillzhelt.db.models.User;

public class RegistrationValidation {

    private RegistrationValidation() {

    }

    public static RegistrationValidationInfo validate(User user, String confirmationPassword) {
        if (!user.getPassword().equals(confirmationPassword)) {
            return new RegistrationValidationInfo(true, "Passwords do not match.");
        }

        if (user.getName().length() < 3) {
            return new RegistrationValidationInfo(true, "Name is too short");
        }

        if (user.getLogin().length() < 3) {
            return new RegistrationValidationInfo(true, "Login is too short");
        }

        return new RegistrationValidationInfo(false);
    }

}
