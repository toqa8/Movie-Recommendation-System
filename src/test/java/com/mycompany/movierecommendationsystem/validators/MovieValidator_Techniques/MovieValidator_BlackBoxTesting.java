package testMovieValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BlackBoxTests {

    @BeforeEach
    void setUp() { MovieValidator.reset(); }

    
    private Movie createMovie(String title, String id) {
        return new Movie(title, id, Arrays.asList("Action", "Drama","Horror"));
    }

    @Test
    @DisplayName("EP: Valid Standard Input")
    void testEP_Valid_Standard() {
        assertDoesNotThrow(() -> MovieValidator.validate(createMovie("The Dark Knight", "TDK123")));
    }

    @Test
    @DisplayName("EP: Valid Single Word Title")
    void testEP_Valid_SingleWord() {
        assertDoesNotThrow(() -> MovieValidator.validate(createMovie("Matrix", "M456")));
    }

    @Test
    @DisplayName("EP: Invalid Title (Lowercase)")
    void testEP_Invalid_TitleLowercase() {
        ValidationException e = assertThrows(ValidationException.class, 
            () -> MovieValidator.validate(createMovie("the Dark Knight", "TDK123")));
        assertEquals("ERROR: Movie Title the Dark Knight is wrong", e.getMessage());
    }

    @Test
    @DisplayName("EP: Invalid Prefix Mismatch")
    void testEP_Invalid_PrefixMismatch() {
        ValidationException e = assertThrows(ValidationException.class, 
            () -> MovieValidator.validate(createMovie("The Dark Knight", "BAD123")));
        assertEquals("ERROR: Movie Id letters BAD123 are wrong", e.getMessage());
    }

    @Test
    @DisplayName("EP: Invalid Non-Numeric Suffix")
    void testEP_Invalid_NonNumericSuffix() {
        ValidationException e = assertThrows(ValidationException.class, 
            () -> MovieValidator.validate(createMovie("The Dark Knight", "TDK12X")));
        assertTrue(e.getMessage().contains("aren't unique"));
    }

   

    @Test
    @DisplayName("BVA: Length = 2 (Fail)")
    void testBVA_Length_2() {
        ValidationException e = assertThrows(ValidationException.class, 
            () -> MovieValidator.validate(createMovie("Inception", "I99")));
        assertTrue(e.getMessage().contains("aren't unique"));
    }

    @Test
    @DisplayName("BVA: Length = 3 (Pass)")
    void testBVA_Length_3() {
        assertDoesNotThrow(() -> MovieValidator.validate(createMovie("Inception", "I100")));
    }

    @Test
    @DisplayName("BVA: Length = 4 (Fail)")
    void testBVA_Length_4() {
        ValidationException e = assertThrows(ValidationException.class, 
            () -> MovieValidator.validate(createMovie("Inception", "I1000")));
        assertTrue(e.getMessage().contains("aren't unique"));
    }



    @Test
    @DisplayName("State: New Number (Pass)")
    void testState_NewNumber() {
        assertDoesNotThrow(() -> MovieValidator.validate(createMovie("Movie A", "MA100")));
    }

    @Test
    @DisplayName("State: Duplicate Number (Fail)")
    void testState_DuplicateNumber() throws ValidationException {
        // 1. Set State
        MovieValidator.validate(createMovie("Movie A", "MA100"));
        
        // 2. Test Transition
        ValidationException e = assertThrows(ValidationException.class, 
            () -> MovieValidator.validate(createMovie("Movie B", "MB100")));
        assertEquals("ERROR: Movie Id numbers MB100 aren't unique", e.getMessage());
    }
    
    @Test
    @DisplayName("State: Reset Clears Memory (Pass)")
    void testState_ResetClearsMemory() throws ValidationException {
        MovieValidator.validate(createMovie("Movie A", "MA100"));
        MovieValidator.reset();
        assertDoesNotThrow(() -> MovieValidator.validate(createMovie("Movie B", "MB100")));
    }
}
