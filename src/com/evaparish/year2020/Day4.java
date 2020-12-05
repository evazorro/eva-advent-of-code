package com.evaparish.year2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {

    // Disclaimer: I modeled this after Su's solution (aka copied a lot of it) for purposes of learning

    private static final String INPUT = "src/com/evaparish/year2020/resources/day4_input.txt";

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

        List<String> passportStrings = new ArrayList<String>();
        String tmpPassport = "";
        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            if (currentLine.equals("") || i == (lines.size()-1)) {
                passportStrings.add(tmpPassport);
                tmpPassport = "";
            } else {
                tmpPassport = tmpPassport.concat(currentLine).concat(" ");
            }
        }

        // create a Passport for each string in the list
        List<Passport> passports = new ArrayList<>();
        for (String passport : passportStrings) {
            Passport newPass = new Passport(passport);
            passports.add(newPass);
        }

        long count = passports.stream()
                .filter(Passport::hasAllRequiredFields)
                .filter(Passport::hasValidFields)
                .count();
        System.out.println(String.valueOf(count));

    }

    private static class Passport {

        Map<String, Boolean> fields = new HashMap<>();
        Set<String> requiredFields = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

        public Passport(String input) {
            Matcher matcher = Pattern.compile(String.format("(?<field>(\\S+)):(?<value>(\\S+))"))
                    .matcher(input);

            while (matcher.find()) {
                String f = matcher.group("field");
                String v = matcher.group("value");
                fields.put(f, validate(f, v));
            }
        }

        public boolean hasAllRequiredFields() {
            return fields.keySet().containsAll(requiredFields);
        }

        public boolean hasValidFields() {
            return fields.values().stream().allMatch(Boolean::booleanValue);
        }

        private boolean validate(String name, String value) {
            switch (name) {
                case "byr":
                    return validateYear(Integer.valueOf(value), 1920, 2002);
                case "iyr":
                    return validateYear(Integer.valueOf(value), 2010, 2020);
                case "eyr":
                    return validateYear(Integer.valueOf(value), 2020, 2030);
                case "hgt":
                    return validateHeight(value);
                case "hcl":
                    return validateHairColor(value);
                case "ecl":
                    return validateEyeColor(value);
                case "pid":
                    return validatePID(value);

                default:
                    return true;
            }
        }

        private boolean validatePID(String value) {
            return value.matches("\\d{9}");
        }

        private boolean validateEyeColor(String value) {
            return value.matches("amb|blu|brn|gry|grn|hzl|oth");
        }

        private boolean validateHairColor(String value) {
            return value.matches("#[a-f0-9]{6}");
        }

        private boolean validateHeight(String value) {
            Integer numericalValue;

            try {
                numericalValue = Integer.valueOf(value.substring(0, value.length() - 2));
            } catch (NumberFormatException e) {
                return false;
            }

            String unit = value.substring(value.length() - 2);
            if (unit.equals("cm")) {
                return numericalValue >= 150 & numericalValue <= 193;
            }
            if (unit.equals("in")) {
                return numericalValue >= 59 & numericalValue <= 76;
            }
            return false;
        }

        private boolean validateYear(Integer year, int min, int max) {
            return year >= min & year <= max;
        }

    }

}
