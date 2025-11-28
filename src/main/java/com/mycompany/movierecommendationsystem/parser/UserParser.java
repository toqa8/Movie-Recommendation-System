package com.mycompany.movierecommendationsystem.parser;

import com.mycompany.movierecommendationsystem.models.User;
import com.mycompany.movierecommendationsystem.validators.UserValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserParser {

    private String lastError = null;

    public List<User> parseUsers(List<String> lines) {

        lastError = null;

        List<User> users = new ArrayList<>();
        UserValidator validator = new UserValidator();

        for (int i = 0; i < lines.size(); i += 2) {

            String nameAndId = lines.get(i).trim();
            String likedMoviesLine = lines.get(i + 1).trim();

            String[] parts = nameAndId.split(",");

            String name = parts[0].trim();
            String id = parts[1].trim();

            // Validate name
            String nameError = validator.validateUserName(name);
            if (nameError != null) {
                lastError = nameError;
                return null;
            }

            // Validate ID
            String idError = validator.validateUserId(id);
            if (idError != null) {
                lastError = idError;
                return null;
            }

            // liked movies
            List<String> likedMovies = likedMoviesLine.isEmpty()
                    ? new ArrayList<>()
                    : Arrays.asList(likedMoviesLine.split(","));

            users.add(new User(name, id, likedMovies));
        }

        return users;
    }

    public String getLastError() {
        return lastError;
    }
}