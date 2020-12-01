package com.evaparish.year2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day1 {

    // to find the fuel required for a module, take its mass,
    // divide by three, round down, and subtract 2.
    private static int calculateFuel(int mass) {
        // if < 6, return 0
        if (mass < 6) {
            return 0;
        } else {
            // otherwise fuel calculation
            int fuel = mass / 3 - 2;
            // then calculate fuel for fuel
            return (fuel + calculateFuel(fuel));
        }
    }

    private static int addFuel(List<Integer> masses) {
        int fuel = 0;
        int totalFuel = 0;
        for (Integer i : masses) {
            fuel = calculateFuel(i);
            totalFuel += (fuel);
        }
        return totalFuel;
    }


    public static void main(String[] args) {

        List<Integer> masses = new ArrayList<Integer>();

        try {
            File input = new File("src/com/evaparish/year2019/resources/day1_input.txt");
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                Integer i = Integer.parseInt(currentLine);
                masses.add(i);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        System.out.println(addFuel(masses));

    }

}
