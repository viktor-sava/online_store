package com.example.online_store.validation;

import com.example.online_store.model.Account;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountValidator {

    private static final String EMAIL_REGEX = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";

    private static final String PHONE_REGEX = "^(\\+?)(380|0)[0-9]{9}$";

    private final Set<String> messages;

    public AccountValidator() {
        this.messages = new HashSet<>();
    }

    public void validateEmail(String email) {
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            messages.add("Please enter a valid email address.");
        }
    }

    public void validatePhone(String phone) {
        if (!Pattern.matches(PHONE_REGEX, phone)) {
            messages.add("Please enter a valid phone number.");
        }
    }

    public void validateField(String field, String fieldName) {
        if (!(field.length() > 3)) {
            messages.add("Please enter a valid " + fieldName + ".");
        }
    }

    public void passwordCompareHash(String requestedPassword, String databasePassword) {
        if(!requestedPassword.equals(databasePassword)) {
            messages.add("Your email and/or password do not match");
        }
    }

    public void validateNotExistence(Account account) {
        if (account != null) {
            messages.add("User email and/or phone already exists");
        }
    }

    public void validateExistence(Account account) {
        if (account == null) {
            messages.add("Your email and/or password do not match");
        }
    }

    public String normalizePhone(String phone) {
        Matcher matcher = Pattern.compile(PHONE_REGEX).matcher(phone);
        String normalizedPhone = "";
        if (matcher.find()) {
            if (matcher.group(1).equals(""))
                normalizedPhone += "+";
            if (matcher.group(2).equals("0"))
                normalizedPhone += "38";
            normalizedPhone += matcher.group(0);
        }
        return normalizedPhone;
    }

    public boolean isValid() {
        return messages.isEmpty();
    }

    public void checkCreating(boolean isCreated) {
        if (!isCreated) {
            messages.add("Cannot create an account");
        }
    }

    public void passwordCompare(String password1, String password2) {
        if (!password1.equals(password2)) {
            messages.add("Password is not equal to repeat password");
        }
    }

    public Set<String> getMessages() {
        return new HashSet<>(this.messages);
    }
}
