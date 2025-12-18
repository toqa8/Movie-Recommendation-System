package com.mycompany.movierecommendationsystem.logic.MovieRecommenderTests.BlackBoxTests;

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

public class EquivalenceClassPartitioningTest {
    private MovieRecommender recommender;
    private List<Movie> movies;
    private User user;
    List<Movie> result;

    @BeforeEach
    public void setUp() {
        recommender = new MovieRecommender();
    }

    @Test
    @DisplayName("T1: Standard Recommendation - Positive match from valid user and list.")
    void givenValidUserAndList_whenRecommendMovies_thenReturnPositiveMatch() {
        // User Scen 1 (Valid), List Scen 1 (Positive Match)
        // given
        movies = List.of(
                new Movie("Movie A", "MA100", List.of("Action")),
                new Movie("Movie B", "MB200", List.of("Action"))
        );
        user = new User("Standard User", "549QP30VA", List.of("MA100"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertEquals(1, result.size());
        assertTrue(result.stream().anyMatch(m -> m.getId().equals("MB200")));
    }

    @Test
    @DisplayName("T2: Exclusion - Do not recommend movies the user already likes.")
    void givenFavoriteInCatalog_whenRecommendMovies_thenFilterOutFavoriteFromResults() {
        // User Scen 1 (Valid), List Scen 2 (Exclusion)
        // given
        movies = List.of(
                new Movie("Movie A", "MA100", List.of("Action"))
        );
        user = new User("Exclusion User", "549QP30VA", List.of("MA100"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("T3: Mismatch - Do not recommend movies if genres do not match user favorites.")
    void givenNoGenreMatches_whenRecommendMovies_thenReturnEmptyList() {
        // User Scen 1 (Valid), List Scen 3 (Mismatch)
        // given
        movies = List.of(
                new Movie("Movie A", "MA100", List.of("Action")),
                new Movie("Movie C", "MC300", List.of("Comedy"))
        );
        user = new User("Comedy Hater", "549QP30VA", List.of("MA100"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("T4: Missing Favorite - Return empty if favorite ID is missing from catalog.")
    void givenFavoriteIDNotInCatalog_whenRecommendMovies_thenReturnEmptyList() {
        // User Scen 1 (Valid), List Scen 4 (Missing Fav)
        // given
        movies = List.of(
                new Movie("Movie B", "MB200", List.of("Action"))
        );
        user = new User("Missing User", "549QP30VA", List.of("MA100"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("T5: Empty List - Return empty list when movie catalog is empty.")
    void givenEmptyMovieList_whenRecommendMovies_thenReturnEmptyList() {
        // User Scen 1 (Valid), List Scen 5 (Empty List)
        // given
        user = new User("Empty User", "549QP30VA", List.of("MA100"));

        // when
        result = recommender.recommendMoviesForUser(user, List.of());

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("T6: Invalid User - Throw exception if user object is null.")
    void givenNullUser_whenRecommendMovies_thenThrowIllegalArgumentException() {
        // User Scen 2 (Invalid User)
        // given
        movies = List.of(new Movie("Movie B", "549QP30VA", List.of("Action")));
        user = null;

        // when / then
        assertThrows(IllegalArgumentException.class, () -> {
            recommender.recommendMoviesForUser(user, movies);
        });
    }

    @Test
    @DisplayName("T7: No Favorite Set - Throw exception if user's favorites list is null.")
    void givenUserWithNullFavoriteList_whenRecommendMovies_thenReturnEmptyList() {
        // User Scen 2 (No Fav Set)
        // given
        movies = List.of(new Movie("Movie B", "MB200", List.of("Action")));
        user = new User("Null Fav User", "549QP30VA", null);

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // when / then
        assertThrows(IllegalArgumentException.class, () -> {
            recommender.recommendMoviesForUser(user, movies);
        });
    }

    @Test
    @DisplayName("T8: Empty Fav Set - Return empty if user has no favorites.")
    void givenUserWithEmptyFavorites_whenRecommendMovies_thenReturnEmptyList() {
        // Partition: User Scen 2 (No Fav Set - Empty List)
        // given
        movies = List.of(new Movie("Movie B", "MB200", List.of("Action")));
        user = new User("Empty Favs", "549QP30VA", List.of());

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("T9: Null List - Throw exception if movie list is null.")
    void givenNullMovieList_whenRecommendMovies_thenReturnEmptyList() {
        // List Scen 5 (Null List)
        // given
        user = new User("Valid User", "549QP30VA", List.of("MA100"));

        // when / then
        assertThrows(IllegalArgumentException.class, () -> {
            recommender.recommendMoviesForUser(user, movies);
        });
    }
}
