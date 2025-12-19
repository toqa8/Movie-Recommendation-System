import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {
    @Test
    void testCompleteRecommendationFlow() {
        // Arrange
        User user = new User("John", "user1", List.of("movie1", "movie2"));
        List<Movie> movies = List.of(
            new Movie("Movie1", "movie1", List.of("Action")),
            new Movie("Movie2", "movie2", List.of("Drama")),
            new Movie("Movie3", "movie3", List.of("Action", "Drama"))
        );

        // Create real instances of the components
        MovieRecommender movieRecommender = new MovieRecommender();
        RecommenderLogic recommenderLogic = new RecommenderLogic();

        // Act
        Map<User, List<Movie>> recommendations = recommenderLogic.generateRecommendations(movies, List.of(user));

        // Assert
        assertNotNull(recommendations);
        assertTrue(recommendations.containsKey(user));
        assertEquals(1, recommendations.get(user).size());
        assertEquals("Movie3", recommendations.get(user).get(0).getTitle());
    }
}
