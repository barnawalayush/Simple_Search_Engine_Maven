package org.search.Strategy;

import java.util.*;

public class FindAnyNone implements FindStrategy {
    @Override
    public void find(String strategy, ArrayList<String> listOfPeople, Scanner sc, Map<String, ArrayList<Integer>> wordToLineNumber) {

        int total_people = listOfPeople.size();

        HashSet<Integer> setOfLines = new HashSet<>();

        System.out.println("Enter a name or email to search all suitable people.");
        String wordsToSearch = sc.nextLine();

        String[] listOfWordsToSearch = wordsToSearch.split(" ");

        for (String word_to_search : listOfWordsToSearch) {
            if (wordToLineNumber.get(word_to_search.toLowerCase()) != null) {
                List<Integer> list_of_line_no_having_word = wordToLineNumber.get(word_to_search.toLowerCase());
                for (int line_number : list_of_line_no_having_word) {
                    setOfLines.add(line_number);
                }
            }
        }

        if (strategy.equals("NONE")) {
            for (int i = 0; i < total_people; i++) {
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

        return;

    }
}
