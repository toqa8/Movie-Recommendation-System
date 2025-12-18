package com.mycompany.movierecommendationsystem.logic.MovieRecommenderTests.BlackBoxTests;

import com.mycompany.movierecommendationsystem.logic.MovieRecommender;
import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoundaryValueAnalysisTest {
    private MovieRecommender recommender;
    private List<Movie> movies;
    private User user;
    List<Movie> result;

    @BeforeEach
    public void setUp() {
        recommender = new MovieRecommender();
    }

    @Test
    @DisplayName("T1: Return empty list if movie list is empty.")
    void givenEmptyMovieList_whenRecommendMovies_thenReturnEmptyList() {
        // given
        user = new User("Tim Allen","451ZK93TA",List.of("TS034"));

        // when
        result = recommender.recommendMoviesForUser(user, List.of());

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("T2: Given only one Movie which is User's Favorite")
    void givenOnlyTheUsersFavorite_whenRecommendMovies_thenReturnAllActionMovies() {
        // given
        movies = List.of(
                new Movie( "Action C","AC145", List.of("Action", "Drama"))
        );
        user = new User("Test Sr.", "451ZK93TA", List.of("AC145"));

        // when
        List<Movie> recommended = recommender.recommendMoviesForUser(user, movies);

        // then
        assertEquals(0, recommended.size());
    }

    @Test
    @DisplayName("T3: Return all other movies.")
    void givenTwoMoviesBothFavoriteGenre_whenRecommendMovies_thenReturnOtherMovieMatchingGenre() {
        // given
        movies = List.of(
                new Movie( "Movie A","MA645", List.of("Action")),
                new Movie("Movie B","MB469", List.of("Action"))
        );
        user = new User("James Normalson", "582JK44TR", List.of("MA645"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertEquals(1, result.size());
        assertFalse(result.contains(movies.get(0)));
        assertTrue(result.contains(movies.get(1)));
    }

    @Test
    @DisplayName("T4: Mix of Favorite, Valid Match, and Wrong Genre.")
    void givenMovies_whenRecommendMovies_thenReturnMovieMatchingGenre() {
        // given
        movies = List.of(
                new Movie( "Movie A","MA645", List.of("Action")),
                new Movie("Movie B","MB469", List.of("Comedy")),
                new Movie( "Movie C","MC642", List.of("Action"))
        );
        user = new User("James Normalson", "582JK44TR", List.of("MC642"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertEquals(1, result.size());
        assertTrue(result.contains(movies.get(0)));
        assertFalse(result.contains(movies.get(1)));
        assertFalse(result.contains(movies.get(2)));

    }
}
