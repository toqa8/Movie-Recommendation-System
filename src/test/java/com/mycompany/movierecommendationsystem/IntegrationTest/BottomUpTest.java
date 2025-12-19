package com.mycompany.movierecommendationsystem.IntegrationTest;

import com.mycompany.movierecommendationsystem.logic.MovieRecommender;
import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BottomUpTest {
    @Test
    void testRecommendMoviesForUser() {
        // Arrange
        User user = new User("John", "user1", List.of("movie1", "movie2"));
        List<Movie> movies = List.of(
            new Movie("Movie1", "movie1", List.of("Action")),
            new Movie("Movie2", "movie2", List.of("Drama")),
            new Movie("Movie3", "movie3", List.of("Action", "Drama"))
        );
        MovieRecommender movieRecommender = new MovieRecommender();

        // Act
        List<Movie> recommendedMovies = movieRecommender.recommendMoviesForUser(user, movies);

        // Assert
        assertEquals(1, recommendedMovies.size());
        assertEquals("Movie3", recommendedMovies.get(0).getTitle());
    }
}
