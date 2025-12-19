package com.mycompany.movierecommendationsystem.parser.UserParserTests.BlackBoxTesting;

import com.mycompany.movierecommendationsystem.models.User;
import com.mycompany.movierecommendationsystem.parser.UserParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserParserStateTransitionTest {

    @Test
    public void testStateTransitionFromErrorToValid() {

        UserParser parser = new UserParser();

        // -------- First call: Error state --------
        List<String> invalidLines = Arrays.asList(
                "123,1A2345678",   // invalid name
                "M1"
        );

        List<User> users1 = parser.parseUsers(invalidLines);

        assertNull(users1);
        assertNotNull(parser.getLastError());

        // -------- Second call: Valid state --------
        List<String> validLines = Arrays.asList(
                "Ahmed,1A2345678",
                "M1"
        );

        List<User> users2 = parser.parseUsers(validLines);

        assertNotNull(users2);
        assertEquals(1, users2.size());
        assertNull(parser.getLastError());
    }
}