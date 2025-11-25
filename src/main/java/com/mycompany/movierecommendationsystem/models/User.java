/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movierecommendationsystem.models;

/**
 *
 * @author hp
 */
import java.util.*;

public class User {
    private String name;
    private String id;
    private List<String> likedMovieIds;

    public User(String name, String id, List<String> likedMovieIds) {
        this.name = name.trim();
        this.id = id.trim();
        this.likedMovieIds = new ArrayList<>();
        for (String m : likedMovieIds) {
            if (!m.trim().isEmpty())
                this.likedMovieIds.add(m.trim());
        }
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<String> getLikedMovieIds() {
        return new ArrayList<>(likedMovieIds);
    }

    @Override
    public String toString() {
        return name + " (" + id + ") likes " + likedMovieIds;
    }
}