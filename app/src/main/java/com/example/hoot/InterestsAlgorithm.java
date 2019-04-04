package com.example.hoot;

import java.util.List;

public class InterestsAlgorithm {

    private float percentageMatch;

    public int calculatePercentageMatch(List myInterests, List theirInterests) {
        int numberOfMatchedInterests = calculateMatch(myInterests, theirInterests);
        float matches = (float) numberOfMatchedInterests;
        percentageMatch = (matches / myInterests.size()) * 100;
        return Math.round(percentageMatch);
    }

    public int calculateMatch(List myInterests, List theirInterests) {
        int numberOfMatchedInterests = 0;
        for (Object myInterest : myInterests) {
            for (Object theirInterest : theirInterests) {
                if (myInterest == theirInterest) {
                    numberOfMatchedInterests += 1;
                }
            }
        }
        return numberOfMatchedInterests;
    }


}
