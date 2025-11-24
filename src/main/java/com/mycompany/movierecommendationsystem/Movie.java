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
import java.util.List;

public class Movie {
    private final String title;
    private final String id;
    private final List<String> genres;

    public Movie(String title, String id, List<String> genres) {
        this.title = title;
        this.id = id;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public List<String> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title=" + title + ' ' +
                ",id=" + id + ' ' +
                ",genres=" + genres +
                '}';
    }
}
