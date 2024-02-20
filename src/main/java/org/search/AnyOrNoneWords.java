package search;

import search.Strategy.FindAnyNone;
import search.Strategy.FindStrategy;

public class AnyOrNoneWords extends ComputeSearch {

    AnyOrNoneWords() {
        super(new FindAnyNone());
    }

}
