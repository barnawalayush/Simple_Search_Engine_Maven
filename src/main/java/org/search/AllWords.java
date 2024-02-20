package search;

import search.Strategy.FindAll;
import search.Strategy.FindStrategy;

public class AllWords extends ComputeSearch {

    AllWords() {
        super(new FindAll());
    }

}
