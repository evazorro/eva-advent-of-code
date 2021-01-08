package com.evaparish.year2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {

    private static final String INPUT = "src/com/evaparish/year2020/resources/day12_input.txt";


    public static void main(String[] args) {

        List<Instruction> listOfInstructions =
                Arrays.stream(INPUT.split("\n"))
                        .map(l -> new Instruction(l.substring(0, 1), Integer.parseInt(l.substring(1))))
                        .collect(Collectors.toList());

        System.out.println(listOfInstructions);

    }

    private static class Instruction {
        String action;
        int value;

        public Instruction(String action, int value) {
            this.action = action;
            this.value = value;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

//    private static void moveShip() {
//
//        for (char instruction : listOfInstructions) {
//            switch (instruction) {
//
//            }
//
//        }
//
//    }

}
