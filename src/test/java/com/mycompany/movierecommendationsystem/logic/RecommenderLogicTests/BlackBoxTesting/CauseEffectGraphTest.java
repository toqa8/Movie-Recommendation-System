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

class CauseEffectGraphTest {

    private RecommenderLogic logic;
    private MovieRecommender movieRecommender;

    @BeforeEach
    void setUp() {
        movieRecommender = mock(MovieRecommender.class);
        logic = spy(new RecommenderLogic());
        doReturn(movieRecommender).when(logic).createMovieRecommender();
    }

    @Test
    @DisplayName("Cause: Valid User + Valid Movie + Match -> Effect: Recommendation Generated")
    void testCauseEffect_SuccessfulRecommendation() {
        // Cause 1: User exists
        User user = new User("U1", "1", List.of("M1"));
        // Cause 2: Movies exist
        List<Movie> movies = List.of(new Movie("M1", "ID1", List.of("Action")));
        
        // Mocking the 'Match' logic (Implicit Cause 3)
        when(movieRecommender.recommendMoviesForUser(user, movies)).thenReturn(movies);

        // Effect check
        Map<User, List<Movie>> result = logic.generateRecommendations(movies, List.of(user));

        // Assert Effect
        assertFalse(result.isEmpty());
        assertEquals(1, result.get(user).size());
    }

    @Test
    @DisplayName("Cause: Valid User + No Match -> Effect: Empty Recommendation List")
    void testCauseEffect_NoMatch() {
        // Cause 1: User exists
        User user = new User("U1", "1", List.of("M1"));
        // Cause 2: Movies exist
        List<Movie> movies = List.of(new Movie("M2", "ID2", List.of("Comedy"))); // Different movie

        // Mocking 'No Match'
        when(movieRecommender.recommendMoviesForUser(user, movies)).thenReturn(List.of());

        // Effect check
        Map<User, List<Movie>> result = logic.generateRecommendations(movies, List.of(user));

        // Assert Effect
        assertFalse(result.isEmpty()); // User key exists
        assertTrue(result.get(user).isEmpty()); // But list is empty
    }
}