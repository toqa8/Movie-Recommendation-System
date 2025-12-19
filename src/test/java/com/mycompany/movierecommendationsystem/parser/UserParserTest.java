package com.mycompany.movierecommendationsystem.parser;

import java.util.Arrays;
import java.util.List;

import com.mycompany.movierecommendationsystem.models.User;
import com.mycompany.movierecommendationsystem.parser.UserParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserParserTest {

    @Test
    public void testParsingMultipleValidUsers() {

        List<String> lines = Arrays.asList(
            // User 1
            "Ahmed,1A2345678",
            "M1,M2,M3",
            
            // User 2
            "Sara,2B9876543",
            "M2,M5",

            // User 3
            "John Doe,9C111111A",
            "M9"
        );

        UserParser parser = new UserParser();
        List<User> users = parser.parseUsers(lines);

        assertNotNull(users);
        assertEquals(3, users.size());

        // --------- User 1 ---------
        User u1 = users.get(0);
        assertEquals("Ahmed", u1.getName());
        assertEquals("1A2345678", u1.getId());
        assertEquals(Arrays.asList("M1","M2","M3"), u1.getLikedMovieIds());

        // --------- User 2 ---------
        User u2 = users.get(1);
        assertEquals("Sara", u2.getName());
        assertEquals("2B9876543", u2.getId());
        assertEquals(Arrays.asList("M2","M5"), u2.getLikedMovieIds());

        // --------- User 3 ---------
        User u3 = users.get(2);
        assertEquals("John Doe", u3.getName());
        assertEquals("9C111111A", u3.getId());
        assertEquals(Arrays.asList("M9"), u3.getLikedMovieIds());

        assertNull(parser.getLastError());
    }
}