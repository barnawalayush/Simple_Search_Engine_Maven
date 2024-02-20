package search;

import search.Strategy.FindStrategy;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class ComputeSearch {

    FindStrategy findStrategy;

    ComputeSearch(FindStrategy findStrategy) {
        this.findStrategy = findStrategy;
    }

    public void find(String strategy, ArrayList<String> listOfPeople, Scanner sc, Map<String, ArrayList<Integer>> wordToLineNumber) {
        findStrategy.find(strategy, listOfPeople, sc, wordToLineNumber);
    }

}
