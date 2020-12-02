package com.evaparish.year2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {

    public static int findValidPasswordsPart2(List<String> passwordsAndPolicies) {
        int validPasswordsCount = 0;
        for (String entry : passwordsAndPolicies) {
            String[] splitEntry = entry.split(":");
            String policy = splitEntry[0];
            String password = splitEntry[1].trim();

            String policyRange = policy.split(" ")[0];
            Character policyChar = policy.split(" ")[1].toCharArray()[0];

            Integer policyPositionOne = Integer.parseInt(policyRange.split("-")[0]);
            Integer policyPositionTwo = Integer.parseInt(policyRange.split("-")[1]);

            boolean isPasswordValid = false;
            // if it enters BOTH if statements, the password is not valid
            if (Character.compare(policyChar, password.charAt(policyPositionOne-1)) == 0) {
                isPasswordValid = true;
                if (Character.compare(policyChar, password.charAt(policyPositionTwo-1)) == 0) {
                    isPasswordValid = false;
                }
            } else if (Character.compare(policyChar, password.charAt(policyPositionTwo-1)) == 0) {
                isPasswordValid = true;
            }
            if (isPasswordValid == true) {
                validPasswordsCount ++;
            }
        }
        return validPasswordsCount;
    }

    public static int findValidPasswordsPart1(List<String> passwordsAndPolicies) {
        int validPasswordsCount = 0;
        for (String entry : passwordsAndPolicies) {
            String[] splitEntry = entry.split(":");
            String policy = splitEntry[0];
            String password = splitEntry[1].trim();

            String policyRange = policy.split(" ")[0];
            Character policyChar = policy.split(" ")[1].toCharArray()[0];

            Integer policyRangeStart = Integer.parseInt(policyRange.split("-")[0]);
            Integer policyRangeEnd = Integer.parseInt(policyRange.split("-")[1]);

            int charCounter = 0;
            for (int i = 0; i < password.length(); i++) {

                if (Character.compare(policyChar, password.charAt(i)) == 0) {
                    charCounter ++;
                }
            }
            if (charCounter >= policyRangeStart && charCounter <= policyRangeEnd) {
                validPasswordsCount++;
            }
        }
        return validPasswordsCount;
    }


    public static void main(String[] args) {

        List<String> passwordsAndPolicies = new ArrayList<String>();

        try {
            File input = new File("src/com/evaparish/year2020/resources/day2_input.txt");
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                passwordsAndPolicies.add(currentLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("Part 1 answer: " + findValidPasswordsPart1(passwordsAndPolicies));
        System.out.println("Part 2 answer: " + findValidPasswordsPart2(passwordsAndPolicies));
    }

}
