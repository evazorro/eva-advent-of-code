package com.evaparish.year2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {
    /*
    * Put it into a 2-D array but repeat each row a few times to the right??
    * Look up the value at index [i+1][j+3]
    * */
    private static int calculateNumTrees(List<String> tobogganMap) {
        int numTrees = 0;
        int currentCol = 0;
        for (int i = 0; i < tobogganMap.size(); i++) {
            char treeOrSquare = tobogganMap.get(i).charAt(currentCol);
            if (treeOrSquare == '#') {
                numTrees ++;
            }
            currentCol += 3;
        }
        return numTrees;
    }

    private static final String INPUT = "src/com/evaparish/year2020/resources/day3_input.txt";

    public static void main(String[] args) {

        // (I started trying to do it as a stream and then got confused)
//        Path path = Paths.get(INPUT);
//        try (Stream<String> stream = Files.lines(path)) {
//            ArrayList<String> tobogganMap = stream.map().collect(Collectors.toList())
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        ArrayList<String> tobogganMap = new ArrayList<String>();

        try {
            File input = new File(INPUT);
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String combinedMapRow = "";
                for (int i = 0; i < 100; i++) {
                    combinedMapRow += currentLine;
                }
                tobogganMap.add(combinedMapRow);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int numTrees = calculateNumTrees(tobogganMap);
        System.out.println("The number of trees for that path is: " + numTrees);

    }

}
