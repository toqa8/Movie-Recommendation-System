/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movierecommendationsystem.validators;

/**
 *
 * @author hp
 */

public class UserValidator {

    // Validate user name
    public String validateUserName(String name) {

    if (name == null) {
        return "ERROR: User Name null is wrong";
    }

     // Name cannot start with space
    if (!name.isEmpty() && Character.isSpaceChar(name.charAt(0))) {
        return "ERROR: User Name " + name + " is wrong";
    }

    String trimmed = name.trim();

    if (trimmed.isEmpty()) {
        return "ERROR: User Name " + name + " is wrong";
    }

    // Only letters + spaces allowed
    for (char c : trimmed.toCharArray()) {
        if (!(Character.isLetter(c) || c == ' ')) {
            return "ERROR: User Name " + name + " is wrong";
        }
    }

    return null; // valid
}
    

    // Validate user ID (length 9, starts with digit, at least one letter)
    public String validateUserId(String userId) {

        if (userId == null) {
            return "ERROR: User Id null is wrong";
        }

        userId = userId.trim();

        // Must be exactly 9 characters
        if (userId.length() != 9) {
            return "ERROR: User Id " + userId + " is wrong";
        }

        // Must start with digit
        if (!Character.isDigit(userId.charAt(0))) {
            return "ERROR: User Id " + userId + " is wrong";
        }

        int letterCount = 0;

        // Check each char
        for (int i = 0; i < 9; i++) {
            char c = userId.charAt(i);

            if (Character.isLetter(c)) {
                letterCount++;
            } else if (!Character.isDigit(c)) {
                return "ERROR: User Id " + userId + " is wrong";  // no symbols allowed
            }
        }

        // Must contain at least ONE letter
        if (letterCount == 0) {
            return "ERROR: User Id " + userId + " is wrong";
        }

        return null; // valid
    }
}