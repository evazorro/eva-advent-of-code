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

        // I think I want an array of sets, count the # of items in each set, then add
        List<GroupAnswer> allGroups = new ArrayList<>();
        String tmpAnswer = "";
        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            if (i == (lines.size()-1)) {
                tmpAnswer = tmpAnswer.concat(currentLine);
                GroupAnswer answer = new GroupAnswer(tmpAnswer);
                allGroups.add(answer);
            } else if (currentLine.equals("")) {
                GroupAnswer answer = new GroupAnswer(tmpAnswer);
                allGroups.add(answer);
                tmpAnswer = "";
            } else {
                tmpAnswer = tmpAnswer.concat(currentLine);
            }
        }
        int allAnswersCount = 0;
        for (int i = 0; i < allGroups.size(); i++) {
            GroupAnswer answer = allGroups.get(i);
            System.out.println(answer);
            int count = answer.yesAnswers.size();
            allAnswersCount += count;
        }
        System.out.println(allAnswersCount);
    }

    private static class GroupAnswer {
        Set<Character> yesAnswers = new HashSet<>();

        public GroupAnswer(String input) {
            for (int i = 0; i < input.length(); i++) {
                yesAnswers.add(input.charAt(i));
            }
        }
    }
}