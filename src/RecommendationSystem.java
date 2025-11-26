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

            
            List<Movie> movies = movieParser.parse(movieLines); 
            List<User> users   = userParser.parse(userLines);   

            
            Map<User, List<Movie>> recommendations =
                    recommenderLogic.generateRecommendations(movies, users);

           
            recommendationWriter.writeRecommendations(recommendations);

        } catch (Exception e) {
     
            recommendationWriter.writeError(e.getMessage());
        }
    }
}
