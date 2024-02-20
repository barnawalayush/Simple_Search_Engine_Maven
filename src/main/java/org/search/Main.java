package org.search;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    private static String exit = "1";
    private static int option_selected = -1;

    public static void main(String[] args) {

// ***********   Mapping of words to line number in which they are occurring   ***********
        Map<String, ArrayList<Integer>> word_to_line_number = new HashMap<>();

        Scanner sc = new Scanner(System.in);


        String input_file_name = null;
        for (int i = 0; i < args.length; i = i + 2) {
            if (args[i].equals("--data")) {
                input_file_name = args[i + 1];
            }
        }


//    ***********   Array list contains all line   ***********
        ArrayList<String> list_of_people = new ArrayList<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get(input_file_name)));
            String[] all_people = content.split("\n");

            for (int i = 0; i < all_people.length; i++) {
                list_of_people.add(all_people[i]);

                String[] each_word = all_people[i].split(" ");

                for (String word : each_word) {
                    if (word_to_line_number.get(word.toLowerCase()) == null) {
                        word_to_line_number.put(word.toLowerCase(), new ArrayList<>());
                    }
                    word_to_line_number.get(word.toLowerCase()).add(i);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!exit.equals("0")) {

            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");

            while (true) {
                try {
                    option_selected = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.print("Please enter number among 0,1,2: ");
                    continue;
                }
            }

            if (option_selected >= 3 || option_selected < 0) {
                System.out.println("Incorrect option! Try again.");
                continue;
            } else if (option_selected == 1) {
                System.out.println("Select a matching strategy: ALL, ANY, NONE");

                String strategy_selected = sc.nextLine();

                ComputeSearch computeSearch;
                if (strategy_selected.equals("ALL")) {
                    computeSearch = new AllWords();
                    computeSearch.find("ALL", list_of_people, sc, word_to_line_number);
                } else if (strategy_selected.equals("ANY")) {
                    computeSearch = new AnyOrNoneWords();
                    computeSearch.find("ANY", list_of_people, sc, word_to_line_number);
                } else {
                    computeSearch = new AnyOrNoneWords();
                    computeSearch.find("NONE", list_of_people, sc, word_to_line_number);
                }
            } else if (option_selected == 2) {
                print_all_people(list_of_people);
            } else {
                System.out.println("Bye!");
                exit = "0";
            }
        }

    }


    private static void print_all_people(ArrayList<String> list_of_people) {

        for (String one_person_detail : list_of_people) {
            System.out.println(one_person_detail);
        }
    }

}
