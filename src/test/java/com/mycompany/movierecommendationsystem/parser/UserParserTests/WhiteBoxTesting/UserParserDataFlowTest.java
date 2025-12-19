package com.mycompany.movierecommendationsystem.parser.UserParserTests.WhiteBoxTesting;

import com.mycompany.movierecommendationsystem.models.User;
import com.mycompany.movierecommendationsystem.parser.UserParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserParserDataFlowTest {
    @Test
// Data Flow: lastError defined when name is invalid
    public void testInvalidNameDataFlow() {

        List<String> lines = Arrays.asList(
                "123,1A2345678",   // invalid name
                "M1,M2"
        );

        UserParser parser = new UserParser();
        List<User> users = parser.parseUsers(lines);

        assertNull(users);                     // return path
        assertNotNull(parser.getLastError());  // use of lastError
    }

    @Test
// Data Flow: lastError defined when ID is invalid
    public void testInvalidIdDataFlow() {

        List<String> lines = Arrays.asList(
                "Ahmed,AAAA",      // invalid ID
                "M1,M2"
        );

        UserParser parser = new UserParser();
        List<User> users = parser.parseUsers(lines);

        assertNull(users);
        assertNotNull(parser.getLastError());
    }

    @Test
// Data Flow: likedMovies defined as empty list
    public void testEmptyLikedMoviesDataFlow() {

        List<String> lines = Arrays.asList(
                "Ahmed,1A2345678",
                ""                 // empty liked movies
        );

        UserParser parser = new UserParser();
        List<User> users = parser.parseUsers(lines);

        assertNotNull(users);
        assertEquals(1, users.size());
        assertTrue(users.get(0).getLikedMovieIds().isEmpty());
    }

    @Test
// Data Flow: likedMovies defined from split
    public void testNonEmptyLikedMoviesDataFlow() {

        List<String> lines = Arrays.asList(
                "Ahmed,1A2345678",
                "M1,M2,M3"
        );

        UserParser parser = new UserParser();
        List<User> users = parser.parseUsers(lines);

        assertNotNull(users);
        assertEquals(Arrays.asList("M1","M2","M3"),
                users.get(0).getLikedMovieIds());
    }

    @Test
// Data Flow: lastError reset after successful run
    public void testLastErrorResetDataFlow() {

        UserParser parser = new UserParser();

        // First run: error
        List<String> badLines = Arrays.asList(
                "123,1A2345678",
                "M1"
        );
        assertNull(parser.parseUsers(badLines));
        assertNotNull(parser.getLastError());

        // Second run: valid
        List<String> goodLines = Arrays.asList(
                "Ahmed,1A2345678",
                "M1"
        );
        List<User> users = parser.parseUsers(goodLines);

        assertNotNull(users);
        assertNull(parser.getLastError());   // lastError reset
    }
}
