package org.search;

import org.search.Strategy.FindStrategy;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * The ComputeSearch class is a utility class for performing
 * searches using different strategies.
 */
public class ComputeSearch {

    /**
     * Interface reference type.
     */
    private final FindStrategy findStrategy;

    /**
     * Constructs a new ComputeSearch object with the specified FindStrategy.
     * @param findStrategy The FindStrategy to use for searches.
     */
    public ComputeSearch(final FindStrategy findStrategy) {
        this.findStrategy = findStrategy;
    }

    /**
     * Performs a search using the specified strategy.
     * @param strategy The search strategy to use.
     * @param listOfPeople The list of people's names to search in.
     * @param sc The Scanner to use for input.
     * @param wordToLineNumber The map of words to line numbers.
     */
    public final void find(final String strategy,
                     final ArrayList<String> listOfPeople,
                     final Scanner sc, final Map<String,
                     ArrayList<Integer>> wordToLineNumber) {
        findStrategy.find(strategy, listOfPeople, sc, wordToLineNumber);
    }

}
