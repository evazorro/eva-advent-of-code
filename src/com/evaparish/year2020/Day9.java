package com.evaparish.year2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9 {

    private static final String INPUT = "src/com/evaparish/year2020/resources/day9_input.txt";
    private static final int PREAMBLE = 25;

    public static void main(String[] args) {
        List<Long> lines = new ArrayList<>();
        try {
            File input = new File(INPUT);
            Scanner scanner = new Scanner(input);
            int index = 0;
            while (scanner.hasNextLine()) {
                lines.add(Long.parseLong(scanner.nextLine()));
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Long invalidNumber = isNumberValid(lines);
        System.out.println("Part 1 answer: " + invalidNumber);

        ArrayList<Long> numbers = solvePart2(lines, invalidNumber);
        Collections.sort(numbers);
        Long answer = numbers.get(0) * numbers.get(numbers.size()-1);
        System.out.println("Part 2 answer: " + answer);


    }

    private static ArrayList<Long> solvePart2(List<Long> lines, Long invalidNumber) {
        ArrayList<Long> numbers = new ArrayList<>();
        Long sum = 0L;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = i; j < lines.size(); j++) {
                Long nextNum = lines.get(j);
                numbers.add(nextNum);
                sum += nextNum;
                if (sum.equals(invalidNumber)) {
                    return numbers;
                }
                if (sum > invalidNumber) {
                    numbers.clear();
                    sum = 0L;
                    break;
                }
            }
        }
        System.out.println("Not found");
        return numbers;
    }

    private static Long isNumberValid (List<Long> lines) {
        for (int i = PREAMBLE; i < lines.size(); i++) {
            Long currentNum = lines.get(i);
            HashSet<Long> previous25 = new HashSet<>();
            boolean isValid = false;
            for (int j = i-PREAMBLE; j < i; j++) {
                // Put the previous 25 #s into a hash
                previous25.add(lines.get(j));
            }
            for (int j = i-PREAMBLE; j < i; j++) {
                Long complement = currentNum - lines.get(j);
                if (previous25.contains(complement)) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                return currentNum;
            }
        }
        return 0L;
    }
}


