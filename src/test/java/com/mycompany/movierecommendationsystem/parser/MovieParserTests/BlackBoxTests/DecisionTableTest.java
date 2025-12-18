package com.mycompany.movierecommendationsystem.parser.MovieParserTests.BlackBoxTests;

import com.mycompany.movierecommendationsystem.parser.MovieParser;
import com.mycompany.movierecommendationsystem.validators.MovieValidator;
import com.mycompany.movierecommendationsystem.validators.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DecisionTableTest {

    @BeforeEach
    void reset() {
        MovieValidator.reset();
    }
    @Test
    void rule1_onlyFileEmpty() {
       List<String> data = List.of();
        assertThrows(ValidationException.class,
                () -> MovieParser.parseMovies(data));
    }

    @Test
    void rule2_notEmpty_notEvenNumberOfLines() {
        List<String> data = List.of(
                "The Matrix,TM123",
                "Action",
                "Extra"
        );

        assertThrows(ValidationException.class,
                () -> MovieParser.parseMovies(data));
    }

    @Test
    void rule3_notEmpty_EvenNumberOfLines_invalidFormat() {
        List<String> data = List.of(
                "The Matrix TM123",
                "Action"
        );

        assertThrows(ValidationException.class,
                () -> MovieParser.parseMovies(data));
    }

    @Test
    void rule4_notEmpty_EvenNumberOfLines_validFormat_invalidTitle() {
        List<String> data = List.of(
                "the Matrix,TM123",
                "Action"
        );

        assertThrows(ValidationException.class,
                () -> MovieParser.parseMovies(data));
    }

    @Test
    void rule5_notEmpty_EvenNumberOfLines_validFormat_validTitle_invalidId() {
        List<String> data = List.of(
                "The Matrix,TM12",
                "Action"
        );

        assertThrows(ValidationException.class,
                () -> MovieParser.parseMovies(data));
    }

    @Test
    void rule6_allConditionsValid_shouldPass() throws ValidationException {
        List<String> data = List.of(
                "The Matrix,TM123",
                "Action"
        );

        assertEquals(1, MovieParser.parseMovies(data).size());
    }
}