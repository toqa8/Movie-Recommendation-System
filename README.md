# 📘 Movie Recommendation System – Project Structure

This project reads movies.txt and users.txt, processes the data, validates it, and generates personalized movie recommendations.

Below is a clear explanation of each component.

[![](https://mermaid.ink/img/pako:eNqVVm1vmzAQ_ivIn4hGqiQkTYOmSnvrVqmZqrXbpC37YAWHWgMbGdOtq9LfPtsQcgaTpnxAtu-5x-fnzgePaM1jgiI0HA5XTFKZkshb8ntKvC9kzbOMsBhLypl381BIkq2YAa5TXBTvKU4EVkueemIqyNoAb9-uWLVmUDXbY7Wkn6F3IwVliWe2c6zTGC5e0UI-VZYnLyFMkGJvflXR-5Ax2PMExvt1NT-vvQfQvYYmRN5qX7_HeBnblhat_GiYnd6SVwN_4FXWra3P14IIpzwMZy9UJ6W_SWwEKS5jSyW9iQ94-zXacyiKHjU-K4aXKAXSJ68g_zOCAb06FfUNp1SVJre0azjuKyvxq-rL9HvQK_-RXCZTpXq5mS5oSj5hFqekxWNJIAiONXKXjY0aaz3dnCb8aywKJ6exPnm5tldp91v1wJpydxy7l1cba1o9PJ7VBNE0jkMxix2oivuCC1OijcSBhTb569kTbHfFE7q291zi3BzH4jN9hAiVU7vJ1Qe1Ng2gJDqyoq8sba7vgsq2APecxt4fbfggBG8uJNGTJSkKnJCBG9-Os-dYwoY9I5gBVX3d2YAqAXSxXmN55wAYOVx2eBU2-zGEwMrO9mMIATVaNkMI6KRetBacYJge4ViEGXBpZTctJ8IpYOCUrZtwUTL_mMy9yXO7vAqp1tcVSYYpq8P4-cvDIukUA0zAcHje7qqRDrP-hoBEaKTdMnfAA6wKs1Z9Tx7ggxDABHsJgO5Dc6J2m-5hnUppYNDXPoqzYEAQvJR5KYtnwLtYGrQLX19CjYdXx3mCtgNU_CgHoP5R-I52L_OCghwSWJdz17FmBNWBApQIGqNIipIEKCNC1bqaInMXVkjeEfXfhCI1jMkGl6lcoRXbKrccsx-cZztPwcvkDkUbnBZqVub6G1__0zYQc-p3vGQSRaezieFA0SP6i6Lx6PRkOp_NF-NFOB7PJuE0QA8omownJ6ez8TwMw8XZNJzPtgH6Z3YdnUzD0SgMZ-FkEs4Wk_FZuP0PeIzDXQ?type=png)](https://mermaid.live/edit#pako:eNqVVm1vmzAQ_ivIn4hGqiQkTYOmSnvrVqmZqrXbpC37YAWHWgMbGdOtq9LfPtsQcgaTpnxAtu-5x-fnzgePaM1jgiI0HA5XTFKZkshb8ntKvC9kzbOMsBhLypl381BIkq2YAa5TXBTvKU4EVkueemIqyNoAb9-uWLVmUDXbY7Wkn6F3IwVliWe2c6zTGC5e0UI-VZYnLyFMkGJvflXR-5Ax2PMExvt1NT-vvQfQvYYmRN5qX7_HeBnblhat_GiYnd6SVwN_4FXWra3P14IIpzwMZy9UJ6W_SWwEKS5jSyW9iQ94-zXacyiKHjU-K4aXKAXSJ68g_zOCAb06FfUNp1SVJre0azjuKyvxq-rL9HvQK_-RXCZTpXq5mS5oSj5hFqekxWNJIAiONXKXjY0aaz3dnCb8aywKJ6exPnm5tldp91v1wJpydxy7l1cba1o9PJ7VBNE0jkMxix2oivuCC1OijcSBhTb569kTbHfFE7q291zi3BzH4jN9hAiVU7vJ1Qe1Ng2gJDqyoq8sba7vgsq2APecxt4fbfggBG8uJNGTJSkKnJCBG9-Os-dYwoY9I5gBVX3d2YAqAXSxXmN55wAYOVx2eBU2-zGEwMrO9mMIATVaNkMI6KRetBacYJge4ViEGXBpZTctJ8IpYOCUrZtwUTL_mMy9yXO7vAqp1tcVSYYpq8P4-cvDIukUA0zAcHje7qqRDrP-hoBEaKTdMnfAA6wKs1Z9Tx7ggxDABHsJgO5Dc6J2m-5hnUppYNDXPoqzYEAQvJR5KYtnwLtYGrQLX19CjYdXx3mCtgNU_CgHoP5R-I52L_OCghwSWJdz17FmBNWBApQIGqNIipIEKCNC1bqaInMXVkjeEfXfhCI1jMkGl6lcoRXbKrccsx-cZztPwcvkDkUbnBZqVub6G1__0zYQc-p3vGQSRaezieFA0SP6i6Lx6PRkOp_NF-NFOB7PJuE0QA8omownJ6ez8TwMw8XZNJzPtgH6Z3YdnUzD0SgMZ-FkEs4Wk_FZuP0PeIzDXQ)

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
