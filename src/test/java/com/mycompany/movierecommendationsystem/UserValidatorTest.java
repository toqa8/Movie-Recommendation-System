/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.movierecommendationsystem;


/**
 *
 * @author hp
 */
//import org.junit.Test;
//import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {

    private UserValidator validator = new UserValidator();


    // ---------- NAME TESTS ----------
    @Test
    public void testValidName() {
        assertNull(validator.validateUserName("Ahmed Ali"));
        assertNull(validator.validateUserName("Sarah"));
    }
    
    @Test
    public void testNameNull() {
        assertEquals("Invalid user name", validator.validateUserName(null));
    }

    @Test
    public void testNameCannotStartWithSpace() {
        assertEquals("Invalid user name", validator.validateUserName("  Ahmed"));
    }

    @Test
    public void testNameCannotContainDigits() {
        assertEquals("Invalid user name", validator.validateUserName("A7med"));
    }

    @Test
    public void testNameCannotContainSymbols() {
        assertEquals("Invalid user name", validator.validateUserName("A@hmed"));
    }

    @Test
    public void testNameCannotBeEmpty() {
        assertEquals("Invalid user name", validator.validateUserName(""));
        assertEquals("Invalid user name", validator.validateUserName("   "));
    }


    // ---------- ID TESTS ----------
    @Test
    public void testValidUserId() {
        assertNull(validator.validateUserId("1A2345678"));
        assertNull(validator.validateUserId("9BC4567d1"));
    }
    
    @Test
    public void testIdNull() {
        assertEquals("Invalid user ID", validator.validateUserId(null));
    }

    @Test
    public void testUserIdMustBeLength9() {
        assertEquals("Invalid user ID", validator.validateUserId("123"));
    }

    @Test
    public void testUserIdMustStartWithDigit() {
        assertEquals("Invalid user ID", validator.validateUserId("A12345678"));
    }

    @Test
    public void testUserIdMustHaveAtLeastOneLetter() {
        assertEquals("Invalid user ID", validator.validateUserId("123456789"));
    }

    @Test
    public void testUserIdCannotContainSymbols() {
        assertEquals("Invalid user ID", validator.validateUserId("1A234$678"));
    }


    // ---------- DUPLICATE TEST ----------
    @Test
    public void testUserIdUniqueness() {
        Set<String> ids = new HashSet<>();
        ids.add("1A2345678");

        assertEquals("Duplicate user ID",
            validator.validateUserIdUniqueness("1A2345678", ids));

        assertNull(
            validator.validateUserIdUniqueness("2B3456789", ids)
        );
    }
}