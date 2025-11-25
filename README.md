# 📘 Movie Recommendation System – Project Structure

This project reads movies.txt and users.txt, processes the data, validates it, and generates personalized movie recommendations.

Below is a clear explanation of each component.

---

## 🎬 1. Movie.java
Represents one movie from *movies.txt*.

Fields:
- title (String)
- id (String)
- genres (List<String>)

---

## 👤 2. User.java
Represents one user from *users.txt*.

Fields:
- name (String)
- id (String)
- likedMoviesIds (List<String>)

---

## ✔ 3. MovieValidator.java & UserValidator.java
Validate movie and user data.

- Ensure the input follows all required rules  
- Return a String error message if invalid  
- Return null if valid  

---

## 📄 4. FileHandler.java
Reads input files: movies.txt and users.txt.

Converts the file into a List<String> where each element is a line.

### Handles:
- ❌ File not found  
- ❌ Empty file  

Returns an error message in case of failure.

---

## 🔍 5. MovieParser.java

- Create Movie objects  
- Validate each movie using MovieValidator  
- Return a List<Movie>

---

## 🔍 6. UserParser.java

- Create User objects  
- Validate each user using UserValidator  
- Return a List<User>

---

## ⭐ 7. MovieRecommender.java
Generates recommendations for one user and prints them to the console.

### Rules:
- Must NOT recommend movies already watched by the user.

### Input:
- One User
- List of all Movie objects

### Output:
- List of recommended movies for that user

---

## ⭐ 8. RecommenderLogic.java
Generates recommendations for all users.

### Input:
- List<Movie> from MovieParser  
- List<User> from UserParser  

### Process:
- For each user → call MovieRecommender
- Store results in a Map<User, List<Movie>>

### Output:
- Complete recommendation map

---

## 📝 9. RecommendationWriter.java

### writeError(String errorMessage)
- Writes an error message to recommendations.txt
- Stops the program immediately  
- Used for fatal validation or file errors

### writeRecommendations(Map<User, List<Movie>> map)
- Writes recommendations for all users into recommendations.txt
- Used when the system runs successfully

---

## 🔗 10. RecommendationSystem.java
The controller that connects all components.

### Responsibilities:
- Execute the pipeline:  
  FileHandler → Parser → Validator → RecommenderLogic → Writer  
- Handle errors  
- Pass results to RecommendationWriter  

Acts as the core engine of the system.

---

## 🚀 11. RecommendationApp.java
The main application entry point.

### Responsibilities:
- Contains main()  
- Creates a RecommendationSystem  
- Supplies file paths  
- Starts execution  

---

---

# 🧪 Testing Types in the Project

The project includes the following types of testing:

### ✔ 1. Unit Testing (JUnit)
- Ensures each component behaves correctly on its own.

### ✔ 2. White-Box Testing
- Tests the internal logic of the code.

### ✔ 3. Data-Flow Testing
- Ensures variables are properly defined, used, and not misused.

### ✔ 4. Black-Box Testing
- Tests inputs and outputs without looking at internal code.

### ✔ 5. Integration Testing
- Ensures components work correctly together.
