package com.britenet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneNumberTypeRecognizerTest {

    private PhoneNumberTypeRecognizer phoneNumberTypeRecognizer;

    @BeforeEach
    private void init() {
        phoneNumberTypeRecognizer = new PhoneNumberTypeRecognizer();
    }

    private static Stream<Arguments> correctPhoneNumbers() {
        return Stream.of(
                Arguments.of(PhoneNumberType.MOBILE, "665999999"),
                Arguments.of(PhoneNumberType.LANDLINE, "815465469"),
                Arguments.of(PhoneNumberType.LANDLINE, "655896547"),
                Arguments.of(PhoneNumberType.SPECIAL, "700848848")
        );
    }

    @ParameterizedTest
    @MethodSource("correctPhoneNumbers")
    void shouldReturnCorrectPhoneNumberTypesForCorrectData(PhoneNumberType type, String phoneNumber) {
        //when
        PhoneNumberType result = phoneNumberTypeRecognizer.recognize(phoneNumber);
        //then
        assertEquals(type, result);
    }
}
