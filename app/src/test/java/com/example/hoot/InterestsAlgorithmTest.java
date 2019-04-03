package com.example.hoot;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InterestsAlgorithmTest {

    private InterestsAlgorithm algorithm;
    private List<String> myInterests;
    private List<String> theirInterests;

    @Before
    public void beforeEachTestMethod() {
        myInterests = new ArrayList();
        theirInterests = new ArrayList();
        this.algorithm = new InterestsAlgorithm();
    }

    @Test
    public void testSameInterestsGive100PercentAnd2Matches() {
        List<String> interests = Arrays.asList("Knitting", "Books");
        myInterests.addAll(interests);
        theirInterests.addAll(interests);
        assertEquals(100, algorithm.calculatePercentageMatch(myInterests, theirInterests), 0.1);
        assertEquals(2, algorithm.calculateMatch(myInterests, theirInterests));
    }

    @Test
    public void testDifferentInterestsGive0PercentAnd0Matches() {
        List<String> interests1 = Arrays.asList("Knitting", "Books");
        List<String> interests2 = Arrays.asList("Puzzles", "Board Games");
        myInterests.addAll(interests1);
        theirInterests.addAll(interests2);
        assertEquals(0, algorithm.calculatePercentageMatch(myInterests, theirInterests), 0.1);
        assertEquals(0, algorithm.calculateMatch(myInterests, theirInterests));
    }

    @Test
    public void testOneOfThreeSameInterestsGive30PercentAnd1match() {
        List<String> interests1 = Arrays.asList("Knitting", "Books", "Puzzles");
        List<String> interests2 = Arrays.asList("Books", "Board Games", "Card Games");
        myInterests.addAll(interests1);
        theirInterests.addAll(interests2);
        assertEquals(33.0, algorithm.calculatePercentageMatch(myInterests, theirInterests), 0.1);
        assertEquals(1, algorithm.calculateMatch(myInterests, theirInterests));
    }


}