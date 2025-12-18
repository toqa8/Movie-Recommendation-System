package com.mycompany.movierecommendationsystem.parser.MovieParserTests.BlackBoxTests;

import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.parser.MovieParser;
import com.mycompany.movierecommendationsystem.validators.MovieValidator;
import com.mycompany.movierecommendationsystem.validators.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EquivalencePartitioningTest {

    @BeforeEach
    void reset() {
        MovieValidator.reset();  // required because suffixes must be globally unique
    }

    //-------------Test Cases-------------------------//

    @Test
    void testNullFile() {
        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> MovieParser.parseMovies(null)
        );

        assertEquals("ERROR: movies.txt is empty or missing", ex.getMessage());
    }

    @Test
    void testEmptyFile() {
        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> MovieParser.parseMovies(List.of())
        );

        assertEquals("ERROR: movies.txt is empty or missing", ex.getMessage());
    }

    @Test
    void testOddNumberOfLines() {
        List<String> data = List.of(
                "The Matrix,TM123",
                "Action",
                "Extra"
        );

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> MovieParser.parseMovies(data)
        );

        assertEquals("ERROR: movies.txt format is wrong", ex.getMessage());
    }

    @Test
    void testMissingCommaInTitleId() {
        List<String> data = List.of(
                "The Matrix TM123",   // missing comma
                "Action"
        );

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> MovieParser.parseMovies(data)
        );

        assertEquals("ERROR: movies.txt format is wrong", ex.getMessage());
    }

    @Test
    void testInvalidMovieTitle() {
        List<String> data = List.of(
                "the Matrix,TM123",
                "Action"
        );

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> MovieParser.parseMovies(data)
        );

        assertEquals("ERROR: Movie Title the Matrix is wrong", ex.getMessage());
    }

    @Test
    void testIdLettersDoNotMatchTitle() {
        List<String> data = List.of(
                "The Matrix,TR123",   // capitals TM ≠ TR
                "Action"
        );

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> MovieParser.parseMovies(data)
        );

        assertEquals("ERROR: Movie Id letters TR123 are wrong", ex.getMessage());
    }

    @Test
    void testIdNumbersInvalidLength() {
        List<String> data = List.of(
                "The Matrix,TM12",   // only 2 digits
                "Action"
        );

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> MovieParser.parseMovies(data)
        );

        assertTrue(ex.getMessage().contains("Movie Id numbers"));
    }

    @Test
    void testDuplicateIdNumericSuffix() {
        List<String> data = List.of(
                "The Matrix,TM123",
                "Action",
                "Avatar,AV123",   // duplicate 123
                "SciFi"
        );

        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> MovieParser.parseMovies(data)
        );

        assertEquals("ERROR: Movie Id numbers AV123 aren't unique", ex.getMessage());
    }

    @Test
    void testValidSingleMovie_Success() throws ValidationException {
        List<String> data = List.of(
                "The Matrix,TM123",
                "Action,SciFi"
        );

        List<Movie> movies = MovieParser.parseMovies(data);

        assertEquals(1, movies.size());
        assertEquals("The Matrix", movies.get(0).getTitle());
        assertEquals("TM123", movies.get(0).getId());
        assertEquals(List.of("Action", "SciFi"), movies.get(0).getGenres());
    }

    @Test
    void testValidMultipleMovies_Success() throws ValidationException {
        List<String> data = List.of(
                "The Matrix,TM123",
                "Action",
                "Avatar,A456",
                "SciFi"
        );

        List<Movie> movies = MovieParser.parseMovies(data);

        assertEquals(2, movies.size());
        assertEquals("TM123", movies.get(0).getId());
        assertEquals("A456", movies.get(1).getId());
    }
}
