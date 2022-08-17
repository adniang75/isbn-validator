package com.alassaneniang.isbntools;

import java.util.regex.Pattern;

public class ValidateISBN {

    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final char ALLOWED_LAST_CHAR = 'X';

    private static boolean isValidShortISBN(String isbn) {
        int total = 0;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            if (i == 9 && isbn.charAt(i) == 'X') {
                total += 10;
                continue;
            }
            total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGTH - i);
        }
        return total % 11 == 0;
    }

    private static boolean isValidLongISBN(String isbn) {
        int total = 0;
        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            total += Character.getNumericValue(isbn.charAt(i)) * (i % 2 == 0 ? 1 : 3);
        }
        return total % 10 == 0;
    }

    public boolean checkISBN(String isbn) {
        if (isbn.length() != SHORT_ISBN_LENGTH && isbn.length() != LONG_ISBN_LENGTH) {
            throw new NumberFormatException("ISBN numbers must be 10 digits long");
        }
        if (!Pattern.matches("\\d{0,9}[\\d," + ALLOWED_LAST_CHAR + "]", isbn) && !Pattern.matches("\\d{0,13}", isbn)) {
            throw new NumberFormatException("ISBN numbers can only contain numeric digits");
        }
        return isbn.length() == SHORT_ISBN_LENGTH ? isValidShortISBN(isbn) : isValidLongISBN(isbn);
    }

}
