/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.movierecommendationsystem.logic.RecommenderLogicTests.BlackBoxTesting;

import com.mycompany.movierecommendationsystem.logic.MovieRecommender;
import com.mycompany.movierecommendationsystem.logic.RecommenderLogic;
import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;
/**
 *
 * @author nadam
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquivalenceClassPartitioningTest {

    private List<Movie> movies;
    private List<User> users;
    private Map<User, List<Movie>> result;
    private MovieRecommender movieRecommender;
    private RecommenderLogic logic;

    @BeforeEach
    void setUp() {
        movies = List.of(
            new Movie("Action A", "AA123", List.of("Action")),
            new Movie("Comedy B", "CB564", List.of("Comedy")),
            new Movie("Drama F", "DF642", List.of("Drama"))
        );
        movieRecommender = mock(MovieRecommender.class);
        logic = spy(new RecommenderLogic());
        doReturn(movieRecommender).when(logic).createMovieRecommender();
    }

    @Test
    @DisplayName("ECP Class 1 (Valid): Users with valid matches get recommendations")
    void testValidPartition_StandardScenario() {
        // Class: Valid Input -> Valid Output
        User userA = new User("Test Sr.", "451ZK93TA", List.of("AA123"));
        users = List.of(userA);

        when(movieRecommender.recommendMoviesForUser(userA, movies)).thenReturn(List.of(movies.get(0)));

        result = logic.generateRecommendations(movies, users);

        assertEquals(1, result.size());
        assertFalse(result.get(userA).isEmpty());
        verify(movieRecommender, times(1)).recommendMoviesForUser(userA, movies);
    }

    @Test
    @DisplayName("ECP Class 2 (Invalid): User with no liked movies -> Empty List")
    void testInvalidPartition_NoLikedMovies() { 
        User user = new User("No History", "811DF22MX", List.of());
        users = List.of(user);

        when(movieRecommender.recommendMoviesForUser(user, movies)).thenReturn(List.of());

        result = logic.generateRecommendations(movies, users);

        assertTrue(result.get(user).isEmpty());
    }

    @Test
    @DisplayName("ECP Class 3 (Invalid): User likes unknown movie -> Empty List")
    void testInvalidPartition_UnknownMovie() { 
        User user = new User("Unknown Movie", "582JK44TR", List.of("NO999"));
        users = List.of(user);

        when(movieRecommender.recommendMoviesForUser(user, movies)).thenReturn(List.of());

        result = logic.generateRecommendations(movies, users);

        assertTrue(result.get(user).isEmpty());
    }
}