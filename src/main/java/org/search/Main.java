package org.search;

import org.search.Utils.OptionSelected;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * The Main class is the entry point for the application.
 * It provides a menu-driven interface for searching and
 * printing people's names based on a specified search strategy.
 */
public final class Main {

    /**
     * constructor.
     */
    private Main() {

    }

    /**
     * The exit field is used to control the main loop of the application. I
     * t is set to "1" initially and is set to "0"
     * when the user selects the "Exit" option.
     */
    private static String exit = "1";
    /**
     * The option_selected field is used to store the user's selected
     * option from the menu. It is set to -1 initially
     * and is updated when the user selects an option.
     */
    private static int optionSelected = -1;

    /**
     * The main method is the entry point for the application.
     * It initializes the necessary data structures,
     * reads data from a file, and provides a menu-driven
     * interface for searching and printing people's names.
     * @param args The command-line arguments.
     */

    public static void main(final String[] args) {

     /**
     * Mapping of words to line number in which they are occurring
     */
        Map<String, ArrayList<Integer>> wordToLineNumber = new HashMap<>();

        Scanner sc = new Scanner(System.in, "UTF-8");


        String inputFileName = null;
        for (int i = 0; i < args.length; i = i + 2) {
            if (args[i].equals("--data")) {
                inputFileName = args[i + 1];
            }
        }

        if (inputFileName.contains("../")) {
            System.out.println("Invalid file name");
            return;
        }

//    ***********   Array list contains all line   ***********
        ArrayList<String> listOfPeople = new ArrayList<>();

        try {

            if (inputFileName.contains("../")) {
                System.out.println("Invalid file name");
                return;
            }

            String content = new String(Files.readAllBytes(Paths
                    .get(inputFileName)), StandardCharsets.UTF_8);
            String[] allPeople = content.split("\n");

            for (int i = 0; i < allPeople.length; i++) {
                listOfPeople.add(allPeople[i]);

                String[] eachWord = allPeople[i].split(" ");

                for (String word : eachWord) {
                    if (wordToLineNumber.get(word.
                            toLowerCase()) == null) {
                        wordToLineNumber.put(word.toLowerCase(),
                                new ArrayList<>());
                    }
                    wordToLineNumber.get(word.toLowerCase()).add(i);
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
                    optionSelected = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.print("Please enter number among 0,1,2: ");
                    continue;
                }
            }

            if (optionSelected >= OptionSelected.OPTION4.getOption()
                    || optionSelected < 0) {
                System.out.println("Incorrect option! Try again.");
                continue;
            } else if (optionSelected == 1) {
                System.out.println("Select a matching strategy: "
                        + "ALL, ANY, NONE");

                String strategySelected = sc.nextLine();

                ComputeSearch computeSearch;
                if (strategySelected.equals("ALL")) {
                    computeSearch = new AllWords();
                    computeSearch.find("ALL", listOfPeople,
                            sc, wordToLineNumber);
                } else if (strategySelected.equals("ANY")) {
                    computeSearch = new AnyOrNoneWords();
                    computeSearch.find("ANY", listOfPeople,
                            sc, wordToLineNumber);
                } else {
                    computeSearch = new AnyOrNoneWords();
                    computeSearch.find("NONE", listOfPeople,
                            sc, wordToLineNumber);
                }
            } else if (optionSelected == 2) {
                printAllPeople(listOfPeople);
            } else {
                System.out.println("Bye!");
                exit = "0";
            }
        }

    }

    /**
     * Prints all people's names in the list.
     * @param listOfPeople The list of people's names to print.
     */
    private static void printAllPeople(final ArrayList<String> listOfPeople) {

        for (String onePersonDetail : listOfPeople) {
            System.out.println(onePersonDetail);
        }
    }

//    /**
//     * Method to validate input file nameMethod to validate input file name
//     */
//    private static String validateInput(String inputFileName) {
//        // Check if inputFileName contains path traversal characters
//        if (inputFileName.contains("../")) {
//            // Reject file name if it contains path traversal characters
//            return null;
//        }
//        // Return validated file name
//        return inputFileName;
//    }

}
