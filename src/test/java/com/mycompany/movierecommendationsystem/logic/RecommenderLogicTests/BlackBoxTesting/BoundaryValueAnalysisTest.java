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

class BoundaryValueAnalysisTest {

    private List<Movie> movies;
    private Map<User, List<Movie>> result;
    private MovieRecommender movieRecommender;
    private RecommenderLogic logic;

    @BeforeEach
    void setUp() {
        movies = List.of(new Movie("Action A", "AA123", List.of("Action")));
        movieRecommender = mock(MovieRecommender.class);
        logic = spy(new RecommenderLogic());
        doReturn(movieRecommender).when(logic).createMovieRecommender();
    }

    @Test
    @DisplayName("BVA Min Value: Empty User List -> Empty Map")
    void testBoundary_EmptyUserList() { 
        List<User> emptyUsers = List.of();

        result = logic.generateRecommendations(movies, emptyUsers);

        assertTrue(result.isEmpty());
        verify(movieRecommender, never()).recommendMoviesForUser(any(), any());
    }

    @Test
    @DisplayName("BVA Min Value: Empty Movie List -> Map with Empty Values")
    void testBoundary_EmptyMovieList() { 
        User user = new User("User A", "ID1", List.of("AA123"));
        List<User> users = List.of(user);
        List<Movie> emptyMovies = List.of();

        when(movieRecommender.recommendMoviesForUser(user, emptyMovies)).thenReturn(List.of());

        result = logic.generateRecommendations(emptyMovies, users);

        assertFalse(result.isEmpty());
        assertTrue(result.get(user).isEmpty()); 
    }
}