package com.fsd.validators;

import java.util.regex.Pattern;

public interface Patterns {
    Pattern USERNAME = Pattern.compile("[a-zA-Z0-9]*");
    Pattern AMOUNT_RANGE = Pattern.compile("^[0-9]+\\.[0-9]{2}(\\-[0-9]+\\.[0-9]{2})?$");
    Pattern AMOUNT = Pattern.compile("[0-9]+\\.[0-9]{2}");
    Pattern NAME = Pattern.compile("[a-zA-Z]*\\S");
}
