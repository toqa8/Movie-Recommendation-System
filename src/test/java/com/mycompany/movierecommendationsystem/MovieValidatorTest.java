/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.movierecommendationsystem;

import java.util.Arrays;
import org.junit.Test; 
import static org.junit.Assert.*;

public class MovieValidatorTest {

   // Helper method 
    private Movie createMovie(String title, String id) {
        return new Movie(title, id, Arrays.asList("Action", "Drama"));
    }


    // =========================================================
    // SECTION 1: POSITIVE TESTING
    // =========================================================

    @Test
    public void testValidMovie_Standard() {
        Movie movie = createMovie("The Dark Knight", "TDK123");
        try {
            MovieValidator.validate(movie);
        } catch (ValidationException e) {
            fail("Should not throw exception, but got: " + e.getMessage());
        }
    }

    @Test
    public void testValidMovie_SingleWord() {
        Movie movie = createMovie("Matrix", "M456");
        try {
            MovieValidator.validate(movie);
        } catch (ValidationException e) {
            fail("Should not throw exception, but got: " + e.getMessage());
        }
    }

    @Test
    public void testValidMovie_LongTitle() {
        Movie movie = createMovie("Lord Of The Rings", "LOTR987");
        try {
            MovieValidator.validate(movie);
        } catch (ValidationException e) {
            fail("Should not throw exception, but got: " + e.getMessage());
        }
    }

    // =========================================================
    // SECTION 2: TITLE ERRORS
    // =========================================================

    @Test
    public void testInvalidTitle_FirstWordLowercase() {
        Movie movie = createMovie("the Dark Knight", "TDK123");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Title the Dark Knight is wrong", e.getMessage());
        }
    }

    @Test
    public void testInvalidTitle_MiddleWordLowercase() {
        Movie movie = createMovie("The dark Knight", "TDK123");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Title The dark Knight is wrong", e.getMessage());
        }
    }

    @Test
    public void testInvalidTitle_SingleWordAllLowercase() {
        Movie movie = createMovie("matrix", "M123");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Title matrix is wrong", e.getMessage());
        }
    }
 @Test
    public void testInvalidTitle_AllLowercase() {
        Movie movie = createMovie("home alone", "HA123");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Title home alone is wrong", e.getMessage());
        }
    }
    // =========================================================
    // SECTION 3: ID LETTERS ERRORS
    // =========================================================

    @Test
    public void testInvalidId_WrongLetters() {
        Movie movie = createMovie("The Dark Knight", "BAD123");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Id letters BAD123 are wrong", e.getMessage());
        }
    }

    @Test
    public void testInvalidId_MissingLetters() {
        Movie movie = createMovie("The Dark Knight", "TD123");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Id letters TD123 are wrong", e.getMessage());
        }
    }

    @Test
    public void testInvalidId_CaseSensitivity() {
        Movie movie = createMovie("The Dark Knight", "tdk123");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Id letters tdk123 are wrong", e.getMessage());
        }
    }

    // =========================================================
    // SECTION 4: ID NUMBERS ERRORS
    // =========================================================

    @Test
    public void testInvalidId_DuplicateNumbers_Format1() {
        Movie movie = createMovie("The Dark Knight", "TDK112");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Id numbers TDK112 aren't unique", e.getMessage());
        }
    }

    @Test
    public void testInvalidId_DuplicateNumbers_Format2() {
        Movie movie = createMovie("The Dark Knight", "TDK121");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Id numbers TDK121 aren't unique", e.getMessage());
        }
    }

    @Test
    public void testInvalidId_NotEnoughNumbers() {
        Movie movie = createMovie("The Dark Knight", "TDK12");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Id numbers TDK12 aren't unique", e.getMessage());
        }
    }

    @Test
    public void testInvalidId_TooManyNumbers() {
        Movie movie = createMovie("The Dark Knight", "TDK1234");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Id numbers TDK1234 aren't unique", e.getMessage());
        }
    }

    @Test
    public void testInvalidId_NonNumericSuffix() {
        Movie movie = createMovie("The Dark Knight", "TDK12X");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Id numbers TDK12X aren't unique", e.getMessage());
        }
    }

    // =========================================================
    // SECTION 5: PRIORITY & ORDER
    // =========================================================

    @Test
    public void testPriority_TitleFirst() {
        Movie movie = createMovie("the Dark Knight", "BAD123");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Title the Dark Knight is wrong", e.getMessage());
        }
    }

    @Test
    public void testPriority_IdLettersBeforeNumbers() {
        Movie movie = createMovie("The Dark Knight", "BAD112");

        try {
            MovieValidator.validate(movie);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ERROR: Movie Id letters BAD112 are wrong", e.getMessage());
        }
    }
}