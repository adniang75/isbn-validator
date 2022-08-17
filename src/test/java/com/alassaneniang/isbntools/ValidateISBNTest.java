package com.alassaneniang.isbntools;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateISBNTest {

    @Test
    @DisplayName("Check A Valid ISBN")
    void checkAValid10DigitIsbn() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue(result, "first value");
        result = validator.checkISBN("0140177396");
        assertTrue(result, "second value");
    }

    @Test
    @DisplayName("ISBN with 13 Digit Numbers Ending With An X Are Valid")
    void isbn10DigitNumbersEndingWithAnXAreValid() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }

    @Test
    @DisplayName("Check An Invalid ISBN")
    void checkAnInvalid10DigitIsbn() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test
    @DisplayName("Nine Digit ISBN Are Not Allowed")
    void nineDigitIsbnAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class, () -> validator.checkISBN("123456789"));
    }

    @Test
    @DisplayName("Non-Numeric ISBN Are Not Allowed")
    void nonNumericIsbnAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class, () -> validator.checkISBN("helloworld"));
    }

    @Test
    @DisplayName("Check A Valid 13 Digits ISBN")
    void checkAValid13DigitsIsbn() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9782205088168");
        assertTrue(result);
    }

    @Test
    @DisplayName("Check An Invalid 13 Digits ISBN")
    void checkAInvalid13DigitsIsbn() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9782205088161");
        assertFalse(result);
    }

}