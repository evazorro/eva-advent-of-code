package com.evaparish.year2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {

    private static final String INPUT = "src/com/evaparish/year2020/resources/day6_input.txt";

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        try {
            File input = new File(INPUT);
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String tmpAnswer = "";
        int tmpGroupSize = 0;
        int allYesAnswers = 0;
        int allSharedYesAnswers = 0;
        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            if (i == (lines.size()-1)) {
                tmpAnswer = tmpAnswer.concat(currentLine);
                tmpGroupSize++;
                Group group = new Group(tmpAnswer, tmpGroupSize);
                allYesAnswers += group.allYesAnswers();
                allSharedYesAnswers += group.sharedYesAnswers();
            } else if (currentLine.equals("")) {
                Group group = new Group(tmpAnswer, tmpGroupSize);
                allYesAnswers += group.allYesAnswers();
                allSharedYesAnswers += group.sharedYesAnswers();
                tmpAnswer = "";
                tmpGroupSize = 0;
            } else {
                tmpAnswer = tmpAnswer.concat(currentLine);
                tmpGroupSize++;
            }
        }

        System.out.println("Part 1 answer: " + allYesAnswers);
        System.out.println("Part 2 answer: " + allSharedYesAnswers);

    }


    private static class Group {
        String yesAnswers;
        int groupSize;

        public Group(String input, int size) {
            this.yesAnswers = input;
            this.groupSize = size;
        }

        private int allYesAnswers() {
            Set<Character> yesSet = new HashSet<>();
            for (int i = 0; i < yesAnswers.length(); i++) {
                yesSet.add(yesAnswers.charAt(i));
            }
            return yesSet.size();
        }

        private int sharedYesAnswers() {
            // for each string of answers, put the count per letter in a map
            Map<Character, Integer> yesMap = new HashMap<>();
            for (int i = 0; i < yesAnswers.length(); i++) {
                char letter = yesAnswers.charAt(i);
                if (yesMap.containsKey(letter)) {
                    yesMap.put(letter, yesMap.get(letter) + 1);
                } else {
                    yesMap.put(letter, 1);
                }
            }
            // compare the count of each letter to group size
            int sharedYesAnswers = 0;
            for (Map.Entry<Character, Integer> entry : yesMap.entrySet()) {
                if (entry.getValue() == groupSize) {
                    sharedYesAnswers++;
                }
            }
            return sharedYesAnswers;
        }
    }

}