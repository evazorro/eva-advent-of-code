package com.evaparish.year2020;

import java.util.*;
import java.util.stream.Collectors;

public class Day15 {

    private static final String INPUT = "14,3,1,0,9,5";

    // ArrayList to keep track of all numbers,
    // plus a Map with each number as key and last position where it was found as value
    public static void main(String[] args) {

        // create Map and List and handle initial input
        Map<Integer, Integer> numbersWithPositions = new HashMap<>();
        List<Integer> gameHistory =
                Arrays.stream(INPUT.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
        for (int i = 0; i < gameHistory.size(); i++) {
            updateNumberLastPosition(gameHistory.get(i), i+1, numbersWithPositions);
        }

        int answer = speakNumber(gameHistory, numbersWithPositions, 30000000);
        System.out.println("Part 1 answer: " + answer);

    }

    private static int speakNumber(List<Integer> gameHistory, Map<Integer, Integer> numbersWithPositions, int nthNumber) {
        int nextNumber = -1;
        for (int i = gameHistory.size(); i < nthNumber; i++) {
            int turn = i+1;
            int previousTurnIndex = gameHistory.size();
            int previousNumber = gameHistory.get(previousTurnIndex-1);
            // hard-coded the 6 to deal with the last of the starting numbers lolol
            if (i != 6 && numbersWithPositions.containsKey(previousNumber)) {
                int lastSeenAtIndex = numbersWithPositions.get(previousNumber);
                nextNumber = previousTurnIndex - lastSeenAtIndex;
            } else {
                nextNumber = 0;
            }
            gameHistory.add(nextNumber);
            updateNumberLastPosition(previousNumber, previousTurnIndex, numbersWithPositions);
        }
        return nextNumber;
    }

    private static void updateNumberLastPosition(int number, int turn, Map<Integer, Integer> numbersWithPositions) {
        if (numbersWithPositions.containsKey(number)) {
            numbersWithPositions.replace(number, turn);
        } else {
            numbersWithPositions.put(number, turn);
        }
    }

}
