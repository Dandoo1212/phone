package com.kul.service;

import java.util.regex.Pattern;

import static com.kul.service.PhoneNumber.createIncorrectPhoneNumber;
import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

class PhoneNumberService {

  private PhoneNumberFormatter phoneNumberFormatter;

  private static final Pattern DIGITS_PATTERN = Pattern.compile("\\d{9}");

  PhoneNumberService(PhoneNumberFormatter phoneNumberFomatter) {
    this.phoneNumberFormatter = phoneNumberFomatter;
  }

  PhoneNumber recognizePhoneNumber(final String phoneNumber) {

    checkArgument(nonNull(phoneNumber), "Invalid Phone Number");

    String number = phoneNumber.trim();
    return validatePhoneNumber(phoneNumber)
        ? phoneNumberFormatter.formatPhoneNumber(number)
        : createIncorrectPhoneNumber();
  }

  private boolean validatePhoneNumber(String phoneNumber) {
    return DIGITS_PATTERN.matcher(phoneNumber).matches();
  }
}
