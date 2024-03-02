package org.search.Strategy;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

/**
 * The FindAll class implements the FindStrategy interface and
 * provides a strategy for finding people's names
 * based on a specified search strategy.
 */
public class FindAll implements FindStrategy {

    /**
     * Finds people's names based on a specified search strategy.
     *
     * @param strategy The search strategy to use ("ALL").
     * @param listOfPeople The list of people's names to search in.
     * @param sc The Scanner to use for input.
     * @param wordToLineNumber The map of words to line numbers.
     */
    @Override
    public final void find(final String strategy,
                           final ArrayList<String> listOfPeople,
                     final Scanner sc, final Map<String,
            ArrayList<Integer>> wordToLineNumber) {

        print();

//        String wordsToSearch = sc.nextLine();

//        String[] listOfWordsToSearch = wordsToSearchMethod(sc.nextLine());

        HashMap<Integer, Integer> mappingOfLines = mappingOfLinesMethod(wordsToSearchMethod(sc.nextLine()), wordToLineNumber);

        output(mappingOfLines, wordsToSearchMethod(sc.nextLine()), listOfPeople);
    }

    public void output(HashMap<Integer, Integer> mappingOfLines,
                       String[] listOfWordsToSearch, ArrayList<String> listOfPeople) {
        for (Map.Entry<Integer, Integer> entry : mappingOfLines.entrySet()) {
            int lineNumber = entry.getKey();
            int numberOfOccurrence = entry.getValue();
            if (numberOfOccurrence == listOfWordsToSearch.length) {
                System.out.println(listOfPeople.get(lineNumber));
            }
        }
    }

    public HashMap<Integer, Integer> mappingOfLinesMethod(String[] listOfWordsToSearch, Map<String,
            ArrayList<Integer>> wordToLineNumber) {

        HashMap<Integer, Integer> mappingOfLines = new HashMap<>();

        for (String wordToSearch : listOfWordsToSearch) {
            if (wordToLineNumber.get(wordToSearch.toLowerCase()) == null) {
                System.out.println("No matching people found");
                return null;
            }
            List<Integer> listOfLineNoHavingWord = wordToLineNumber
                    .get(wordToSearch.toLowerCase());
            for (int lineNumber : listOfLineNoHavingWord) {
                if (mappingOfLines.get(lineNumber) == null) {
                    mappingOfLines.put(lineNumber, 1);
                } else {
                    int x = mappingOfLines.get(lineNumber);
                    mappingOfLines.put(lineNumber, x + 1);
                }
            }
        }

        return mappingOfLines;
    }

    public String[] wordsToSearchMethod(String wordsToSearch) {
        return wordsToSearch.split(" ");
    }

    public void print() {
        System.out.println("Enter a name or email "
                + "to search all suitable people.");
    }


}
