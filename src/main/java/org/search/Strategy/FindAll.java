package search.Strategy;

import java.util.*;

public class FindAll implements FindStrategy {

    @Override
    public void find(String strategy, ArrayList<String> listOfPeople, Scanner sc, Map<String, ArrayList<Integer>> wordToLineNumber) {

        HashSet<Integer> setOfLines = new HashSet<>();

        HashMap<Integer, Integer> mappingOfLines = new HashMap<>();

        System.out.println("Enter a name or email to search all suitable people.");
        String wordsToSearch = sc.nextLine();

        String[] listOfWordsToSearch = wordsToSearch.split(" ");

        int total_word = listOfWordsToSearch.length;

        for (String word_to_search : listOfWordsToSearch) {
            if (wordToLineNumber.get(word_to_search.toLowerCase()) == null) {
                System.out.println("No matching people found");
                return;
            }
            List<Integer> list_of_line_no_having_word = wordToLineNumber.get(word_to_search.toLowerCase());
            for (int line_number : list_of_line_no_having_word) {
                if (mappingOfLines.get(line_number) == null) {
                    mappingOfLines.put(line_number, 1);
                } else {
                    int x = mappingOfLines.get(line_number);
                    mappingOfLines.put(line_number, x + 1);
                }
            }
        }

        for (int key : mappingOfLines.keySet()) {
            int numberOfOccurrence = mappingOfLines.get(key);
            if (numberOfOccurrence == total_word) System.out.println(listOfPeople.get(key));
        }

        return;
    }
}
