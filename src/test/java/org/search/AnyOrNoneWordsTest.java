package org.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnyOrNoneWordsTest {

    @Test
    void isCalled() {
        AnyOrNoneWords anyOrNoneWords = new AnyOrNoneWords();

        assertEquals(true, anyOrNoneWords.isCalled());
    }


}