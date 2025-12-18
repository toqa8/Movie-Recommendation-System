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

class DecisionTableTest {

    private MovieRecommender movieRecommender;
    private RecommenderLogic logic;

    @BeforeEach
    void setUp() {
        movieRecommender = mock(MovieRecommender.class);
        logic = spy(new RecommenderLogic());
        doReturn(movieRecommender).when(logic).createMovieRecommender();
    }

    @Test
    @DisplayName("Rule 1: If Users List is Empty -> Return Empty Map")
    void testDecisionRule1() {
        List<User> users = List.of();
        List<Movie> movies = List.of(new Movie("M1", "1", List.of("G")));

        Map<User, List<Movie>> result = logic.generateRecommendations(movies, users);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Rule 2: If Users Not Empty AND Movies Empty -> Return Map with Empty Lists")
    void testDecisionRule2() {
        User user = new User("U1", "1", List.of("M1"));
        List<User> users = List.of(user);
        List<Movie> movies = List.of(); // Condition: Empty Movies

        when(movieRecommender.recommendMoviesForUser(user, movies)).thenReturn(List.of());

        Map<User, List<Movie>> result = logic.generateRecommendations(movies, users);

        assertEquals(1, result.size());
        assertTrue(result.get(user).isEmpty());
    }

    @Test
    @DisplayName("Rule 3: If Users Not Empty AND Movies Not Empty -> Return Map with Recommendations")
    void testDecisionRule3() {
        User user = new User("U1", "1", List.of("M1"));
        List<User> users = List.of(user);
        List<Movie> movies = List.of(new Movie("M1", "1", List.of("G")));

        when(movieRecommender.recommendMoviesForUser(user, movies)).thenReturn(movies);

        Map<User, List<Movie>> result = logic.generateRecommendations(movies, users);

        assertEquals(1, result.size());
        assertFalse(result.get(user).isEmpty());
    }
}