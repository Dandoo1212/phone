package com.kul.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PeselValidatorTest {

    PeselValidator validator;

    @BeforeEach
    public void begin() {
        validator = new PeselValidator();
    }

    @Test
    public void shouldReturnTrueForCorrectPesel() {
        //given
        String correctPesel = "00222913104";
        //when
        boolean result = validator.validatePeselStructure(correctPesel);
        //then
        assertTrue(result);
    }

    static Collection<String> incorrectParams() {
        return Arrays.asList(new String[]{
                "0022291310", "Britenetspz", "00222913103", ""
        });
    }

    @ParameterizedTest
    @MethodSource("incorrectParams")
    public void testForIncorrectData(String pesel) {
        //when
        boolean result = validator.validatePeselStructure(pesel);
        //then
        assertFalse(result);
    }
}
