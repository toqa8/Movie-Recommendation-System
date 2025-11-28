package com.mycompany.movierecommendationsystem;

import com.mycompany.movierecommendationsystem.logic.RecommenderLogic;
import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;
import com.mycompany.movierecommendationsystem.parser.MovieParser;
import com.mycompany.movierecommendationsystem.parser.UserParser;
import com.mycompany.movierecommendationsystem.recommender.RecommendationWriter;

import java.util.List;
import java.util.Map;

public class RecommendationSystem {


    private final String moviesFilePath;
    private final String usersFilePath;


    private final FileHandler fileHandler;
    private final MovieParser movieParser;
    private final UserParser userParser;
    private final RecommenderLogic recommenderLogic;
    private final RecommendationWriter recommendationWriter;


    public RecommendationSystem() {
        this("movies.txt", "users.txt");
    }

    public RecommendationSystem(String moviesFilePath, String usersFilePath) {
        this.moviesFilePath = moviesFilePath;
        this.usersFilePath = usersFilePath;

        this.fileHandler = new FileHandler();
        this.movieParser = new MovieParser();
        this.userParser = new UserParser();
        this.recommenderLogic = new RecommenderLogic();
        this.recommendationWriter = new RecommendationWriter();
    }

        public void run() {
        try {
            List<String> movieLines = fileHandler.readFile(moviesFilePath);
            List<String> userLines  = fileHandler.readFile(usersFilePath);


            List<Movie> movies = movieParser.parseMovies(movieLines);
            List<User> users   = userParser.parseUsers(userLines);

            if (users==null) {
                
                RecommendationWriter.writeError(userParser.getLastError());
                return;
            }

            Map<User, List<Movie>> recommendations =
                    recommenderLogic.generateRecommendations(movies, users);


            recommendationWriter.writeRecommendations(recommendations);

        } catch (Exception e) {

            recommendationWriter.writeError(e.getMessage());
        }
    }
}
