package org.search.Strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FindAnyNoneTest {

    FindAnyNone findAnyNone;
    Map<String, ArrayList<Integer>> wordToLineNumber;
    ArrayList<String> listOfPeople;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    String strategy;

    @BeforeEach
    void setUp() {
        findAnyNone = new FindAnyNone();
        listOfPeople = new ArrayList<>();
        System.setOut(new PrintStream(outputStream));
        strategy = null;
    }

    @Test
    void find() {
    }

    @Test
    void testGetSetOfLines(){

        String[] listOfWordsToSearch = {"John", "smith"};

        Map<String, ArrayList<Integer>>
                wordToLineNumber = new HashMap<>();

        wordToLineNumber.put("john", new ArrayList<>(List.of(0)));
        wordToLineNumber.put("cadre", new ArrayList<>(List.of(1)));
        wordToLineNumber.put("Smith", new ArrayList<>(List.of(2)));

        HashSet<Integer> setOfLines = new HashSet<>();
        setOfLines.add(0);

        assertEquals(setOfLines, findAnyNone.getSetOfLines(listOfWordsToSearch, wordToLineNumber));

    }

    @Test
    void testOutput() {

        listOfPeople.add("John");
        listOfPeople.add("Doe");
        listOfPeople.add("Jane");
        listOfPeople.add("Smith");

        //"ayush jane"

        HashSet<Integer> setOfLines = new HashSet<>();
        setOfLines.add(2);

        findAnyNone.output("NONE", listOfPeople, setOfLines);

        assertEquals("John\nDoe\nSmith\n", outputStream.toString());

        System.setOut(new PrintStream(outputStream));
//
        HashSet<Integer> setOfLines1 = new HashSet<>();

        findAnyNone.output("ANY", listOfPeople, setOfLines1);
        assertEquals("John\nDoe\nSmith\nNo matching people found\n", outputStream.toString());


        HashSet<Integer> setOfLines2 = new HashSet<>();
        setOfLines2.add(2);

        findAnyNone.output("ANY", listOfPeople, setOfLines2);

        assertEquals("John\nDoe\nSmith\nNo matching people found\nJane\n", outputStream.toString());

    }


    @Test
    void testPrint(){
        findAnyNone.print();
        String expectedOutput = "Enter a name or email to "
                + "search all suitable people.\n";

        assertEquals(expectedOutput, outputStream.toString());
    }
}