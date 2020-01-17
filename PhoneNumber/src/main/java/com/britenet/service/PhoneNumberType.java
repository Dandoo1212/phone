package com.britenet.service;

import java.util.function.Function;

enum PhoneNumberType {
  LANDLINE(
      (notFormattedNumber) -> {
        return new StringBuilder(notFormattedNumber)
            .insert(2, "-")
            .insert(6, "-")
            .insert(9, "-")
            .toString();
      }),
  MOBILE(
      (notFormattedNumber) -> {
        return new StringBuilder(notFormattedNumber).insert(3, "-").insert(7, "-").toString();
      }),
  SPECIAL(MOBILE.format);

  private Function<String, String> format;

  PhoneNumberType(Function<String, String> format) {
    this.format = format;
  }

  public String format(String notFormattedNumber) {
    return format.apply(notFormattedNumber);
  }
}
