package com.mycompany.movierecommendationsystem.validators.UserValidator_Techniques;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import com.mycompany.movierecommendationsystem.validators.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("UserValidator - Black Box - Decision Table Testing")
public class UserValidatorDecisionTableTest {

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

    static Stream<Arguments> userIdDecisionRules() {
        return Stream.of(

            Arguments.of("R1: null -> A3", null, "ERROR: User Id null is wrong"),

            Arguments.of("R2: len!=9 (too short)", "1ABC2345", idError("1ABC2345")),
            Arguments.of("R2: len!=9 (too long)", "1ABC234567", idError("1ABC234567")),

            Arguments.of("R3: start not digit", "A1234567B", idError("A1234567B")),

            Arguments.of("R4: contains symbol", "1ABC23$56", idError("1ABC23$56")),

            Arguments.of("R5: digits only", "123456789", idError("123456789")),

            Arguments.of("R6: valid", "1A2345678", null)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("userIdDecisionRules")
    void validateUserId_DecisionTable(String rule, String userId, String expected) {
        assertStringOrNull(validator.validateUserId(userId), expected);
    }

    static Stream<Arguments> userNameDecisionRules() {
        return Stream.of(

            Arguments.of("R1: null -> A2", null, "ERROR: User Name null is wrong"),

            Arguments.of("R2: leading space -> A3", " Ahmed", nameError(" Ahmed")),

            Arguments.of("R3: empty string -> A3", "", nameError("")),

            Arguments.of("R4: invalid char (digit) -> A3", "A7med", nameError("A7med")),

            Arguments.of("R5: valid -> A1", "Ahmed Ali", null)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("userNameDecisionRules")
    void validateUserName_DecisionTable(String rule, String name, String expected) {
        assertStringOrNull(validator.validateUserName(name), expected);
    }
}
