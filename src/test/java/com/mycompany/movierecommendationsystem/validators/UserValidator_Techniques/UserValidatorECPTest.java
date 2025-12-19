package com.mycompany.movierecommendationsystem.validators.UserValidator_Techniques;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import com.mycompany.movierecommendationsystem.validators.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("UserValidator - Black Box - Equivalence Class Partitioning (ECP)")
public class UserValidatorECPTest {

    private UserValidator validator;

    @BeforeEach
    void setUp() {
        validator = new UserValidator();
    }

    private static String nameError(String name) {
        return "ERROR: User Name " + name + " is wrong";
    }

    private static String idError(String trimmedId) {
        return "ERROR: User Id " + trimmedId + " is wrong";
    }

    private static void assertStringOrNull(String actual, String expected) {
        if (expected == null) assertNull(actual);
        else assertEquals(expected, actual);
    }

    static Stream<Arguments> ecpNameCases() {
        return Stream.of(
            Arguments.of("TC#1 Null", null, "ERROR: User Name null is wrong"),
            Arguments.of("TC#2 Empty", "", nameError("")),
            Arguments.of("TC#3 Leading space", " Ahmed", nameError(" Ahmed")),
            Arguments.of("TC#4 Contains digit", "A7med", nameError("A7med")),
            Arguments.of("TC#5 Contains symbol", "A@hmed", nameError("A@hmed")),
            Arguments.of("TC#6 Contains hyphen", "Ahmed-Ali", nameError("Ahmed-Ali")),
            Arguments.of("TC#7 Valid single letter", "A", null),
            Arguments.of("TC#8 Valid letters + space", "Ahmed Ali", null),
            Arguments.of("TC#9 Valid Arabic letters", "محمد علي", null)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("ecpNameCases")
    void validateUserName_ECP(String tc, String name, String expected) {
        assertStringOrNull(validator.validateUserName(name), expected);
    }

    static Stream<Arguments> ecpIdCases() {
        return Stream.of(
            Arguments.of("TC#10 Null", null, "ERROR: User Id null is wrong"),
            Arguments.of("TC#11 Too short (8)", "1ABC2345", idError("1ABC2345")),
            Arguments.of("TC#12 Too long (10)", "1ABC234567", idError("1ABC234567")),
            Arguments.of("TC#13 Start not digit", "A1234567B", idError("A1234567B")),
            Arguments.of("TC#14 Contains symbol", "1ABC23$56", idError("1ABC23$56")),
            Arguments.of("TC#15 Digits only (no letters)", "123456789", idError("123456789")),
            Arguments.of("TC#16 Valid mixed", "1A2345678", null),
            Arguments.of("TC#17 Valid letters after digit", "1ABCDEFGH", null),
            Arguments.of("TC#18 Valid with trim", " 1ABC23456 ", null)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("ecpIdCases")
    void validateUserId_ECP(String tc, String userId, String expected) {
        assertStringOrNull(validator.validateUserId(userId), expected);
    }
}
