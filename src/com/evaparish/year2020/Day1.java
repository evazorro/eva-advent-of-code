package com.evaparish.year2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Day1 {

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
            for (Integer secondEntry : entries) {
                Integer thirdEntry = remainingTwoThirds - secondEntry;
                if (entries.contains(thirdEntry)) {
                    multiplyEntries(entry, secondEntry, thirdEntry);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {

        Set<Integer> entries = new HashSet<Integer>();
        try {
            File input = new File("src/com/evaparish/year2020/resources/day1_input.txt");
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
