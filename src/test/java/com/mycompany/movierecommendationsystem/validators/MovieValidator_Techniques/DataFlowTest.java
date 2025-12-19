package com.mycompany.movierecommendationsystem.validators.MovieValidator_Techniques;
import com.mycompany.movierecommendationsystem.validators.ValidationException;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.validators.MovieValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataFlowTest {

    @BeforeEach
    void setUp() {
        MovieValidator.reset();
        
    }
    
    private Movie createMovie(String title, String id) {
        return new Movie(title, id, Arrays.asList("Action", "Drama","Horror"));
        
       
    }
    
   
    @Test
    @DisplayName("MovieValidatorAllDefsCoverage - Valid Movie")
    void testValidMovie() {
        
        assertDoesNotThrow(() -> MovieValidator.validate(createMovie("The Matrix", "TM001")));
    }

    
    @Test
    @DisplayName("MovieValidatorAllUsesTest - Invalid Title (p-use)")
    void testInvalidTitleLowercaseWord() {
        

     
         assertThrows(ValidationException.class,() -> MovieValidator.validate(createMovie("the Matrix", "TM002")));

       
    }

    @Test
    @DisplayName("MovieValidatorAllUsesTest - Wrong ID Letters (p-use → false branch)")
    void testWrongIdLetters() {
        
                assertThrows(ValidationException.class,() -> MovieValidator.validate(createMovie("The Matrix", "TX001")));

    }

 
    @Test
    @DisplayName("MovieValidatorAllDUPathsTest - ID Number Wrong Length")
    void testIdNumberWrongLength() {
       
     
      assertThrows(ValidationException.class,() -> MovieValidator.validate(createMovie("The Matrix", "TM01")));
       
    }

    @Test
    @DisplayName("MovieValidatorAllDUPathsTest - ID Number Not Numeric")
    void testIdNumberNotNumeric() {
   
  
        assertThrows(ValidationException.class, () -> MovieValidator.validate(createMovie("The Matrix", "TM0A1")));

    }

    @Test
    @DisplayName("MovieValidatorAllDUPathsTest - Duplicate ID Number")
    void testDuplicateIdNumber() {
      
        assertDoesNotThrow(() -> MovieValidator.validate(createMovie("The Matrix", "TM005")));
  
        assertThrows(ValidationException.class,() -> MovieValidator.validate(createMovie("The Matrix", "TM005")));

       
    }
}
