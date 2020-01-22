package com.kul.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.when;

public class PeselServiceTest {

    @Mock
    private DateValidator dateValidator;
    @Mock
    private PeselValidator peselValidator;
    @InjectMocks
    private PeselService peselService;

    @BeforeEach
    public void begin() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNullPesel() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> peselService.extractPeselInfo(null));
    }

    @Test
    public void shouldCreatePeselInfoWithFalseForIncorrectPesel() {
        //given
        String incorrectPesel = "Britenetspz";
        PeselInfo incorrectPeselInfo = new PeselInfo(null, null, false, incorrectPesel);
        when(dateValidator.validateBirthDate(incorrectPesel)).thenReturn(false);
        //when
        PeselInfo result = peselService.extractPeselInfo(incorrectPesel);
        //then
        Assertions.assertEquals(incorrectPeselInfo.getGender(), result.getGender());
        Assertions.assertEquals(incorrectPeselInfo.getDateOfBirth(), result.getDateOfBirth());
        Assertions.assertEquals(incorrectPeselInfo.getIsValid(), result.getIsValid());
        Assertions.assertEquals(incorrectPeselInfo.getPesel(), result.getPesel());
    }

    static Collection<Object[]> correctStringsForPeselInfo() {
        return Arrays.asList(new Object[][]{
                {"05810153616", new PeselInfo(Gender.MALE, LocalDate.of(1805, 1, 1), true, "05810153616")},
                {"05810164405", new PeselInfo(Gender.FEMALE, LocalDate.of(1805, 1, 1), true, "05810164405")},
                {"05010110914", new PeselInfo(Gender.MALE, LocalDate.of(1905, 1, 1), true, "05010110914")},
                {"05010102500", new PeselInfo(Gender.FEMALE, LocalDate.of(1905, 1, 1), true, "05010102500")},
                {"05210113111", new PeselInfo(Gender.MALE, LocalDate.of(2005, 1, 1), true, "05210113111")},
                {"05210146007", new PeselInfo(Gender.FEMALE, LocalDate.of(2005, 1, 1), true, "05210146007")}
        });
    }

    @ParameterizedTest
    @MethodSource("correctStringsForPeselInfo")
    public void testForCorrectData(String pesel, PeselInfo peselInfo) {
        //given
        when(dateValidator.validateBirthDate(pesel)).thenReturn(true);
        when(peselValidator.validatePeselStructure(pesel)).thenReturn(true);
        //when
        PeselInfo result = peselService.extractPeselInfo(pesel);
        //then
        Assertions.assertEquals(peselInfo.getGender(), result.getGender());
        Assertions.assertEquals(peselInfo.getDateOfBirth(), result.getDateOfBirth());
        Assertions.assertEquals(peselInfo.getIsValid(), result.getIsValid());
        Assertions.assertEquals(peselInfo.getPesel(), result.getPesel());
    }
}
