package com.britenet.converter;

import java.time.Month;

  class DateValidator {

     boolean validateBirthDate(String pesel) {
        int year = Integer.parseInt(pesel.substring(0, 2));
        int month = Integer.parseInt(pesel.substring(2, 4));
        int day = Integer.parseInt(pesel.substring(4, 6));
        return isMonthValid(month) && isDayInMonthValid(year, month, day);
    }

    private boolean isMonthValid(int month) {
        return month % 20 <= 12;
    }

    private boolean isDayInMonthValid(int year, int month, int day) {
        Month monthOfYear = Month.of(month % 20);
        return day <= monthOfYear.length(isLeapYear(year, month));
    }

    private boolean isLeapYear(int year, int month) {
        if (year == 0 && month / 20 == 1) { // if we are in year 2000
            return true;
        } else {
            return year % 4 == 0;
        }
    }
}
