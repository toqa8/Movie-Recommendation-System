package com.mycompany.movierecommendationsystem.parser;

import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.validators.MovieValidator;
import com.mycompany.movierecommendationsystem.validators.ValidationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieParser {
    public static List<Movie> parseMovies(List<String> lines) throws ValidationException {
        List<Movie> movies = new ArrayList<>();

        if (lines.size() % 2 != 0) {
            throw new ValidationException("ERROR: movies.txt format is wrong");
        }

        for (int i = 0; i < lines.size(); i += 2) {
            String line1 = lines.get(i);
            String line2 = lines.get(i+1);
            //  title,id
            String[] parts = line1.split(",", 2);

            if (parts.length < 2) {
                throw new ValidationException("ERROR: movies.txt format is wrong");
            }

            String title = parts[0].trim();
            String id = parts[1].trim();

            // genres line
            String[] genresArr = Arrays.stream(line2.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toArray(String[]::new);

            List<String> genres = Arrays.asList(genresArr);

            // Build movie object
            Movie m = new Movie(title, id, genres);

            // Validate using  MovieValidator
            MovieValidator.validate(m);

            // Add to list
            movies.add(m);
        }
        return movies;
    }

}