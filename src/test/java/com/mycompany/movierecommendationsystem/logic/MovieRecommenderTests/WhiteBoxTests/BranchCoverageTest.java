package com.mycompany.movierecommendationsystem.logic.MovieRecommenderTests.WhiteBoxTests;

import com.mycompany.movierecommendationsystem.logic.MovieRecommender;
import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BranchCoverageTest {
    private MovieRecommender recommender;
    private List<Movie> movies;
    private User user;
    List<Movie> result;

    @BeforeEach
    public void setUp() {
        recommender = new MovieRecommender();
    }

    @Test
    @DisplayName("T1: True branch of guard rail.")
    void givenNullMovies_whenRecommendMovies_thenThrowError() {
        // given
        movies = null;
        user = new User("Test Sr.", "451ZK93TA", List.of("AC145"));

        // when / then
        assertThrows(IllegalArgumentException.class, () -> {
            recommender.recommendMoviesForUser(user, movies);
        });
    }

    @Test
    @DisplayName("T2: Shouldn't even enter the discovery loop.")
    void givenUserLikesNoMovies_whenRecommendMovies_thenReturnEmptyList() {
        // given
        movies = List.of();
        user = new User("The Mad Hater","811DF22MX",List.of());

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("T3: Pass through both branches of rest.")
    void givenMovies_whenRecommendMovies_thenReturnOtherMovieInFavoriteGenre() {
        // given
        movies = List.of(
                new Movie( "Movie A","MA156", List.of("Action")),
                new Movie( "Movie B","MB417", List.of("Action")),
                new Movie( "Movie C","MC648", List.of("Comedy"))
        );
        user = new User("Dupie McDuperson","220LK11CR",List.of("MB417"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertEquals(1, result.size());
        assertTrue(result.contains(movies.get(0)));
        assertFalse(result.contains(movies.get(1)));
        assertFalse(result.contains(movies.get(2)));
    }
}
