package com.kul.service;

import static com.kul.service.PhoneNumber.createCorrectPhoneNumber;

class PhoneNumberFormatter {

  private PhoneNumberTypeRecognizer phoneNumberTypeRecognizer;

  public PhoneNumberFormatter(PhoneNumberTypeRecognizer phoneNumberTypeRecognizer) {
    this.phoneNumberTypeRecognizer = phoneNumberTypeRecognizer;
  }

  PhoneNumber formatPhoneNumber(String phoneNumber) {
    PhoneNumberType type = phoneNumberTypeRecognizer.recognize(phoneNumber);
    return createCorrectPhoneNumber(type,phoneNumber);
  }
}
