package org.search.Strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public interface FindStrategy {

    public void find(String strategy, ArrayList<String> listOfPeople, Scanner sc, Map<String, ArrayList<Integer>> wordToLineNumber);

}
