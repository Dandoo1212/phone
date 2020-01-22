package com.kul.converter;

public class PeselFacade {

  private PeselService peselService;
  private PeselValidator peselValidator;
  private DateValidator dateValidator;

  public PeselFacade() {
    dateValidator = new DateValidator();
    peselValidator = new PeselValidator();
    peselService = new PeselService(dateValidator, peselValidator);
  }

  public PeselInfo extractInfo(String pesel) {
    return peselService.extractPeselInfo(pesel);
  }
}
