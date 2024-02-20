package org.search;

import org.search.Strategy.FindAnyNone;

/**
 * The AnyOrNoneWords class is a subclass of ComputeSearch that uses
 * the FindAnyNone strategy to find any or none
 * of the occurrences of a given word in a list of people's names.
 */

public class AnyOrNoneWords extends ComputeSearch {

    /**
     * Constructs a new AnyOrNoneWords object with the FindAnyNone strategy.
     */
    AnyOrNoneWords() {
        super(new FindAnyNone());
    }

}
