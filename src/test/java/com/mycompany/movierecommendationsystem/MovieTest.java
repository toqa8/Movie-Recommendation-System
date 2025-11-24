/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.movierecommendationsystem;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class MovieTest {

    @Test
    public void testMovieConstructorAndGetter1() {
       
        String title = "The Dark Knight";
        String id = "TDK123";
        List<String> genres = Arrays.asList("Action", "Drama");
        
        Movie movie = new Movie(title, id, genres);
        assertEquals("Title should match constructor input", title, movie.getTitle());
        
       
    }
 @Test
    public void testMovieGetter2() {
       
        String title = "The Dark Knight";
        String id = "TDK123";
        List<String> genres = Arrays.asList("Action", "Drama");
        
        Movie movie = new Movie(title, id, genres);
      assertEquals("ID should match constructor input", id, movie.getId());}
        
 @Test
    public void testMovieGetter3() {
       
        String title = "The Dark Knight";
        String id = "TDK123";
        List<String> genres = Arrays.asList("Action", "Drama");
        
        Movie movie = new Movie(title, id, genres);
     assertEquals("Genres list should match constructor input", genres, movie.getGenres());}
    @Test
    public void testToString() {
        Movie movie = new Movie("Inception", "I456", Collections.singletonList("Sci-Fi"));
        
        String result = movie.toString();
        assertEquals("Movie{title=Inception ,id=I456 ,genres=[Sci-Fi]}",result);
       
    }}

    