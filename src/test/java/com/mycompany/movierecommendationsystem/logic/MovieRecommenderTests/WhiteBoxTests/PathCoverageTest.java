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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathCoverageTest {
    private MovieRecommender recommender;
    private List<Movie> movies;
    private User user;
    List<Movie> result;

    @BeforeEach
    public void setUp() {
        recommender = new MovieRecommender();
    }

    @Test
    @DisplayName("P1: Guard Rail Trigger: The input user or movies is null.")
    void testPath1_NullInput() {
        assertThrows(IllegalArgumentException.class, () ->
                recommender.recommendMoviesForUser(null, null));
    }

    @Test
    @DisplayName("P2: Empty Catalog: The input movie list is valid but empty, skipping both loops.")
    void testPath2_EmptyList() {
        // given
        user = new User("User", "549QP30VA", List.of("MA123"));
        // when
        result = recommender.recommendMoviesForUser(user, List.of());
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("P3: Zero Discovery: The loop executes, but no user favorites are found in the list (Block 7 is False).")
    void testPath3_ZeroDiscovery() {
        // given
        movies = List.of(new Movie("Movie A", "MA123", List.of("Action")));
        user = new User("User", "549QP30VA", List.of("MB173"));
        // when
        result = recommender.recommendMoviesForUser(user, movies);
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("P4: Exclusion Flow: A favorite is found, but it is filtered out in the second pass (Block 11 is True).")
    void testPath4_Exclusion() {
        // given
        movies = List.of(new Movie("Movie A", "MA123", List.of("Action")));
        user = new User("User", "549QP30VA", List.of("MA123"));
        // when
        result = recommender.recommendMoviesForUser(user, movies);
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("P5: Genre Mismatch: A favorite is found, but other movies in the list do not share its genre (Block 12 is False).")
    void testPath5_Mismatch() {
        // given
        movies = List.of(
                new Movie("Fav", "F648", List.of("Action")),
                new Movie("Other", "O396", List.of("Comedy"))
        );
        user = new User("User", "549QP30VA", List.of("F648"));
        // when
        result = recommender.recommendMoviesForUser(user, movies);
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("P6: Success Path: A favorite is identified, and a different movie with a matching genre is recommended.")
    void testPath6_RecommendationFound() {
        // given
        movies = List.of(
                new Movie("Fav", "F648", List.of("Action")),
                new Movie("Rec", "R309", List.of("Action"))
        );
        user = new User("User", "549QP30VA", List.of("F648"));
        // when
        result = recommender.recommendMoviesForUser(user, movies);
        // then
        assertEquals(1, result.size());
        assertEquals("R309", result.getFirst().getId());
    }
}
