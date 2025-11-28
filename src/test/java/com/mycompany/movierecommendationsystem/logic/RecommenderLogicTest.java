package com.mycompany.movierecommendationsystem.logic;

import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecommenderLogicTest {
    private List<Movie> movies;
    private List<User> users;
    private Map<User, List<Movie>> result;

    private MovieRecommender movieRecommender;
    private RecommenderLogic logic;

    @BeforeEach
    void setUp() {
        movies = List.of(
                new Movie( "Action A","AA123", List.of("Action")),
                new Movie("Comedy B","CB564", List.of("Comedy")),
                new Movie( "Action Drama C","ADC145", List.of("Action", "Drama")),
                new Movie( "Romance D","RD349", List.of("Romance")),
                new Movie("Comedy E","CE469", List.of("Comedy")),
                new Movie( "Drama F","DF642", List.of("Drama")),
                new Movie( "Action Comedy G","ACG145", List.of("Action", "Comedy")),
                new Movie( "Horror H","HH012", List.of("Horror"))
        );
        movieRecommender = mock(MovieRecommender.class);

        logic = spy(new RecommenderLogic());
        doReturn(movieRecommender).when(logic).createMovieRecommender();
    }

    @Test
    void givenUserWithNoLikedMovies_whenGenerateRecommendations_thenReturnEmptyList() {
        // given
        User user = new User("The Mad Hater","811DF22MX",List.of());
        users = List.of(user);

        when(movieRecommender.recommendMoviesForUser(user, movies)).thenReturn(List.of());

        // when
        result = logic.generateRecommendations(movies, users);

        // then
        assertTrue(result.containsKey(user));
        assertTrue(result.get(user).isEmpty());
        verify(movieRecommender, times(1)).recommendMoviesForUser(user, movies);
    }

    @Test
    void givenUserWithLikedMoviesNotInList_whenGenerateRecommendations_thenReturnEmptyList() {
        // given
        User user = new User("Lemony Snicket","582JK44TR",List.of("NO999"));
        users = List.of(user);

        when(movieRecommender.recommendMoviesForUser(user, movies)).thenReturn(List.of());

        // when
        result = logic.generateRecommendations(movies, users);

        // then
        assertTrue(result.get(user).isEmpty());
        verify(movieRecommender, times(1)).recommendMoviesForUser(user, movies);
    }

    @Test
    void givenMultipleUsersWithOverlappingLikedMovies_whenGenerateRecommendations_thenReturnCorrectRecommendationsForEach() {
        // given
        User userA = new User("Test Sr.", "451ZK93TA", List.of("AA123"));
        User userB = new User("Test jr","549QP30VA",List.of("DF642"));
        User userC = new User("James Normalson", "582JK44TR", List.of("CE469","HH012"));
        users = List.of(userA, userB, userC);

        when(movieRecommender.recommendMoviesForUser(userA, movies)).thenReturn(
                List.of(movies.get(2), movies.get(6)));
        when(movieRecommender.recommendMoviesForUser(userB, movies)).thenReturn(
                List.of(movies.get(2)));
        when(movieRecommender.recommendMoviesForUser(userC, movies)).thenReturn(
                List.of(movies.get(1),movies.get(6)));

        // when
        result = logic.generateRecommendations(movies, users);

        // then
        assertEquals(3, result.size());
        assertEquals(List.of(movies.get(2), movies.get(6)), result.get(userA));
        assertEquals(List.of(movies.get(2)), result.get(userB));
        assertEquals( List.of(movies.get(1),movies.get(6)), result.get(userC));

        verify(movieRecommender, times(1)).recommendMoviesForUser(userA, movies);
        verify(movieRecommender, times(1)).recommendMoviesForUser(userB, movies);
        verify(movieRecommender, times(1)).recommendMoviesForUser(userC, movies);
    }

    @Test
    void givenEmptyUserList_whenGenerateRecommendations_thenReturnEmptyMap() {
        // given
        users = List.of();

        // when
        result = logic.generateRecommendations(movies, users);

        // then
        assertTrue(result.isEmpty());
        verify(movieRecommender, times(0)).recommendMoviesForUser(any(), any());
    }

    @Test
    void givenEmptyMovieList_whenGenerateRecommendations_thenAllUsersReceiveEmptyRecommendations() {
        // given
        movies = List.of();

        User userA = new User("Test Sr.", "451ZK93TA", List.of("AA123"));
        User userB = new User("Test jr","549QP30VA",List.of("DF642"));
        users = List.of(userA, userB);

        when(movieRecommender.recommendMoviesForUser(userA, movies)).thenReturn(List.of());
        when(movieRecommender.recommendMoviesForUser(userB, movies)).thenReturn(List.of());

        // when
        result = logic.generateRecommendations(movies, users);

        // then
        assertTrue(result.get(userA).isEmpty());
        assertTrue(result.get(userB).isEmpty());
        verify(movieRecommender, times(1)).recommendMoviesForUser(userA, movies);
        verify(movieRecommender, times(1)).recommendMoviesForUser(userB, movies);
    }
}
