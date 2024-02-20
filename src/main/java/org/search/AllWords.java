package org.search;

import org.search.Strategy.FindAll;

/**
 * The AllWords class is a subclass of ComputeSearch that uses
 * the FindAll strategy to find all occurrences
 * of a given word in a list of people's names.
 */
public class AllWords extends ComputeSearch {

    /**
     * Constructs a new AllWords object with the FindAll strategy.
     */
    AllWords() {
        super(new FindAll());
    }

    //hello hello ayush here

}
