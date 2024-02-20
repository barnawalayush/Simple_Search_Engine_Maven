package org.search.Strategy;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * The FindStrategy interface defines a contract for a
 * strategy to find occurrences of a given word
 * in a list of people's names, using a provided Scanner
 * and a map of words to line numbers.
 * Implementations of this interface should provide a way
 * to search for occurrences of the given
 * word in the list of people's names, and return a map of
 * words to line numbers indicating where
 * the word was found.
 */
public interface FindStrategy {

    /**
     * Finds occurrences of a given word in a list of people's names.
     *
     * @param strategy The strategy pattern.
     * @param listOfPeople The list of people's names to search in.
     * @param sc The Scanner to use for input.
     * @param wordToLineNumber The map of words to line numbers.
     */

    void find(String strategy, ArrayList<String> listOfPeople, Scanner sc,
                     Map<String, ArrayList<Integer>> wordToLineNumber);

}
