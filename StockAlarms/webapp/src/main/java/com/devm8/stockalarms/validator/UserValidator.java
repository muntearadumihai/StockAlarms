package com.devm8.stockalarms.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public static boolean isValidName(String name) {
        return name != null && !name.isEmpty() && !name.isBlank();
    }

    public static boolean isValidPassword(String password) {
        return password != null && !password.isEmpty() && !password.isBlank();
    }

    public static boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

}
