package com.sold.hotel.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private static final Pattern LOGIN_REGEX =
            Pattern.compile("^[a-zA-Z0-9._-]{3,}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern PHONE_REGEX =
            Pattern.compile("^[+]?\\d{11}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern ONLY_LETTERS_REGEX =
            Pattern.compile("^[-a-zA-Zа-яА-Я ]+$", Pattern.CASE_INSENSITIVE);

    private static final Pattern ONLY_NUMBERS_REGEX =
            Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE);

    private static final Pattern COST_REGEX =
            Pattern.compile("^(\\$)?(([1-9]\\d{0,2}(\\,\\d{3})*)|([1-9]\\d*)|(0))(\\.\\d{2})?$", Pattern.CASE_INSENSITIVE);

    private static final Pattern STATUS_REGEX =
            Pattern.compile("^[0-1]+$", Pattern.CASE_INSENSITIVE);

    private static final Pattern PASSPORT_REGEX =
            Pattern.compile("^\\d{10}+$", Pattern.CASE_INSENSITIVE);

    public Validate() {
    }

    public static boolean isName(String str) {
        Matcher matcher = ONLY_LETTERS_REGEX.matcher(str);
        return matcher.find();
    }

    public static boolean isPhone(String str) {
        Matcher matcher = PHONE_REGEX.matcher(str);
        return matcher.find();
    }

    public static boolean isLogin(String str) {
        Matcher matcher = LOGIN_REGEX.matcher(str);
        return matcher.find();
    }

    public static boolean isPassword(String str) {
        Matcher matcher = PASSWORD_REGEX.matcher(str);
        return matcher.find();
    }

    public static boolean isCost(String str) {
        Matcher matcher = COST_REGEX.matcher(str);
        return matcher.find();
    }

    public static boolean isPost(String str) {
        Matcher matcher = ONLY_LETTERS_REGEX.matcher(str);
        return matcher.find();
    }

    public static boolean isId(String str) {
        Matcher matcher = ONLY_NUMBERS_REGEX.matcher(str);
        return matcher.find();
    }

    public static boolean isNumberOfSeats(String str) {
        Matcher matcher = ONLY_NUMBERS_REGEX.matcher(str);
        return matcher.find();
    }

    public static boolean isStatus(String str) {
        Matcher matcher = STATUS_REGEX.matcher(str);
        return matcher.find();
    }

    public static boolean isPassport(String str) {
        Matcher matcher = PASSPORT_REGEX.matcher(str);
        return matcher.find();
    }

    public static boolean isCorrectDate(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        return date.isBefore(currentDate);
    }

    public static boolean isDateMoreDate(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isBefore(secondDate);
    }
}
