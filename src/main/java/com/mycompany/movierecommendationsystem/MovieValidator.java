/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movierecommendationsystem;

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
import java.util.HashSet;
import java.util.Set;

class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}

public class MovieValidator {

    /**
     * Validates the movie logic using Exceptions.Throws ValidationException if any rule is violated.
     * @param movie
     * @throws ValidationException
     */
    public static void validate(Movie movie) throws ValidationException {
      
        if (!isValidTitle(movie.getTitle())) {
            throw new ValidationException("ERROR: Movie Title " + movie.getTitle() + " is wrong");
        }

        // Extract Capital Letters from Title to check ID
        String capsFromTitle = extractCapitals(movie.getTitle());

        if (!movie.getId().startsWith(capsFromTitle)) {
            throw new ValidationException("ERROR: Movie Id letters " + movie.getId() + " are wrong");
        }

        // We take the part of the ID after the capital letters
        String idNumbers = movie.getId().substring(capsFromTitle.length());
        
        if (!areUniqueNumbers(idNumbers)) {
            
            throw new ValidationException("ERROR: Movie Id numbers " + movie.getId() + " aren't unique");
        }
    }

    // --- Helper Methods (Private) ---

    private static boolean isValidTitle(String title) {
        if (title == null || title.trim().isEmpty()) return false;
        
        String[] words = title.split("\\s+"); // Split by whitespace
        for (String word : words) {
            // Check if the word is not empty and starts with UpperCase
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

    private static boolean areUniqueNumbers(String suffix) {
        // Check 1: Length must be exactly 3
        if (suffix.length() != 3) return false;

        // Check 2: Must be digits
        if (!suffix.matches("\\d+")) return false;

        // Check 3: Digits must be unique (e.g., "123" ok, "112" bad)
        Set<Character> uniqueDigits = new HashSet<>();
        for (char c : suffix.toCharArray()) {
            // If we can't add it to the set, it means it's a duplicate
            if (!uniqueDigits.add(c)) {
                return false; 
            }
        }
        return true;
    }
}