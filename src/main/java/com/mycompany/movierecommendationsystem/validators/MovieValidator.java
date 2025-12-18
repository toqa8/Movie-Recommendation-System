/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movierecommendationsystem.validators;

/**
 *
 * @author nadam
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nadam
 */
import com.mycompany.movierecommendationsystem.models.Movie;

import java.util.HashSet;
import java.util.Set;

public class MovieValidator {

    private static final Set<String> usedNumbers = new HashSet<>();

    public static void reset() {
        usedNumbers.clear();
    }

    public static void validate(Movie movie) throws ValidationException {
        
        if (!isValidTitle(movie.getTitle())) {
            throw new ValidationException("ERROR: Movie Title " + movie.getTitle() + " is wrong");
        }

        String capsFromTitle = extractCapitals(movie.getTitle());

        if (!movie.getId().startsWith(capsFromTitle)) {
            throw new ValidationException("ERROR: Movie Id letters " + movie.getId() + " are wrong");
        }

        String idNumbers = movie.getId().substring(capsFromTitle.length());
        
        if (!isValidGlobalNumber(idNumbers)) {
            throw new ValidationException("ERROR: Movie Id numbers " + movie.getId() + " aren't unique");
        }
        
        usedNumbers.add(idNumbers);
    }

    // --- Helper Methods ---

    private static boolean isValidTitle(String title) {
        if (title == null || title.trim().isEmpty()) return false;
        String[] words = title.split("\\s+");
        for (String word : words) {
            if (word.isEmpty() || !Character.isUpperCase(word.charAt(0))) {
                return false;
            }
        }
        return true;
    }

    private static String extractCapitals(String title) {
        StringBuilder sb = new StringBuilder();
        for (char c : title.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static boolean isValidGlobalNumber(String suffix) {
        // 1. Must be exactly 3 characters long
        if (suffix.length() != 3) return false;

        // 2. Must be digits only
        if (!suffix.matches("\\d+")) return false;

        // 3. Must NOT be in the usedNumbers set (Global Uniqueness)
        if (usedNumbers.contains(suffix)) {
            return false; 
        }

        return true;
    }
}