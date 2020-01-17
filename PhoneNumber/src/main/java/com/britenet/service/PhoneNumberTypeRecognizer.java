package com.britenet.service;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

class PhoneNumberTypeRecognizer {

  private final Set<String> areaCodes =
      ImmutableSet.of(
          "12", "13", "14", "15", "16", "17", "18", "22", "23", "24", "25", "29", "32", "33", "34",
          "41", "42", "43", "44", "46", "52", "54", "55", "56", "58", "59", "61", "62", "63", "65",
          "67", "68", "71", "74", "75", "76", "77", "81", "82", "83", "84", "85", "86", "87", "89",
          "91", "94", "95");

  private Set<String> specialCodes = ImmutableSet.of("70", "80");

  PhoneNumberType recognize(String phoneNumber) {

    String prefix = phoneNumber.substring(0, 2);

    if (specialCodes.contains(prefix)) {
      return PhoneNumberType.SPECIAL;
    } else if (areaCodes.contains(prefix)) {
      return PhoneNumberType.LANDLINE;
    }
    return PhoneNumberType.MOBILE;
  }
}
