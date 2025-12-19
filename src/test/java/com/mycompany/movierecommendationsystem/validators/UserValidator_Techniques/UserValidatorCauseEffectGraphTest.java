package com.mycompany.movierecommendationsystem.validators.UserValidator_Techniques;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import com.mycompany.movierecommendationsystem.validators.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("UserValidator - Black Box - Cause-Effect Graph Testing")
public class UserValidatorCauseEffectGraphTest {

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

    static Stream<Arguments> nameCauseEffectCases() {
        return Stream.of(
            Arguments.of("C1 -> E2 (null)", null, "ERROR: User Name null is wrong"),
            Arguments.of("C2 -> E3 (leading space)", " Ahmed", nameError(" Ahmed")),
            Arguments.of("C3 -> E3 (empty after trim)", "", nameError("")),
            Arguments.of("C4 -> E3 (bad char digit)", "A7med", nameError("A7med")),
            Arguments.of("No causes -> E1 (valid)", "Ahmed Ali", null)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("nameCauseEffectCases")
    void validateUserName_CauseEffect(String label, String name, String expected) {
        assertStringOrNull(validator.validateUserName(name), expected);
    }

    static Stream<Arguments> userIdCauseEffectCases() {
        return Stream.of(
            Arguments.of("¬U1 -> F2 (null)", null, "ERROR: User Id null is wrong"),
            Arguments.of("U1 & ¬U2 -> F3 (len != 9)", "1ABC2345", idError("1ABC2345")),
            Arguments.of("U1 & U2 & ¬U3 -> F3 (start not digit)", "A1234567B", idError("A1234567B")),
            Arguments.of("U1 & U2 & U3 & ¬U4 -> F3 (symbol)", "1ABC23$56", idError("1ABC23$56")),
            Arguments.of("U1 & U2 & U3 & U4 & ¬U5 -> F3 (digits only)", "123456789", idError("123456789")),
            Arguments.of("U1..U5 -> F1 (valid)", "1A2345678", null)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("userIdCauseEffectCases")
    void validateUserId_CauseEffect(String label, String userId, String expected) {
        assertStringOrNull(validator.validateUserId(userId), expected);
    }
}
