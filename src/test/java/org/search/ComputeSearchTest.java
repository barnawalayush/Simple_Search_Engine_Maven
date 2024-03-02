package org.search;

import org.junit.jupiter.api.Test;
import org.search.Strategy.FindStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ComputeSearchTest {

    FindStrategy findStrategy;

    @Test
    void find() {

        ComputeSearch computeSearch = new ComputeSearch(findStrategy);

//        computeSearch.find("ALL", new ArrayList<String>(), new Scanner(System.in),new HashMap<>());

        assertEquals(true, computeSearch.isGetCalled());
    }
}