package org.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private String exit = "1";
    private int optionSelected = -1;
    ArrayList<String> listOfPeople;

    @BeforeEach
    void setUp(){
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void main() {

    }

    @Test
    void testGetFileName(){
        String[] args = {"--data", "testing.txt"};

        String actualFileName = Main.getFileName(args);

        assertEquals("testing.txt", actualFileName);
        assertNotEquals(null, actualFileName);
    }

    @Test
    void testGetFileNameNull(){
        String[] args = {};

        String actualFileName = Main.getFileName(args);

        assertEquals(null, actualFileName);
    }

    @Test
    void testExecute(){

        listOfPeople = new ArrayList<>();

        listOfPeople.add("John");
        listOfPeople.add("Doe");
        listOfPeople.add("Jane");
        listOfPeople.add("Smith");

        Map<String, ArrayList<Integer>> wordToLineNumber = new HashMap<>();
        wordToLineNumber.put("john", new ArrayList<>(List.of(0)));
        wordToLineNumber.put("dwight", new ArrayList<>(List.of(1)));
        wordToLineNumber.put("rene", new ArrayList<>(List.of(2)));
        wordToLineNumber.put("erick", new ArrayList<>(List.of(3)));

        Main.execute(5, listOfPeople, wordToLineNumber);
        assertEquals("Incorrect option! Try again.\n", outputStream.toString());

        Main.execute(2, listOfPeople, wordToLineNumber);
        assertEquals("Incorrect option! Try again.\nJohn\nDoe\nJane\nSmith\n", outputStream.toString());

        Main.execute(0, listOfPeople, wordToLineNumber);
        assertEquals("Incorrect option! Try again.\nJohn\nDoe\nJane\nSmith\nBye!\n", outputStream.toString());

        String strategySelected = "ALL";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((strategySelected).getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);

        Main.execute(1, listOfPeople, wordToLineNumber);

        assertEquals("Incorrect option! Try again.\nJohn\nDoe\nJane\nSmith\nBye!\nSelect a matching strategy: ALL, ANY, NONE\n", outputStream.toString());

    }

    @Test
    void testExecuteAny(){
        listOfPeople = new ArrayList<>();

        listOfPeople.add("John");
        listOfPeople.add("Doe");
        listOfPeople.add("Jane");
        listOfPeople.add("Smith");

        Map<String, ArrayList<Integer>> wordToLineNumber = new HashMap<>();
        wordToLineNumber.put("john", new ArrayList<>(List.of(0)));
        wordToLineNumber.put("dwight", new ArrayList<>(List.of(1)));
        wordToLineNumber.put("rene", new ArrayList<>(List.of(2)));
        wordToLineNumber.put("erick", new ArrayList<>(List.of(3)));

        String strategySelected = "ANY";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((strategySelected).getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);

        Main.execute(1, listOfPeople, wordToLineNumber);

        assertEquals("Select a matching strategy: "
                + "ALL, ANY, NONE\n", outputStream.toString());
    }

    @Test
    void testExecuteNONE(){
        listOfPeople = new ArrayList<>();

        listOfPeople.add("John");
        listOfPeople.add("Doe");
        listOfPeople.add("Jane");
        listOfPeople.add("Smith");

        Map<String, ArrayList<Integer>> wordToLineNumber = new HashMap<>();
        wordToLineNumber.put("john", new ArrayList<>(List.of(0)));
        wordToLineNumber.put("dwight", new ArrayList<>(List.of(1)));
        wordToLineNumber.put("rene", new ArrayList<>(List.of(2)));
        wordToLineNumber.put("erick", new ArrayList<>(List.of(3)));

        String strategySelected = "NONE";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((strategySelected).getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);

        Main.execute(1, listOfPeople, wordToLineNumber);

        assertEquals("Select a matching strategy: "
                + "ALL, ANY, NONE\n", outputStream.toString());
    }

    @Test
    void testGetOption(){

        String optionSelect = "1";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((optionSelect).getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
        String actualOptionSelected = String.valueOf(Main.getOption());

        assertEquals(optionSelect, actualOptionSelected);
    }

    @Test
    void testGetOptionWithThrow(){

        String optionSelect1 = "h";
        ByteArrayInputStream inputStream1 = new ByteArrayInputStream((optionSelect1).getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream1);

        Main.getOption();

//        assertThrows(InputMismatchException.class, () -> Main.getOption());
        assertEquals("Please enter number among 0,1,2: ", outputStream.toString());
    }

    @Test
    void testPrintMenu(){
        Main.printMenu();
        String menu = "=== Menu ===\n1. Find a person\n2. Print all people\n0. Exit\n";
        assertEquals(menu, outputStream.toString());
    }

    @Test
    void testWordToLineNumberMethod(){

        Map<String, ArrayList<Integer>> expectedWordToLineNumber = new HashMap<>();
        expectedWordToLineNumber.put("john", new ArrayList<>(List.of(0)));
        expectedWordToLineNumber.put("dwight", new ArrayList<>(List.of(1)));
        expectedWordToLineNumber.put("rene", new ArrayList<>(List.of(2)));
        expectedWordToLineNumber.put("erick", new ArrayList<>(List.of(3)));

        ArrayList<String> listOfPeople = new ArrayList<>();

        String content = "John\nDwight\nRene\nErick";

        Map<String, ArrayList<Integer>> actualWordToLineNumber =
                Main.wordToLineNumberMethod(content.split("\n"), listOfPeople);

        assertEquals(expectedWordToLineNumber, actualWordToLineNumber);

    }

    @Test
    void testReadFileWhenFileExists() {
        try {
            String fileName = "testing.txt";
            String content = """
                                Dwight Joseph djo@gmail.com
                                Rene Webb webb@gmail.com
                                Katie Jacobs
                                Erick Harrington harrington@gmail.com
                                Myrtle Medina
                                Erick Burgess
                                """;

            Path path = Paths.get(fileName);
            Files.write(path, content.getBytes(StandardCharsets.UTF_8));
            String fileContent = Main.readFile(fileName);
            assertEquals(content, fileContent, "File content mismatch");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testReadFileWhenFileDoesNotExist() {
        assertThrows(IOException.class, () -> Main.readFile("test.txt"), "Working fine!");
    }

    @Test
    void testPrintAllPeople(){

        listOfPeople = new ArrayList<>();

        listOfPeople.add("John");
        listOfPeople.add("Doe");
        listOfPeople.add("Jane");
        listOfPeople.add("Smith");

        Main.printAllPeople(listOfPeople);

        assertEquals("John\nDoe\nJane\nSmith\n", outputStream.toString());

    }
}