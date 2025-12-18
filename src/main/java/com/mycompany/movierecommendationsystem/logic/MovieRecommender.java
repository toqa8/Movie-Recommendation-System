package com.mycompany.movierecommendationsystem.logic;

import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieRecommender {

    public List<Movie> recommendMoviesForUser(User user, List<Movie> movies) {
        if (user == null || movies == null) {
            throw new IllegalArgumentException("User or movies cannot be null");
        }

        Set<String> likedIds = new HashSet<>(user.getLikedMovieIds());

        Set<Movie> favouriteMovies = new HashSet<>();
        Set<String> favGenres = new HashSet<>();

        for (Movie movie : movies) {
            if (likedIds.contains(movie.getId())) {
                favouriteMovies.add(movie);
                favGenres.addAll(movie.getGenres());
            }
        }

        return movies.stream()
                .filter(movie -> !favouriteMovies.contains(movie))
                .filter(movie -> movie.getGenres().stream().anyMatch(favGenres::contains))
                .toList();
    }
}
