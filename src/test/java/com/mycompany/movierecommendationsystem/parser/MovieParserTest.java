package com.mycompany.movierecommendationsystem.parser;

import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.validators.ValidationException;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MovieParserTest {

    @org.junit.Test
    public void parseMovies() throws ValidationException {
        List<String> lines = Arrays.asList(
                "The Matrix,TM123",
                "Action,Sci-Fi"
        );

        List<Movie> movies = MovieParser.parseMovies(lines);

        assertEquals(1, movies.size());
        Movie m = movies.get(0);

        assertEquals("The Matrix", m.getTitle());
        assertEquals("TM123", m.getId());
        assertEquals(Arrays.asList("Action", "Sci-Fi"), m.getGenres());
    }

    @org.junit.Test
    public void movieTitleError_lowercase() {

        List<String> lines = Arrays.asList(
                "the matrix,TM123",  // lowercase
                "Action,Sci-Fi"
        );

        com.mycompany.movierecommendationsystem.validators.ValidationException ex = assertThrows( com.mycompany.movierecommendationsystem.validators.ValidationException.class, () -> MovieParser.parseMovies(lines)
        );

        assertTrue(ex.getMessage().contains("ERROR: Movie Title"));
    }

    @org.junit.Test
    public void movieTitleError_InvalidCharacter() {

        List<String> lines = Arrays.asList(
                "the m@trix,TM123",
                "Action,Sci-Fi"
        );

        com.mycompany.movierecommendationsystem.validators.ValidationException ex = assertThrows( com.mycompany.movierecommendationsystem.validators.ValidationException.class, () -> MovieParser.parseMovies(lines)
        );

        assertTrue(ex.getMessage().contains("ERROR: Movie Title"));
    }


    @org.junit.Test
    public void movieIdLettersError() {

        List<String> lines = Arrays.asList(
                "The Matrix,XX123",  //  not TM
                "Action,Sci-Fi"
        );

        com.mycompany.movierecommendationsystem.validators.ValidationException ex = assertThrows(com.mycompany.movierecommendationsystem.validators.ValidationException.class, () -> MovieParser.parseMovies(lines)
        );

        assertTrue(ex.getMessage().contains("ERROR: Movie Id letters"));
    }

    @org.junit.Test
    public void movieIdNumbersError() {

        List<String> lines = Arrays.asList(
                "The Matrix,TM111",  // duplicate numbers
                "Action,Sci-Fi"
        );

        com.mycompany.movierecommendationsystem.validators.ValidationException ex = assertThrows(com.mycompany.movierecommendationsystem.validators.ValidationException.class, () -> MovieParser.parseMovies(lines)
        );

        assertTrue(ex.getMessage().contains("ERROR: Movie Id numbers"));
    }

    @Test
    public void testNullInput() {
        List<String> lines = null;
        ValidationException ex = assertThrows(ValidationException.class,
                () -> MovieParser.parseMovies(lines));

    }

    @Test
    public void testEmptyList() {
        List<String> lines = Arrays.asList();
        ValidationException ex = assertThrows(ValidationException.class,
                () -> MovieParser.parseMovies(lines));
    }

    @org.junit.Test
    public void oddNumberOfLinesError() {

        List<String> lines = Arrays.asList(
                "The Matrix,TM123" // missing 2nd line
        );

        com.mycompany.movierecommendationsystem.validators.ValidationException ex = assertThrows(
                com.mycompany.movierecommendationsystem.validators.ValidationException.class,
                () -> MovieParser.parseMovies(lines)
        );

        assertEquals("ERROR: movies.txt format is wrong", ex.getMessage());
    }

    @org.junit.Test
    public void wrongFormat_Line1_Error() {

        List<String> lines = Arrays.asList(
                "OnlyOnePartLine",  // missing comma
                "Action,Sci-Fi"
        );

        com.mycompany.movierecommendationsystem.validators.ValidationException ex = assertThrows(
                ValidationException.class,
                () -> MovieParser.parseMovies(lines)
        );

        assertEquals("ERROR: movies.txt format is wrong", ex.getMessage());
    }
}