package com.fsd.validators;

import java.util.regex.Pattern;

public interface Patterns {
    Pattern USERNAME = Pattern.compile("[a-zA-Z0-9]*");

    // Minimum eight characters, at least one letter, one number and one special character:
    Pattern PASSWORD = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");

    Pattern AMOUNT_RANGE = Pattern.compile("^[0-9]+\\.[0-9]{2}(\\-[0-9]+\\.[0-9]{2})?$");

    Pattern AMOUNT = Pattern.compile("[0-9]+\\.[0-9]{2}");

    Pattern EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    Pattern MOBILE = Pattern.compile("[7-9][0-9]{9}");

    Pattern ADDRESS = Pattern.compile("^[#.0-9a-zA-Z\\s,-]+$");
}
