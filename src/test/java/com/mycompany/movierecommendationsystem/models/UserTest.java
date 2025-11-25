/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.movierecommendationsystem.models;

import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hp
 */
public class UserTest {

    @Test
    public void testUserConstructorTrimsInput() {
        List<String> movies = Arrays.asList("  M1  ", "M2", "   M3");
        User u = new User("  Ahmed Ali  ", " 123A56B78 ", movies);

        assertEquals("Ahmed Ali", u.getName());
        assertEquals("123A56B78", u.getId());
        assertEquals(Arrays.asList("M1", "M2", "M3"), u.getLikedMovieIds());
    }

    @Test
    public void testEmptyMovieIdsRemoved() {
        List<String> movies = Arrays.asList("M1", "", "   ", "M2");
        User u = new User("Omar", "1A2345678", movies);

        assertEquals(Arrays.asList("M1", "M2"), u.getLikedMovieIds());
    }

    @Test
public void testToStringContainsImportantData() {
    List<String> movies = Arrays.asList("M1", "M2");
    User u = new User("John", "1A2345678", movies);

    String s = u.toString();

    assertTrue(s.contains("John"));
    assertTrue(s.contains("1A2345678"));
    assertTrue(s.contains("M1"));
}
}