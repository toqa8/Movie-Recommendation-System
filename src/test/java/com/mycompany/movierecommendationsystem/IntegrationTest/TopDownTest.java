package com.mycompany.movierecommendationsystem.IntegrationTest;

import com.mycompany.movierecommendationsystem.logic.MovieRecommender;
import com.mycompany.movierecommendationsystem.logic.RecommenderLogic;
import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TopDownTest {
    private MovieRecommender movieRecommenderMock;
    private RecommenderLogic recommenderLogic;

    @BeforeEach
    void setUp() {
        // Create mock for MovieRecommender
        movieRecommenderMock = mock(MovieRecommender.class);
        recommenderLogic = spy(new RecommenderLogic());
        doReturn(movieRecommenderMock).when(recommenderLogic).createMovieRecommender();
    }

    @Test
    void testGenerateRecommendations() {
        // Arrange
        User user = new User("John", "user1", List.of("movie1", "movie2"));
        List<Movie> movies = List.of(
            new Movie("Movie1", "movie1", List.of("Action")),
            new Movie("Movie2", "movie2", List.of("Drama")),
            new Movie("Movie3", "movie3", List.of("Action", "Drama"))
        );
        List<Movie> expectedRecommendations = List.of(new Movie("Movie3", "movie3", List.of("Action", "Drama")));

        // Simulate the behavior of movieRecommenderMock
        when(movieRecommenderMock.recommendMoviesForUser(user, movies)).thenReturn(expectedRecommendations);

        // Act
        Map<User, List<Movie>> recommendations = recommenderLogic.generateRecommendations(movies, List.of(user));

        // Assert
        assertEquals(expectedRecommendations, recommendations.get(user));
    }
}
