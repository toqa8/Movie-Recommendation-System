/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.movierecommendationsystem.validators;


import com.mycompany.movierecommendationsystem.models.Movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 
import java.util.Arrays;

class MovieValidatorTest {

    // Helper method
    private Movie createMovie(String title, String id) {
        return new Movie(title, id, Arrays.asList("Action", "Drama"));
    }

    @BeforeEach
    void setUp() {
        MovieValidator.reset();
    }

    // =========================================================
    // SECTION 1: POSITIVE TESTING 
    // =========================================================

    @Test
    void testValidMovie_Standard() {
        Movie movie = createMovie("The Dark Knight", "TDK123");
      
        assertDoesNotThrow(() -> MovieValidator.validate(movie));
    }

    @Test
    void testValidMovie_SingleWord() {
        Movie movie = createMovie("Matrix", "M456");
        assertDoesNotThrow(() -> MovieValidator.validate(movie));
    }

    @Test
    void testValidMovie_LongTitle() {
        Movie movie = createMovie("Lord Of The Rings", "LOTR987");
        assertDoesNotThrow(() -> MovieValidator.validate(movie));
    }

    // =========================================================
    // SECTION 2: GLOBAL UNIQUENESS 
    // =========================================================

    @Test
    void testGlobalUniqueness_DuplicateIdNumber() {
       
        Movie m1 = createMovie("The Dark Knight", "TDK123");
        assertDoesNotThrow(() -> MovieValidator.validate(m1));

      
        Movie m2 = createMovie("The Dark Knight Rises", "TDKR123");
        
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            MovieValidator.validate(m2);
        });
        
        assertEquals("ERROR: Movie Id numbers TDKR123 aren't unique", exception.getMessage());
    }

    // =========================================================
    // SECTION 3: TITLE ERRORS
    // =========================================================

    @Test
    void testInvalidTitle_FirstWordLowercase() {
        Movie movie = createMovie("the Dark Knight", "TDK123");
        
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            MovieValidator.validate(movie);
        });
        assertEquals("ERROR: Movie Title the Dark Knight is wrong", exception.getMessage());
    }

    @Test
    void testInvalidTitle_MiddleWordLowercase() {
        Movie movie = createMovie("The dark Knight", "TDK123");
        
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            MovieValidator.validate(movie);
        });
        assertEquals("ERROR: Movie Title The dark Knight is wrong", exception.getMessage());
    }

    @Test
    void testInvalidTitle_SingleWordAllLowercase() {
        Movie movie = createMovie("matrix", "M123");

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            MovieValidator.validate(movie);
        });
        assertEquals("ERROR: Movie Title matrix is wrong", exception.getMessage());
    }

    @Test
    void testInvalidTitle_AllLowercase() {
        Movie movie = createMovie("home alone", "HA123");

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            MovieValidator.validate(movie);
        });
        assertEquals("ERROR: Movie Title home alone is wrong", exception.getMessage());
    }

    // =========================================================
    // SECTION 4: ID LETTERS ERRORS
    // =========================================================

    @Test
    void testInvalidId_WrongLetters() {
        Movie movie = createMovie("The Dark Knight", "BAD123");

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            MovieValidator.validate(movie);
        });
        assertEquals("ERROR: Movie Id letters BAD123 are wrong", exception.getMessage());
    }

    @Test
    void testInvalidId_MissingLetters() {
        Movie movie = createMovie("The Dark Knight", "TD123");

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            MovieValidator.validate(movie);
        });
        assertEquals("ERROR: Movie Id letters TD123 are wrong", exception.getMessage());
    }

    @Test
    void testInvalidId_CaseSensitivity() {
        Movie movie = createMovie("The Dark Knight", "tdk123");

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            MovieValidator.validate(movie);
        });
        assertEquals("ERROR: Movie Id letters tdk123 are wrong", exception.getMessage());
    }

    // =========================================================
    // SECTION 5: ID NUMBERS ERRORS (Format & Length)
    // =========================================================

    @Test
    void testInvalidId_NotEnoughNumbers() {
        Movie movie = createMovie("The Dark Knight", "TDK12");

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            MovieValidator.validate(movie);
        });
        assertEquals("ERROR: Movie Id numbers TDK12 aren't unique", exception.getMessage());
    }

    @Test
    void testInvalidId_TooManyNumbers() {
        Movie movie = createMovie("The Dark Knight", "TDK1234");

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            MovieValidator.validate(movie);
        });
        assertEquals("ERROR: Movie Id numbers TDK1234 aren't unique", exception.getMessage());
    }

    @Test
    void testInvalidId_NonNumericSuffix() {
        Movie movie = createMovie("The Dark Knight", "TDK12X");

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            MovieValidator.validate(movie);
        });
        assertEquals("ERROR: Movie Id numbers TDK12X aren't unique", exception.getMessage());
    }

    // =========================================================
    // SECTION 6: PRIORITY & ORDER
    // =========================================================

    @Test
    void testPriority_TitleFirst() {
        Movie movie = createMovie("the Dark Knight", "BAD123");

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            MovieValidator.validate(movie);
        });
        assertEquals("ERROR: Movie Title the Dark Knight is wrong", exception.getMessage());
    }

    @Test
    void testPriority_IdLettersBeforeNumbers() {
        Movie movie = createMovie("The Dark Knight", "BAD112");

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            MovieValidator.validate(movie);
        });
        assertEquals("ERROR: Movie Id letters BAD112 are wrong", exception.getMessage());
    }
}