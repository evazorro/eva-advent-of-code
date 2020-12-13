package com.evaparish.year2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day13 {

    private static final String INPUT = "src/com/evaparish/year2020/resources/day13_input.txt";

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

        int earliestDepTime = Integer.parseInt(lines.get(0));
        List<Integer> busIds =
                Arrays.stream(lines.get(1).split(","))
                .filter(s -> !s.equals("x"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println("Part 1 answer: " + solvePart1(earliestDepTime, busIds));
    }

    private static int solvePart1(int earliestDepTime, List<Integer> busIds) {
        int firstBestBusId = -1;
        int firstBestBusTime = Integer.MAX_VALUE;
        while (firstBestBusId < 0) {
            for (Integer busId : busIds) {
                int multiplier = (earliestDepTime / busId) + 1;
                int nextTime = multiplier * busId;
                if (nextTime < firstBestBusTime) {
                    firstBestBusId = busId;
                    firstBestBusTime = nextTime;
                }
            }
        }
        return(firstBestBusId * (firstBestBusTime - earliestDepTime));
    }

}
