package com.kul.converter;

import java.time.LocalDate;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Integer.parseInt;
import static java.util.Objects.nonNull;

class PeselService {

  private DateValidator dateValidator;
  private PeselValidator peselValidator;

  PeselService(DateValidator dateValidator, PeselValidator peselValidator) {
    this.dateValidator = dateValidator;
    this.peselValidator = peselValidator;
  }

  PeselInfo extractPeselInfo(final String pesel) {

    checkArgument(nonNull(pesel), "Invalid pesel");

    String trimmedPesel = pesel.trim();
    if (validatePesel(trimmedPesel)) {
      return new PeselInfo(
          extractGenderFromPesel(trimmedPesel),
          assembleBirthDate(trimmedPesel),
          true,
          trimmedPesel);
    }
    return new PeselInfo(null, null, false, trimmedPesel);
  }

  private boolean validatePesel(String pesel) {
    return peselValidator.validatePeselStructure(pesel) && dateValidator.validateBirthDate(pesel);
  }

  private Gender extractGenderFromPesel(String pesel) {
    return extractGenderDigit(pesel) % 2 == 1 ? Gender.MALE : Gender.FEMALE;
  }

  private LocalDate assembleBirthDate(String pesel) {
    return LocalDate.of(extractFullYear(pesel), extractMonth(pesel), extractDay(pesel));
  }

  private int extractFullYear(String pesel) {
    int year = 1900 + parseInt(pesel.substring(0, 2));
    int month = parseInt(pesel.substring(2, 4));
    int amountOfCenturies = month / 20;
    return year = amountOfCenturies == 4 ? year - 100 : year + (amountOfCenturies * 100);
  }

  private int extractMonth(String pesel) {
    return parseInt(pesel.substring(2, 4)) % 20;
  }

  private int extractDay(String pesel) {
    return parseInt(pesel.substring(4, 6));
  }

  private int extractGenderDigit(String pesel) {
    return pesel.toCharArray()[9];
  }
}
