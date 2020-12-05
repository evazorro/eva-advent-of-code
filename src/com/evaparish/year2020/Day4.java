package com.evaparish.year2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {

    private static final String INPUT = "src/com/evaparish/year2020/resources/day4_input.txt";

    /*
    * IMPORTANT DETAILS:
    * Passport fields can be separated by spaces OR newlines
    * Individual passports are separated by a blank line
    * `cid` is optional; you need the other 7 fields
    * TASK: count the number of valid passports
    * */

    public static void main(String[] args) {
        List<String> lines = new ArrayList<String>();
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

        List<String> passports = new ArrayList<String>();
        String tmpPassport = "";
        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            if (currentLine.equals("")) {
                passports.add(tmpPassport);
                tmpPassport = "";
            } else {
                tmpPassport = tmpPassport.concat(currentLine).concat(" ");
            }

        }

        int answer = countValidPassports(passports);
        System.out.println("The number of valid passports is: " + answer);

    }

    private static int countValidPassports(List<String> passports) {
        int numValidPassports = 0;
        for (String passport : passports) {
            if (passport.contains("byr:")
                    && passport.contains("iyr:")
                    && passport.contains("eyr:")
                    && passport.contains("hgt:")
                    && passport.contains("hcl:")
                    && passport.contains("ecl:")
                    && passport.contains("pid:")
            ) {
                numValidPassports++;
            }
        }
        return numValidPassports;
    }

    //    static class Passport {
//
//        Map<String, Boolean> fields = new HashMap<>();
//        Set<String> requiredFields = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
//
//        static Passport fromInput(String input) {
//
//        }
//    }
//


}
