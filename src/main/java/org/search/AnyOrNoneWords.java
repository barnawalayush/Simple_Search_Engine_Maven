package org.search;

import org.search.Strategy.FindAnyNone;
import org.search.Strategy.FindStrategy;

public class AnyOrNoneWords extends ComputeSearch {

    AnyOrNoneWords() {
        super(new FindAnyNone());
    }

}
