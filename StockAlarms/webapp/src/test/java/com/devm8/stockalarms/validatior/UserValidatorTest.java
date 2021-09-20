package com.devm8.stockalarms.validatior;

import com.devm8.stockalarms.validator.UserValidator;
import org.junit.jupiter.api.Test;

import static com.devm8.stockalarms.validator.UserValidator.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserValidatorTest {

    private UserValidator userValidator;

    @Test
    void testIsValidNameReturnsTrueWhenNotEmpty() {
        String name = "admin";
        boolean actual = isValidName(name);
        assertTrue(actual);
    }

    @Test
    void testIsValidNameReturnsFalseWhenEmpty() {
        String name = "";
        boolean actual = isValidName(name);
        assertFalse(actual);
    }

    @Test
    void testIsValidPasswordReturnsTrueWhenNotEmpty() {
        String password = "password";
        boolean actual = isValidPassword(password);
        assertTrue(actual);
    }

    @Test
    void testIsValidPasswordReturnsFalseWhenEmpty() {
        String password = "";
        boolean actual = isValidPassword(password);
        assertFalse(actual);
    }

    @Test
    void testIsValidEmailReturnsTrueWhenEmailHasCorrectForm() {
        String email = "admin@mail.com";
        boolean actual = isValidEmail(email);
        assertTrue(actual);
    }

    @Test
    void testIsValidEmailReturnsFalseWhenEmailHasIncorrectForm() {
        String email = "adm@in@mail.c1om";
        boolean actual = isValidEmail(email);
        assertFalse(actual);
    }
}
