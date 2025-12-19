package com.mycompany.movierecommendationsystem.parser.UserParserTests.BlackBoxTesting;

import com.mycompany.movierecommendationsystem.models.User;
import com.mycompany.movierecommendationsystem.parser.UserParser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserParserEquivalenceClassTest {

    @Test
    public void testValidEquivalenceClass() {

        List<String> lines = Arrays.asList(
                "Ahmed,1A2345678",
                "M1,M2"
        );

        UserParser parser = new UserParser();
        List<User> users = parser.parseUsers(lines);

        assertNotNull(users);
        assertEquals(1, users.size());
        assertNull(parser.getLastError());
    }

    @Test
    public void testInvalidNameEquivalenceClass() {

        List<String> lines = Arrays.asList(
                "123,1A2345678",   // invalid name
                "M1"
        );

        UserParser parser = new UserParser();
        List<User> users = parser.parseUsers(lines);

        assertNull(users);
        assertNotNull(parser.getLastError());
    }

    @Test
    public void testInvalidIdEquivalenceClass() {

        List<String> lines = Arrays.asList(
                "Ahmed,ABC",      // invalid ID
                "M1"
        );

        UserParser parser = new UserParser();
        List<User> users = parser.parseUsers(lines);

        assertNull(users);
        assertNotNull(parser.getLastError());
    }
}