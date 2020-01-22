package com.kul.converter;

import java.time.LocalDate;

public class PeselInfo {

  private Gender gender;
  private LocalDate dateOfBirth;
  private boolean isValid;
  private String pesel;

  public PeselInfo(Gender gender, LocalDate dateOfBirth, boolean isValid, String pesel) {
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.isValid = isValid;
    this.pesel = pesel;
  }

  public Gender getGender() {
    return gender;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public boolean getIsValid() {
    return isValid;
  }

  public String getPesel() {
    return pesel;
  }

  @Override
  public String toString() {
    return "PeselInfo{" +
            "gender=" + gender +
            ", dateOfBirth=" + dateOfBirth +
            ", isValid=" + isValid +
            ", pesel='" + pesel + '\'' +
            '}';
  }
}
