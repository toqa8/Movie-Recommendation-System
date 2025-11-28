package com.mycompany.movierecommendationsystem.recommender;

import com.mycompany.movierecommendationsystem.models.Movie;
import com.mycompany.movierecommendationsystem.models.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


public final class RecommendationWriter {

    private static final String OUTPUT_FILE_NAME = "recommendations.txt";

    
    public RecommendationWriter() {
    }

    
    public static void writeError(String errorMessage) {
        if (errorMessage == null || errorMessage.trim().isEmpty()) {
            errorMessage = "Unknown error";
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE_NAME, false))) {
            writer.println(errorMessage);
        } catch (IOException e) {
          
            System.err.println("Failed to write error message to " + OUTPUT_FILE_NAME);
            e.printStackTrace();
        }
	System.exit(1);
    }

   
    public static void writeRecommendations(Map<User, List<Movie>> recommendations) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE_NAME, false))) {

            for (Map.Entry<User, List<Movie>> entry : recommendations.entrySet()) {
                User user = entry.getKey();
                List<Movie> movies = entry.getValue();

            
                writer.println(user.getName() + "," + user.getId());

               
                if (movies != null && !movies.isEmpty()) {
                    StringBuilder line = new StringBuilder();
                    for (int i = 0; i < movies.size(); i++) {
                        if (i > 0) {
                            line.append(",");
                        }
                        line.append(movies.get(i).getTitle());
                    }
                    writer.println(line.toString());
                } else {
                   
                    writer.println();
                }
            }

        } catch (IOException e) {
      
            writeError("ERROR: Failed to write recommendations file.");
        }
    }
}
