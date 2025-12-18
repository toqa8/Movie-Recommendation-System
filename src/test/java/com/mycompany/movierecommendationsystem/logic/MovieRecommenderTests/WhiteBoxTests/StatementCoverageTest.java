package com.mycompany.movierecommendationsystem.logic.MovieRecommenderTests.WhiteBoxTests;

import com.mycompany.movierecommendationsystem.logic.MovieRecommender;
import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatementCoverageTest {
    private MovieRecommender recommender;
    private List<Movie> movies;
    private User user;
    List<Movie> result;

    @BeforeEach
    public void setUp() {
        recommender = new MovieRecommender();
    }

    @Test
    @DisplayName("T1: Going into the True branch of line 1.")
    void givenNullUser_whenRecommendMovies_thenThrowError() {
        // given
        movies = List.of(
                new Movie( "Movie A","MA156", List.of("Action")),
                new Movie( "Movie B","MB417", List.of("Action")),
                new Movie( "Movie C","MC648", List.of("Comedy"))
        );
        user = null;

        assertThrows(IllegalArgumentException.class, () -> {
            recommender.recommendMoviesForUser(user, movies);
        });
    }

    @Test
    @DisplayName("T2: Going into the False branch of line 1 and rest of code.")
    void givenTwoMoviesOfFavoriteGenre_whenRecommendMovies_thenReturnOtherMovie() {
        // given
        movies = List.of(
                new Movie( "Movie A","MA678", List.of("Action")),
                new Movie( "Movie B","MB249", List.of("Action"))
        );
        user = new User("Test jr","549QP30VA",List.of("MA678"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertEquals(1, result.size());
        assertTrue(result.contains(movies.getLast()));
    }
}
