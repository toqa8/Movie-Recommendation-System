package com.mycompany.movierecommendationsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

 public class FileHandler {

   
    public List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        File file = new File(fileName);

        
        if (!file.exists()) {
            System.out.println("Error: The file '" + fileName + "' was not found.");
            return lines;
        }

        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

   
        if (lines.isEmpty() && file.exists()) {
            System.out.println("Warning: The file '" + fileName + "' is empty.");
        }

        return lines;
    }
}
