package com.britenet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

import static com.britenet.service.PhoneNumber.*;
import static com.britenet.service.PhoneNumberType.LANDLINE;
import static com.britenet.service.PhoneNumberType.MOBILE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PhoneNumberFormatterTest {

  @Mock PhoneNumberTypeRecognizer phoneNumberTypeRecognizer;
  @InjectMocks PhoneNumberFormatter phoneNumberFormatter;

  @BeforeEach
  private void init() {
    MockitoAnnotations.initMocks(this);
  }

  @ParameterizedTest
  @MethodSource("correctMobilePhoneNumbers")
  void shouldReturnPhoneNumberWithFormattingForMobilePhones(
      String phoneNumber, PhoneNumber expected) {
    // given
    when(phoneNumberTypeRecognizer.recognize(phoneNumber)).thenReturn(MOBILE);
    // when
    PhoneNumber actual = phoneNumberFormatter.formatPhoneNumber(phoneNumber);

    // then
    assertEquals(expected.getTypeOfPhoneNumber(), actual.getTypeOfPhoneNumber());
    assertEquals(expected.getFormattedNumber(), actual.getFormattedNumber());
    assertEquals(expected.isPhoneNumberValid(), actual.isPhoneNumberValid());
  }

  @ParameterizedTest
  @MethodSource("correctLandLinePhoneNumbers")
  void shouldReturnPhoneNumberWithFormattingForLandLinePhones(
      String phoneNumber, PhoneNumber expected) {
    when(phoneNumberTypeRecognizer.recognize(phoneNumber)).thenReturn(LANDLINE);
    // when
    PhoneNumber actual = phoneNumberFormatter.formatPhoneNumber(phoneNumber);
    // then
    assertEquals(expected.getTypeOfPhoneNumber(), actual.getTypeOfPhoneNumber());
    assertEquals(expected.getFormattedNumber(), actual.getFormattedNumber());
    assertEquals(expected.isPhoneNumberValid(), actual.isPhoneNumberValid());
  }

  static Stream<Arguments> correctLandLinePhoneNumbers() {
    return Stream.of(
        Arguments.of("817897899", createCorrectPhoneNumber(LANDLINE, "817897899")),
        Arguments.of("824566544", createCorrectPhoneNumber(LANDLINE, "824566544")));
  }

  static Stream<Arguments> correctMobilePhoneNumbers() {
    return Stream.of(
        Arguments.of("665456123", createCorrectPhoneNumber(MOBILE, "665456123")),
        Arguments.of("698456456", createCorrectPhoneNumber(MOBILE, "698456456")));
  }
}
