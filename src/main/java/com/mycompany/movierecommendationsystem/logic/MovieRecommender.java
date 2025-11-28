package com.mycompany.movierecommendationsystem.logic;

import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;

import java.util.List;

public class MovieRecommender {
    public List<Movie> recommendMoviesForUser(User user, List<Movie> movies) {
        List<Movie> favouriteMovies = user.getLikedMovieIds().stream()
                .map(id -> movies.stream()
                        .filter(movie -> movie.getId().equals(id))
                        .findFirst()
                        .orElse(null))
                .toList();
        List<String> favGenres = favouriteMovies.stream()
                .map(Movie::getGenres)
                .flatMap(List::stream)
                .toList();

        return movies.stream()
                .filter(movie -> movie.getGenres().stream().anyMatch(favGenres::contains))
                .toList();
    }
}
