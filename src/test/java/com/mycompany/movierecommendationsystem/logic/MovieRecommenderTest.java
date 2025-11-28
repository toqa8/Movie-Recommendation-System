package com.mycompany.movierecommendationsystem.logic;

import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieRecommenderTest {
    private MovieRecommender recommender;
    private List<Movie> movies;
    private User user;
    List<Movie> result;

    @BeforeEach
    public void setUp() {
        recommender = new MovieRecommender();
    }

    @Test
    @DisplayName("T1: Return all movies of the same genre as the user's favourite.")
    void givenUserLikesAction_whenRecommendMovies_thenReturnAllActionMovies() {
        // given
        movies = List.of(
                new Movie( "Action A","AA123", List.of("Action")),
                new Movie("Comedy B","CB564", List.of("Comedy")),
                new Movie( "Action C","AC145", List.of("Action", "Drama")),
                new Movie( "Romance D","RD349", List.of("Romance"))
        );
        user = new User("Test Sr.", "451ZK93TA", List.of("AC145"));

        // when
        List<Movie> recommended = recommender.recommendMoviesForUser(user, movies);

        // then
        assertEquals(1, recommended.size());
        assertTrue(recommended.contains(movies.get(0)));
        assertFalse(recommended.contains(movies.get(2)));
    }

    @Test
    @DisplayName("T2: Return movies that match any genre of the user's favourites.")
    void givenUserLikesMultipleGenres_whenRecommendMovies_thenReturnMoviesMatchingAnyGenre() {
        // given
        movies = List.of(
                new Movie( "Movie A","MA645", List.of("Action")),
                new Movie("Movie B","MB469", List.of("Comedy")),
                new Movie( "Movie C","MC642", List.of("Drama")),
                new Movie( "Movie D","MC145", List.of("Action", "Comedy")),
                new Movie( "Movie E","ME012", List.of("Horror"))
        );
        user = new User("James Normalson", "582JK44TR", List.of("MA645","MC642"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertEquals(1, result.size());
        assertFalse(result.contains(movies.get(0)));
        assertFalse(result.contains(movies.get(2)));
        assertTrue(result.contains(movies.get(3)));

    }

    @Test
    @DisplayName("T3: Return empty list if user has no favourites.")
    void givenUserLikesNoMovies_whenRecommendMovies_thenReturnEmptyList() {
        // given
        movies = List.of(
                new Movie("Movie A","MA548", List.of("Action"))
        );
        user = new User("The Mad Hater","811DF22MX",List.of());

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("T4: Return empty list if movie is not in catalog.")
    void givenUserLikesMovieNotInCatalog_whenRecommendMovies_thenReturnEmptyList() {
        // given
        movies = List.of(
                new Movie( "Movie A","MA468", List.of("Action"))
        );
        user = new User("Lemony Snicket","582JK44TR",List.of("NO999")); // Not found in list

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("T5: Return empty list if movie list is empty.")
    void givenEmptyMovieList_whenRecommendMovies_thenReturnEmptyList() {
        // given
        user = new User("Tim Allen","451ZK93TA",List.of("TS034"));

        // when
        result = recommender.recommendMoviesForUser(user, List.of());

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("T6: Duplicates genres in the movie data shouldn't cause an issue.")
    void givenFavoriteMovieHasDuplicateGenres_whenRecommendMovies_thenDuplicatesDoNotBreakLogic() {
        // given
        movies = List.of(
                new Movie( "Movie A","MA156", List.of("Action", "Action")),
                new Movie( "Movie B","MB417", List.of("Action")),
                new Movie( "Movie C","MC648", List.of("Comedy"))
        );
        user = new User("Dupie McDuperson","220LK11CR",List.of("MB417"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertEquals(2, result.size());
        assertTrue(result.contains(movies.get(0)));
        assertFalse(result.contains(movies.get(1)));
        assertFalse(result.contains(movies.get(2)));
    }

    @Test
    @DisplayName("T7: Recommend movies even if one of the favourites does not exist.")
    void givenUserLikesExistingAndNonExistingMovie_whenRecommendMovies_thenRecommendBasedOnExistingMovie() {
        // given
        movies = List.of(
                new Movie( "Movie A","MA123", List.of("Action")),
                new Movie( "Movie B","MB456", List.of("Action"))
        );
        user = new User("Test jr","549QP30VA",List.of("MA123", "NO456"));

        // when
        result = recommender.recommendMoviesForUser(user, movies);

        // then
        assertEquals(1, result.size());
        assertTrue(result.contains(movies.getLast()));
    }
}
