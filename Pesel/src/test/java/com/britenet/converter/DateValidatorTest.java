package com.britenet.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateValidatorTest {

    DateValidator validator;

    @BeforeEach
    public void begin() {
        validator = new DateValidator();
    }


    static Collection<String> correctParams() {
        return Arrays.asList(new String[]{
                "12212534211", "00222952013", "00222913104"
        });
    }

    @ParameterizedTest
    @MethodSource("correctParams")
    public void testForCorrectData(String pesel) {
        //when
        boolean result = validator.validateBirthDate(pesel);
        //then
        assertTrue(result);

    }

    static Collection<String> incorrectParams() {
        return Arrays.asList(new String[]{
                "01243190510", "25223081616", "01222968514"
        });
    }

    @ParameterizedTest
    @MethodSource("incorrectParams")
    public void testForIncorrectData(String pesel) {
        //when
        boolean result = validator.validateBirthDate(pesel);
        //then
        assertFalse(result);

    }


}
