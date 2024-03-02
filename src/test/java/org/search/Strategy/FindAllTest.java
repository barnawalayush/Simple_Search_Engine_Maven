package org.search.Strategy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FindAllTest {

    FindAll findAll;
//    Map<String, ArrayList<Integer>> wordToLineNumber;
    ArrayList<String> listOfPeople;
    Scanner sc;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUpStreams() {
        findAll = new FindAll();
        listOfPeople = new ArrayList<>();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void find() {

    }

    @Test
    void testPrint(){
        findAll.print();
        String expectedStatement = "Enter a name or email "
                + "to search all suitable people.\n";
        assertEquals(expectedStatement, outputStream.toString());
    }

    @Test
    void testWordToSearchMethod(){
        String input = "John Doe";
        String[] x = findAll.wordsToSearchMethod(input);

        assertEquals(2, x.length);
    }

    @Test
    void output(){
        HashMap<Integer, Integer> mappingOfLines1 = new HashMap<>();
        String[] listOfWordsToSearch = {"john"};

        listOfPeople.add("John");
        listOfPeople.add("Doe");
        listOfPeople.add("Jane");
        listOfPeople.add("Smith");

        mappingOfLines1.put(0, 1);
        mappingOfLines1.put(1, 1);
        mappingOfLines1.put(3, 1);

        findAll.output(mappingOfLines1, listOfWordsToSearch, listOfPeople);

        String expectedOutput = "john\nDoe";

        assertEquals("John\nDoe\nSmith\n", outputStream.toString());

    }

    @Test
    void testMappingOfLinesMethod(){

        String[] listOfWordsToSearch = {"john", "Doe"};

        String[] listOfWordsToSearch1 = {"smith", "john"};

        String[] listOfWordsToSearch2 = {"henry"};

        Map<String, ArrayList<Integer>> wordToLineNumber = new HashMap<>();

        wordToLineNumber.put("john", new ArrayList<>(List.of(0, 3)));
        wordToLineNumber.put("doe", new ArrayList<>(List.of(1)));
        wordToLineNumber.put("jane", new ArrayList<>(List.of(2)));
        wordToLineNumber.put("smith", new ArrayList<>(List.of(3)));

        HashMap<Integer, Integer> mappingOfLines1 = new HashMap<>();

        mappingOfLines1.put(0, 1);
        mappingOfLines1.put(1, 1);
        mappingOfLines1.put(3, 1);

        HashMap<Integer, Integer> mappingOfLines2 = new HashMap<>();

        mappingOfLines2.put(0, 1);
        mappingOfLines2.put(3, 2);

        assertEquals(mappingOfLines1, findAll.mappingOfLinesMethod(listOfWordsToSearch, wordToLineNumber));
        assertEquals(mappingOfLines2, findAll.mappingOfLinesMethod(listOfWordsToSearch1, wordToLineNumber));

        HashMap<Integer, Integer> mappingOfLines3 = new HashMap<>();

        assertEquals(null, findAll.mappingOfLinesMethod(listOfWordsToSearch2, wordToLineNumber));
    }
}