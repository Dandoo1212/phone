package com.britenet.service;

public class PhoneNumberFacade {

  private PhoneNumberService phoneNumberService;
  private PhoneNumberTypeRecognizer phoneNumberTypeRecognizer;
  private PhoneNumberFormatter phoneNumberFormatter;

  public PhoneNumberFacade() {
    phoneNumberTypeRecognizer = new PhoneNumberTypeRecognizer();
    phoneNumberFormatter = new PhoneNumberFormatter(phoneNumberTypeRecognizer);
    phoneNumberService = new PhoneNumberService(phoneNumberFormatter);
  }

  public PhoneNumber formatPhoneNumber(String phoneNumber) {
    return phoneNumberService.recognizePhoneNumber(phoneNumber);
  }
}
