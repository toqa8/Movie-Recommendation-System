/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.movierecommendationsystem.models;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {

    @Test
    public void testMovieConstructorAndGetter1() {
       
        String title = "The Dark Knight";
        String id = "TDK123";
        List<String> genres = Arrays.asList("Action", "Drama");
        
        Movie movie = new Movie(title, id, genres);
        assertEquals( title, movie.getTitle(), "Title should match constructor input");
        
       
    }
 @Test
    public void testMovieGetter2() {
       
        String title = "The Dark Knight";
        String id = "TDK123";
        List<String> genres = Arrays.asList("Action", "Drama");
        
        Movie movie = new Movie(title, id, genres);
      assertEquals(id, movie.getId(), "ID should match constructor input");}
        
    @Test
    public void testMovieGetter3() {
       
        String title = "The Dark Knight";
        String id = "TDK123";
        List<String> genres = Arrays.asList("Action", "Drama");
        
        Movie movie = new Movie(title, id, genres);
        assertEquals( genres, movie.getGenres(), "Genres list should match constructor input");
    }
    @Test
    public void testToString() {
        Movie movie = new Movie("Inception", "I456", Collections.singletonList("Sci-Fi"));
        
        String result = movie.toString();
        assertEquals("Movie{title=Inception ,id=I456 ,genres=[Sci-Fi]}",result);
       
    }
}

    