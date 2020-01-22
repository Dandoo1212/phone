package com.kul.service;

public class PhoneNumber {

  private PhoneNumberType typeOfPhoneNumber;
  private String formattedNumber;
  private boolean isPhoneNumberValid;

   private PhoneNumber(
      PhoneNumberType typeOfPhoneNumber, String formattedNumber, boolean isPhoneNumberValid) {
    this.typeOfPhoneNumber = typeOfPhoneNumber;
    this.formattedNumber = formattedNumber;
    this.isPhoneNumberValid = isPhoneNumberValid;
  }

  public static final PhoneNumber createCorrectPhoneNumber(PhoneNumberType typeOfPhoneNumber, String number){
     return new PhoneNumber(typeOfPhoneNumber,typeOfPhoneNumber.format(number),true);
  }

  public static final PhoneNumber createIncorrectPhoneNumber(){
     return new PhoneNumber(null,null,false);
  }

  public PhoneNumberType getTypeOfPhoneNumber() {
    return typeOfPhoneNumber;
  }

  public String getFormattedNumber() {
    return formattedNumber;
  }

  public boolean isPhoneNumberValid() {
    return isPhoneNumberValid;
  }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "typeOfPhoneNumber=" + typeOfPhoneNumber +
                ", formattedNumber='" + formattedNumber + '\'' +
                ", isPhoneNumberValid=" + isPhoneNumberValid +
                '}';
    }
}
