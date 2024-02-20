package org.search.Strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Map;
import java.util.List;

/**
 * The FindAnyNone class implements the FindStrategy interface
 * and provides a strategy for finding people's names
 * based on a specified search strategy.
 */
public class FindAnyNone implements FindStrategy {

    /**
     * Finds people's names based on a specified search strategy.
     *
     * @param strategy The search strategy to use ("ANY" or "NONE").
     * @param listOfPeople The list of people's names to search in.
     * @param sc The Scanner to use for input.
     * @param wordToLineNumber The map of words to line numbers.
     */

    @Override
    public final void find(final String strategy,
                           final ArrayList<String> listOfPeople,
                           final Scanner sc, final Map<String,
            ArrayList<Integer>> wordToLineNumber) {

        int totalPeople = listOfPeople.size();

        HashSet<Integer> setOfLines = new HashSet<>();

        System.out.println("Enter a name or email to "
                + "search all suitable people.");
        String wordsToSearch = sc.nextLine();

        String[] listOfWordsToSearch = wordsToSearch.split(" ");

        for (String wordToSearch : listOfWordsToSearch) {
            if (wordToLineNumber.get(wordToSearch.toLowerCase()) != null) {
                List<Integer> listOfLineNumberHavingWord = wordToLineNumber
                        .get(wordToSearch.toLowerCase());
                for (int lineNumber : listOfLineNumberHavingWord) {
                    setOfLines.add(lineNumber);
                }
            }
        }

        if (strategy.equals("NONE")) {
            for (int i = 0; i < totalPeople; i++) {
                if (!setOfLines.contains(i)) {
                    System.out.println(listOfPeople.get(i));
                }
            }
        } else if (strategy.equals("ANY")) {
            if (setOfLines.size() == 0) {
                System.out.println("No matching people found");
                return;
            }
            for (int lineNumber : setOfLines) {
                System.out.println(listOfPeople.get(lineNumber));
            }
        }
    }
}
