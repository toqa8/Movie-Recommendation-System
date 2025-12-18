package com.mycompany.movierecommendationsystem.logic.MovieRecommenderTests.BlackBoxTests;

import com.mycompany.movierecommendationsystem.logic.MovieRecommender;
import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CauseEffectGraphTest {
    private MovieRecommender recommender;
    private List<Movie> movies;
    private User user;
    List<Movie> result;

    @BeforeEach
    public void setUp() {
        recommender = new MovieRecommender();
    }

    @Test
    @DisplayName("T1: Effect E1 - Movie added when all causes are met.")
    void givenAllCausesMet_whenRecommendMovies_thenAddMovieToList() {
        // Logic: (C3: Fav present) AND (C1: Genre match) AND (NOT C2: Different ID)
        // given
        movies = List.of(
                new Movie("Movie A", "MA345", List.of("Action")), // C3 (The anchor)
                new Movie("Movie B", "MB468", List.of("Action"))  // Target for E1
        );
        user = new User("User", "549QP30VA", List.of("MA345"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertTrue(result.contains(movies.get(1)), "E1: Movie B should be added");
    }

    @Test
    @DisplayName("T2: Effect Negated - Fail C3 (Favorite movie not present in list).")
    void givenFavoriteMovieMissing_whenRecommendMovies_thenNoEffect() {
        // Logic: NOT C3 (Cannot determine genre to match)
        // given
        movies = List.of(
                new Movie("Movie B", "MB468", List.of("Action"))
        );
        user = new User("User", "549QP30VA", List.of("MA345")); // ID1 is not in list

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertTrue(result.isEmpty(), "E1 should not happen because system doesn't know ID1's genre");
    }

    @Test
    @DisplayName("T3: Effect Negated - Fail C1 (Genre does not match).")
    void givenGenreMismatch_whenRecommendMovies_thenNoEffect() {
        // Logic: (C3) AND (NOT C1)
        // given
        movies = List.of(
                new Movie("Movie A", "MA345", List.of("Action")),
                new Movie("Movie B", "MB468", List.of("Comedy")) // Different genre
        );
        user = new User("User", "549QP30VA", List.of("MA345"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertTrue(result.isEmpty(), "E1 should not happen because genres do not match");
    }

    @Test
    @DisplayName("T4: Effect Negated - Fail NOT C2 (Movie ID is the same as favorite).")
    void givenSameMovieID_whenRecommendMovies_thenNoEffect() {
        // Logic: (C3) AND (C1) AND (C2: ID is the same)
        // Note: The logic requires NOT C2 for E1 to occur.
        // given
        movies = List.of(
                new Movie("Movie A", "MA345", List.of("Action"))
        );
        user = new User("User", "549QP30VA", List.of("MA345"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertFalse(result.contains(movies.getFirst()), "E1 should not happen because it's the same movie (C2 is true)");
    }
}
