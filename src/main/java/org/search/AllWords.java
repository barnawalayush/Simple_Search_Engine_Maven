package org.search;

import org.search.Strategy.FindAll;
import org.search.Strategy.FindStrategy;

public class AllWords extends ComputeSearch {

    AllWords() {
        super(new FindAll());
    }

}
