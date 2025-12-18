package com.mycompany.movierecommendationsystem.logic.MovieRecommenderTests.WhiteBoxTests;

import com.mycompany.movierecommendationsystem.logic.MovieRecommender;
import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConditionCoverageTest {
    private static MovieRecommender recommender;
    private static List<Movie> movies;
    private static User user;
    private static List<Movie> result;

    @BeforeEach
    void setUp() {
        recommender = new MovieRecommender();
    }

    @Nested
    @DisplayName("Block 1: Input Guard Rail Tests")
    class GuardRailTests {

        @Test
        @DisplayName("TC-C1: user=null, movies=valid (Short-circuit A)")
        public void givenNullUser_whenRecommendMovies_thenThrowException() {
            assertThrows(IllegalArgumentException.class, () ->
                    recommender.recommendMoviesForUser(null, List.of())
            );
        }

        @Test
        @DisplayName("TC-C2: user=valid, movies=null (Full Evaluation B)")
        public void givenNullMovies_whenRecommendMovies_thenThrowException() {
            // given
            user = new User("Test", "220LK11CR", List.of());
            // when / then
            assertThrows(IllegalArgumentException.class, () ->
                    recommender.recommendMoviesForUser(user, null)
            );
        }

        @Test
        @DisplayName("TC-C4: user=null, movies=null (Double Null Short-circuit)")
        public void givenBothNull_whenRecommendMovies_thenThrowException() {
            assertThrows(IllegalArgumentException.class, () ->
                    recommender.recommendMoviesForUser(null, null)
            );
        }

        @Test
        @DisplayName("TC-C3: user=valid, movies=valid (Proceed to Pass 1)")
        public void givenValidInputs_whenRecommendMovies_thenProceedNormally() {
            // given
            user = new User("Test", "220LK11CR", List.of());
            // when / then
            assertDoesNotThrow(() -> recommender.recommendMoviesForUser(user, List.of()));
        }
    }

    @Nested
    @DisplayName("Block 7-13: Recommendation Logic Tests")
    class LogicTests {

        @Test
        @DisplayName("TC-C5 & TC-C6: Discovery Logic (Pass 1)")
        public void givenKnownAndUnknownIds_whenRecommendMovies_thenIdentifyCorrectFavorites() {
            // given
            movies = List.of(new Movie("Movie A", "MA194", List.of("Action")));
            user = new User("User", "220LK11CR", List.of("A1", "Z9"));

            // when
            result = recommender.recommendMoviesForUser(user, movies);
            // then
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("TC-C7 & TC-C8: Exclusion Logic (Pass 2)")
        public void givenCatalogWithFavorites_whenRecommendMovies_thenExcludeLikedMovies() {
            // given
            movies = List.of(
                    new Movie("Fav", "F045", List.of("Action")),
                    new Movie("Rec", "R648", List.of("Action"))
            );
            user = new User("User", "220LK11CR", List.of("F045"));

            // when
            result = recommender.recommendMoviesForUser(user, movies);
            // then
            assertEquals(1, result.size());
            assertEquals("R648", result.getFirst().getId());
        }

        @Test
        @DisplayName("TC-C9: Recommendation Logic - First Genre Match")
        public void givenFirstGenreMatches_whenRecommendMovies_thenShortCircuitTrue() {
            // given
            movies = List.of(
                    new Movie("Source", "S624", List.of("Action")),
                    new Movie("Target", "T167", List.of("Action", "Comedy"))
            );
            user = new User("User", "220LK11CR", List.of("S624"));
            // when
            result = recommender.recommendMoviesForUser(user, movies);
            // then
            assertTrue(result.stream().anyMatch(m -> m.getId().equals("T167")));
        }

        @Test
        @DisplayName("TC-C10: Recommendation Logic - Late Genre Match")
        public void givenSecondGenreMatches_whenRecommendMovies_thenFullLoopTrue() {
            // given
            movies = List.of(
                    new Movie("Source", "S624", List.of("Sci-Fi")),
                    new Movie("Target", "T167", List.of("Drama", "Sci-Fi"))
            );
            user = new User("User", "220LK11CR", List.of("S624"));
            // when
            result = recommender.recommendMoviesForUser(user, movies);
            // then
            assertTrue(result.stream().anyMatch(m -> m.getId().equals("T167")));
        }

        @Test
        @DisplayName("TC-C11: Recommendation Logic - No Match")
        public void givenNoGenreMatches_whenRecommendMovies_thenFullLoopFalse() {
            // given
            movies = List.of(
                    new Movie("Source", "S624", List.of("Action")),
                    new Movie("Target", "T167", List.of("Horror"))
            );
            user = new User("User", "220LK11CR", List.of("S624"));

            // when
            result = recommender.recommendMoviesForUser(user, movies);
            
            // then
            assertTrue(result.isEmpty());
        }
    }
}
