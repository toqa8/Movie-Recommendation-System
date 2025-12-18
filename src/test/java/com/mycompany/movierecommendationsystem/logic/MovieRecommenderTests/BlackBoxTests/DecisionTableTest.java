package com.mycompany.movierecommendationsystem.logic.MovieRecommenderTests.BlackBoxTests;

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

public class DecisionTableTest {
    private MovieRecommender recommender;
    private List<Movie> movies;
    private User user;
    List<Movie> result;

    @BeforeEach
    public void setUp() {
        recommender = new MovieRecommender();
        user = new User("User", "549QP30VA", List.of("MA100"));
    }

    @Test
    @DisplayName("T1: R1 - Throw exception if movie list is null.")
    void givenNullList_whenRecommendMovies_thenThrowError() {
        // Rule R1: C1=No (Null)
        assertThrows(IllegalArgumentException.class, () -> {
            recommender.recommendMoviesForUser(user, null);
        });
    }

    @Test
    @DisplayName("T2: R2 - Throw exception if user is null.")
    void givenNullUser_whenRecommendMovies_thenThrowError() {
        // Rule R2: C1=Yes, C2=No (User Null)
        // given
        movies = List.of(new Movie("Movie A", "MA100", List.of("Action")));
        user = null;

        // when / then
        assertThrows(IllegalArgumentException.class, () -> {
            recommender.recommendMoviesForUser(user, movies);
        });
    }

    @Test
    @DisplayName("T3: R3 - Return empty if user's favorite ID is not in the list.")
    void givenFavoriteIdNotFoundInList_whenRecommendMovies_thenReturnEmpty() {
        // Rule R3: C1=Yes, C2=Yes, C3=No (Fav not found in catalog)
        // given
        movies = List.of(new Movie("Movie B", "MB200", List.of("Action")));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("T4: R4 - Return empty if movie genre does not match favorite's genre.")
    void givenNoGenreMatch_whenRecommendMovies_thenReturnEmpty() {
        // Rule R4: C1=Yes, C2=Yes, C3=Yes, C4=No (Genre Mismatch)
        // given
        movies = List.of(
                new Movie("Movie A", "MA100", List.of("Action")),
                new Movie("Movie C", "MC300", List.of("Comedy"))
        );

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        // MA100 is Action, MC300 is Comedy. Result should be empty.
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("T5: R5 - Exclude movie if ID matches the user's favorite ID.")
    void givenMovieIsAlreadyAFavorite_whenRecommendMovies_thenExcludeFromResults() {
        // Rule R5: C1=Yes, C2=Yes, C3=Yes, C4=Yes, C5=Yes (Same ID)
        // given
        movies = List.of(new Movie("Movie A", "MA100", List.of("Action")));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("T6: R6 - Recommend movie if genre matches but ID is different.")
    void givenGenreMatchesAndIdIsDifferent_whenRecommendMovies_thenAddToResults() {
        // Rule R6: C1=Yes, C2=Yes, C3=Yes, C4=Yes, C5=No (Success)
        // given
        movies = List.of(
                new Movie("Movie A", "MA100", List.of("Action")),
                new Movie("Movie B", "MB200", List.of("Action"))
        );

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertEquals(1, result.size());
        assertEquals("MB200", result.get(0).getId());
    }
}
