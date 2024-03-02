package org.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AllWordsTest {

    @Test
    void testAllWOrds(){
        AllWords allWords = new AllWords();

        assertEquals(true, allWords.isCalled());
    }

}