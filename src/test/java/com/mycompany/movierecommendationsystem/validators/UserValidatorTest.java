/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.movierecommendationsystem.validators;


/**
 *
 * @author hp
 */
//import org.junit.Test;
//import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {

    private final UserValidator validator = new UserValidator();


    // ---------- NAME TESTS ----------
    @Test
    public void testValidName() {
        assertNull(validator.validateUserName("Ahmed Ali"));
        assertNull(validator.validateUserName("Sarah"));
    }
    
    @Test
    public void testNameNull() {
        assertEquals("ERROR: User Name null is wrong", validator.validateUserName(null));
    }

    @Test
    public void testNameCannotStartWithSpace() {
        assertEquals("ERROR: User Name   Ahmed is wrong", validator.validateUserName("  Ahmed"));
    }

    @Test
    public void testNameCannotContainDigits() {
        assertEquals("ERROR: User Name A7med is wrong", validator.validateUserName("A7med"));
    }

    @Test
    public void testNameCannotContainSymbols() {
        assertEquals("ERROR: User Name A@hmed is wrong", validator.validateUserName("A@hmed"));
    }

    @Test
    public void testNameCannotBeEmpty() {
        assertEquals("ERROR: User Name  is wrong", validator.validateUserName(""));
        assertEquals("ERROR: User Name     is wrong", validator.validateUserName("   "));
    }


    // ---------- ID TESTS ----------
    @Test
    public void testValidUserId() {
        assertNull(validator.validateUserId("1A2345678"));
        assertNull(validator.validateUserId("9BC4567d1"));
    }
    
    @Test
    public void testIdNull() {
        assertEquals("ERROR: User Id null is wrong", validator.validateUserId(null));
    }

    @Test
    public void testUserIdMustBeLength9() {
        assertEquals("ERROR: User Id 123 is wrong", validator.validateUserId("123"));
    }

    @Test
    public void testUserIdMustStartWithDigit() {
        assertEquals("ERROR: User Id A12345678 is wrong", validator.validateUserId("A12345678"));
    }

    @Test
    public void testUserIdMustHaveAtLeastOneLetter() {
        assertEquals("ERROR: User Id 123456789 is wrong", validator.validateUserId("123456789"));
    }

    @Test
    public void testUserIdCannotContainSymbols() {
        assertEquals("ERROR: User Id 1A234$678 is wrong", validator.validateUserId("1A234$678"));
    }
}