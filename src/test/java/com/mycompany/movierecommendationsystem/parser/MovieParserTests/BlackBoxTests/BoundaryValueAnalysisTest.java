package com.mycompany.movierecommendationsystem.parser.MovieParserTests.BlackBoxTests;

import com.mycompany.movierecommendationsystem.parser.MovieParser;
import com.mycompany.movierecommendationsystem.validators.MovieValidator;
import com.mycompany.movierecommendationsystem.validators.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoundaryValueAnalysisTest {

    @BeforeEach
    void reset() {
        MovieValidator.reset();
    }

    //-------------Test Cases-------------------------//
    // ----- ID numeric suffix (exactly 3 digits) -----
    @Test
    void idLength_TwoDigits() {
        List<String> data = List.of(
                "The Matrix,TM12",
                "Action"
        );

        assertThrows(ValidationException.class,
                () -> MovieParser.parseMovies(data));
    }

    @Test
    void idLength_atBoundary_ThreeDigits() throws ValidationException {
        List<String> data = List.of(
                "The Matrix,TM123",
                "Action"
        );

        assertEquals(1, MovieParser.parseMovies(data).size());
    }

    @Test
    void idLength_FourDigits() {
        List<String> data = List.of(
                "The Matrix,TM1234",
                "Action"
        );

        assertThrows(ValidationException.class,
                () -> MovieParser.parseMovies(data));
    }

//-------------- Boundary: number of lines -------------
    @Test
    void lines_oneLine() {
        List<String> data = List.of("The Matrix,TM123");

        assertThrows(ValidationException.class,
                () -> MovieParser.parseMovies(data));
    }

    @Test
    void lines_atBoundary_twoLines() throws ValidationException {
        List<String> data = List.of(
                "The Matrix,TM123",
                "Action"
        );

        assertEquals(1, MovieParser.parseMovies(data).size());
    }

    @Test
    void lines_threeLine() {
        List<String> data = List.of(
                "The Matrix,TM123",
                "Action",
                "Extra"
        );

        assertThrows(ValidationException.class,
                () -> MovieParser.parseMovies(data));
    }


}
