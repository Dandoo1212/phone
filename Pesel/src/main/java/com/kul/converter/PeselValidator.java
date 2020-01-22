package com.kul.converter;

import static java.lang.Character.getNumericValue;

class PeselValidator {

  boolean validatePeselStructure(String pesel) {
    return isPeselElevenDigitsLong(pesel) && validateChecksum(pesel);
  }

  private boolean isPeselElevenDigitsLong(String pesel) {
    return pesel.length() == 11 && pesel.matches("\\d+");
  }

  private boolean validateChecksum(String pesel) {
    int[] digitsOfPesel = extractDigitsOfPesel(pesel);
    return (10 - calculateChecksum(digitsOfPesel) % 10) == digitsOfPesel[10];
  }

  private int calculateChecksum(int[] digitsOfPesel) {
    return (digitsOfPesel[0] * 1)
        + (digitsOfPesel[1] * 3)
        + (digitsOfPesel[2] * 7)
        + (digitsOfPesel[3] * 9)
        + (digitsOfPesel[4] * 1)
        + (digitsOfPesel[5] * 3)
        + (digitsOfPesel[6] * 7)
        + (digitsOfPesel[7] * 9)
        + (digitsOfPesel[8] * 1)
        + (digitsOfPesel[9] * 3);
  }

  private int[] extractDigitsOfPesel(String pesel) {
    int[] digits = new int[11];
    for (int i = 0; i <= 10; i++) {
      digits[i] = getNumericValue(pesel.toCharArray()[i]);
    }
    return digits;
  }
}
