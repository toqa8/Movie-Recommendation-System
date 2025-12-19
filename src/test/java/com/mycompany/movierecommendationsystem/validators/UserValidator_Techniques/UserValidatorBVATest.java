package com.mycompany.movierecommendationsystem.validators.UserValidator_Techniques;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import com.mycompany.movierecommendationsystem.validators.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("UserValidator - Black Box - Boundary Value Analysis (BVA)")
public class UserValidatorBVATest {

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

    static Stream<Arguments> bvaNameCases() {
        return Stream.of(

            Arguments.of(null, "ERROR: User Name null is wrong"),

            Arguments.of("", nameError("")),

            Arguments.of(" Ahmed", nameError(" Ahmed")),

            Arguments.of("A7med", nameError("A7med")),
            Arguments.of("A@hmed", nameError("A@hmed")),
            Arguments.of("Ahmed-Ali", nameError("Ahmed-Ali")),

            Arguments.of("A", null),
            Arguments.of("Ahmed Ali", null),

            Arguments.of("محمد علي", null)
        );
    }

    @ParameterizedTest(name = "[{index}] name={0}")
    @MethodSource("bvaNameCases")
    void validateUserName_BVA(String name, String expected) {
        assertStringOrNull(validator.validateUserName(name), expected);
    }

    static Stream<Arguments> bvaIdCases() {
        return Stream.of(

            Arguments.of(null, "ERROR: User Id null is wrong"),

            Arguments.of("1ABC2345", idError("1ABC2345")),
            Arguments.of("1A2345678", null),
            Arguments.of("1ABC234567", idError("1ABC234567")),

            Arguments.of("A1234567B", idError("A1234567B")),
            Arguments.of("1ABC23$56", idError("1ABC23$56")),
            Arguments.of("123456789", idError("123456789")),

            Arguments.of("1ABCDEFGH", null),

            Arguments.of(" 1ABC23456 ", null)
        );
    }

    @ParameterizedTest(name = "[{index}] userId={0}")
    @MethodSource("bvaIdCases")
    void validateUserId_BVA(String userId, String expected) {
        assertStringOrNull(validator.validateUserId(userId), expected);
    }
}
