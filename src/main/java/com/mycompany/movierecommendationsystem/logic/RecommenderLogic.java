package com.mycompany.movierecommendationsystem.logic;

import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommenderLogic {

    protected MovieRecommender createMovieRecommender() {
        return new MovieRecommender();
    }

    public Map<User, List<Movie>> generateRecommendations(List<Movie> movies, List<User> users) {
        Map<User, List<Movie>> recommendations = new HashMap<>();
        MovieRecommender movieRecommender = createMovieRecommender();
        for (User user : users) {
            List<Movie> recommendedMovies = movieRecommender.recommendMoviesForUser(user, movies);
            recommendations.put(user, recommendedMovies);
        }
        return recommendations;
    }
}
