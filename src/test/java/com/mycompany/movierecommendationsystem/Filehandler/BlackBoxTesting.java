package com.mycompany.movierecommendationsystem;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileHandlerTest {

    @TempDir
    Path tempDir;

    private FileHandler fileHandler;

    private PrintStream originalOut;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        fileHandler = new FileHandler();

        // Capture System.out
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void FH_BB_01() { //missingFile_returnsEmptyList_andPrintsNotFoundError
        String missingPath = tempDir.resolve("missing.txt").toString();

        List<String> result = fileHandler.readFile(missingPath);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        String console = outContent.toString();
        assertTrue(console.contains("Error: The file '" + missingPath + "' was not found."));
    }

    @Test
    void FH_BB_02() throws Exception { //emptyFile_returnsEmptyList_andPrintsEmptyWarning
        Path emptyFile = tempDir.resolve("empty.txt");
        Files.createFile(emptyFile);

        List<String> result = fileHandler.readFile(emptyFile.toString());

        assertNotNull(result);
        assertTrue(result.isEmpty());

        String console = outContent.toString();
        assertTrue(console.contains("Warning: The file '" + emptyFile + "' is empty."));
    }

    @Test
    void FH_BB_03() throws Exception { //singleLineFile_returnsOneLine_andPrintsNothing
        Path dataFile = tempDir.resolve("single.txt");
        Files.write(dataFile, List.of("A"));

        List<String> result = fileHandler.readFile(dataFile.toString());

        assertEquals(List.of("A"), result);

        String console = outContent.toString();
        assertFalse(console.contains("Error:"));
        assertFalse(console.contains("Warning:"));
    }

    @Test
    void FH_BB_04() throws Exception { //multiLineFile_returnsAllLines_inOrder
        Path dataFile = tempDir.resolve("data.txt");
        Files.write(dataFile, List.of("A", "B", "C"));

        List<String> result = fileHandler.readFile(dataFile.toString());

        assertEquals(List.of("A", "B", "C"), result);

        String console = outContent.toString();
        assertFalse(console.contains("Error:"));
        assertFalse(console.contains("Warning:"));
    }

    @Test
    void FH_BB_05() throws Exception { //directoryPath_returnsEmptyList_andPrintsIoError_andAlsoEmptyWarning
        Path dirPath = tempDir.resolve("somedir");
        Files.createDirectory(dirPath);

        List<String> result = fileHandler.readFile(dirPath.toString());

        assertNotNull(result);
        assertTrue(result.isEmpty());

        String console = outContent.toString();
        assertTrue(console.contains("An error occurred while reading the file:"));
        assertTrue(console.contains("Warning: The file '" + dirPath + "' is empty."));
    }

    @Test
    void FH_BB_06() { //nullFileName_throwsNullPointerException
        assertThrows(NullPointerException.class, () -> fileHandler.readFile(null));
    }
}
