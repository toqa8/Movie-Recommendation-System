package com.mycompany.movierecommendationsystem.validators.UserValidator_Techniques;

import static org.junit.jupiter.api.Assertions.*;

import com.mycompany.movierecommendationsystem.validators.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("UserValidator - Black Box - State Transition (Stateless demonstration)")
public class UserValidatorStateTransitionTest {

    private UserValidator validator;

    @BeforeEach
    void setUp() {
        validator = new UserValidator();
    }

    @Test
    @DisplayName("Calling validateUserId multiple times does not change behavior (stateless)")
    void validateUserId_isStateless() {
        String r1 = validator.validateUserId("123456789");
        assertEquals("ERROR: User Id 123456789 is wrong", r1);

        String r2 = validator.validateUserId("1A2345678");
        assertNull(r2);

        String r3 = validator.validateUserId("1ABC2345");
        assertEquals("ERROR: User Id 1ABC2345 is wrong", r3);
    }

    @Test
    @DisplayName("Calling validateUserName multiple times does not change behavior (stateless)")
    void validateUserName_isStateless() {
        String r1 = validator.validateUserName(" Ahmed");
        assertEquals("ERROR: User Name  Ahmed is wrong", r1);

        String r2 = validator.validateUserName("Ahmed Ali");
        assertNull(r2);

        String r3 = validator.validateUserName("A@hmed");
        assertEquals("ERROR: User Name A@hmed is wrong", r3);
    }
}
