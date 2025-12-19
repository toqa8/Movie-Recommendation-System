/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.movierecommendationsystem.logic.RecommenderLogicTests.DataFlowTesting;

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

/**
 Data Flow Testing
 */

class DataFlowTest {

    private RecommenderLogic logic;
    private MovieRecommender movieRecommender;
    private List<Movie> movies;

    @BeforeEach
    void setUp() {
      
        movieRecommender = mock(MovieRecommender.class);
        logic = spy(new RecommenderLogic());
        
       
        doReturn(movieRecommender).when(logic).createMovieRecommender();

        movies = List.of(new Movie("Inception", "M1", List.of("Sci-Fi")));
    }

    /**
     * Strategy: All-Defs & All-Uses (Loop Entry Path)
     * Path Covered: 1 -> 2 -> 3 -> 4 -> 5 -> ... -> 2 -> 6
     */
    @Test
    @DisplayName("Data Flow: Loop Entry Path (Standard Case)")
    void testLoopEntryPath_StandardExecution() {
        // Given: Non-empty list to force entering the loop
        User user = new User("Alice", "U1", List.of("M1"));
        List<User> users = List.of(user);

        when(movieRecommender.recommendMoviesForUser(user, movies)).thenReturn(movies);

        Map<User, List<Movie>> result = logic.generateRecommendations(movies, users);

      
        assertEquals(1, result.size());
        assertTrue(result.containsKey(user));
        
      
        verify(movieRecommender, times(1)).recommendMoviesForUser(user, movies);
    }

    /**
     * Strategy: All-Uses & ADUP (Loop Skip Path)
     * Path Covered: 1 -> 2 -> 6
     */
    @Test
    @DisplayName("Data Flow: Loop Skip Path (Empty List)")
    void testLoopSkipPath_EmptyUserList() {
        // Given: Empty list to force skipping the loop
        List<User> users = List.of();

        Map<User, List<Movie>> result = logic.generateRecommendations(movies, users);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(movieRecommender, never()).recommendMoviesForUser(any(), any());
    }
}