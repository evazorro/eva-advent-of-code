package com.evaparish.year2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class day1 {

    public static void multiplyEntries(Integer entry1, Integer entry2) {
        System.out.println(entry1 * entry2);
    }

    public static void findComplement(Set<Integer> entries) {
        for (Integer entry : entries) {
            int toFind = 2020 - entry;
            if (entries.contains(toFind)) {
                multiplyEntries(entry, toFind);
                break;
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
