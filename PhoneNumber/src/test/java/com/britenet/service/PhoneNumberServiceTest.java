package com.britenet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

import static com.britenet.service.PhoneNumber.createCorrectPhoneNumber;
import static com.britenet.service.PhoneNumber.createIncorrectPhoneNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PhoneNumberServiceTest {

    @Mock
    private PhoneNumberFormatter phoneNumberFormatter;
    @InjectMocks
    private PhoneNumberService phoneNumberService;

    @BeforeEach
    private void init() {
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest
    @MethodSource("incorrectPhoneNumbers")
    public void shouldReturnInvalidPhoneNumberForIncorrectData(String phoneNumber) {
        //given
        PhoneNumber expected = createIncorrectPhoneNumber();
        //when
        PhoneNumber actual = phoneNumberService.recognizePhoneNumber(phoneNumber);
        //then
        assertEquals(expected.getTypeOfPhoneNumber(), actual.getTypeOfPhoneNumber());
        assertEquals(expected.getFormattedNumber(), actual.getTypeOfPhoneNumber());
        assertEquals(expected.isPhoneNumberValid(), actual.isPhoneNumberValid());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNullPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> phoneNumberService.recognizePhoneNumber(null));
    }

    @Test
    public void shouldReturnCorrectPhoneNumberFormatForCorrectNumber() {
        //given
        String phoneNumber = "815655656";
        PhoneNumber expected = createCorrectPhoneNumber(PhoneNumberType.LANDLINE,phoneNumber);
        when(phoneNumberFormatter.formatPhoneNumber(phoneNumber)).thenReturn(expected);
        //when
        PhoneNumber actual = phoneNumberService.recognizePhoneNumber(phoneNumber);
        //then
        assertEquals(expected.getTypeOfPhoneNumber(), actual.getTypeOfPhoneNumber());
        assertEquals(expected.getFormattedNumber(), actual.getFormattedNumber());
        assertEquals(expected.isPhoneNumberValid(), actual.isPhoneNumberValid());

    }

    static Stream<String> incorrectPhoneNumbers() {
        return Stream.of("", "1", "asdasdasd", "1231231231", "12345678w", "1234567 a", "1234567 9");
    }


}
