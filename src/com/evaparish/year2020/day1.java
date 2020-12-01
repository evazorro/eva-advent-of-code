package com.evaparish.year2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class day1 {

    public static void multiplyEntries(Integer... entries) {
        Integer total = 1;
        for (Integer entry : entries) {
            total = total * entry;
        }
        System.out.println(total);
    }

    public static void findComplement(Set<Integer> entries) {
        for (Integer entry : entries) {
            int remainingTwoThirds = 2020 - entry;
            //entries.remove(entry); // so you don't check it multiple times? -- this didn't work, and I don't know how to make it stop after finding the answer once
            for (Integer secondEntry : entries) {
                Integer thirdEntry = remainingTwoThirds - secondEntry;
                if (entries.contains(thirdEntry)) {
                    multiplyEntries(entry, secondEntry, thirdEntry);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {

        Set<Integer> entries = new HashSet<Integer>();
        try {
            File input = new File("/Users/eparish/git/eva-advent-of-code/src/com/evaparish/year2020/resources/day1_input.txt");
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                Integer i = Integer.parseInt(currentLine);
                entries.add(i);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        findComplement(entries);

    }

}
