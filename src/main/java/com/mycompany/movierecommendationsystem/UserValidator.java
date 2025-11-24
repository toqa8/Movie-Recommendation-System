/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movierecommendationsystem;

/**
 *
 * @author hp
 */
import java.util.Set;

public class UserValidator {

    // Validate user name
    public String validateUserName(String name) {

    if (name == null) {
        return "Invalid user name";
    }

    if (!name.isEmpty() && Character.isSpaceChar(name.charAt(0))) {
        return "Invalid user name";
    }

    String trimmed = name.trim();

    if (trimmed.isEmpty()) {
        return "Invalid user name";
    }

    for (char c : trimmed.toCharArray()) {
        if (!(Character.isLetter(c) || c == ' ')) {
            return "Invalid user name";
        }
    }

    return null; 
}
    

    // Validate user ID (length 9, starts with digit, at least one letter)
    public String validateUserId(String userId) {

        if (userId == null) {
            return "Invalid user ID";
        }

        userId = userId.trim();

        // Must be exactly 9 characters
        if (userId.length() != 9) {
            return "Invalid user ID";
        }

        // Must start with digit
        if (!Character.isDigit(userId.charAt(0))) {
            return "Invalid user ID";
        }

        int letterCount = 0;

        // Check each char
        for (int i = 0; i < 9; i++) {
            char c = userId.charAt(i);

            if (Character.isLetter(c)) {
                letterCount++;
            } else if (!Character.isDigit(c)) {
                return "Invalid user ID";  // no symbols allowed
            }
        }

        // Must contain at least ONE letter
        if (letterCount == 0) {
            return "Invalid user ID";
        }

        return null;
    }

    
    // Check duplicate IDs
    public String validateUserIdUniqueness(String id, Set<String> usedIds) {
        if (usedIds.contains(id)) {
            return "Duplicate user ID";
        }
        return null;
    }
}