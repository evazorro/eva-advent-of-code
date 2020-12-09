package com.evaparish.year2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/* The first 7 characters will either be F or B;
these specify exactly one of the 128 rows on the plane (numbered 0 through 127).
Each letter tells you which half of a region the given seat is in.
Start with the whole list of rows; the first letter indicates whether the seat is
in the front (0 through 63) or the back (64 through 127).
The next letter indicates which half of that region the seat is in,
and so on until you're left with exactly one row.

For example, consider just the first seven characters of FBFBBFFRLR:

Start by considering the whole range, rows 0 through 127.
F means to take the lower half, keeping rows 0 through 63.
B means to take the upper half, keeping rows 32 through 63.
F means to take the lower half, keeping rows 32 through 47.
B means to take the upper half, keeping rows 40 through 47.
B keeps rows 44 through 47.
F keeps rows 44 through 45.
The final F keeps the lower of the two, row 44.
The last three characters will be either L or R;
these specify exactly one of the 8 columns of seats on the plane (numbered 0 through 7).
The same process as above proceeds again, this time with only three steps.
L means to keep the lower half, while R means to keep the upper half.

For example, consider just the last 3 characters of FBFBBFFRLR:

Start by considering the whole range, columns 0 through 7.
R means to take the upper half, keeping columns 4 through 7.
L means to take the lower half, keeping columns 4 through 5.
The final R keeps the upper of the two, column 5.
So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.

Every seat also has a unique seat ID: multiply the row by 8, then add the column.
In this example, the seat has ID 44 * 8 + 5 = 357.
* */
public class Day5 {

    private static final String INPUT = "src/com/evaparish/year2020/resources/day5_input.txt";

    public static void main(String[] args) {

        List<String> lines = new ArrayList<>();
        HashSet<Integer> seatIds = new HashSet<>();
        int highestSeatId = 0;
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
        for (String line : lines) {
            int rowNum = findSeat(line.substring(0,7), 0, 0, 127);
            int columnNum = findSeat(line.substring(7,10), 0, 0, 7);
            int seatId = rowNum*8 + columnNum;
            seatIds.add(seatId);
            //System.out.println("Row: " + rowNum + ", column: " + columnNum + ", seat ID: " + seatId);
            if (seatId > highestSeatId) {
                highestSeatId = seatId;
            }
        }
        System.out.println("Part 1 answer: " + highestSeatId);

        int mySeat = 0;
        for (int i = 12; i < highestSeatId; i++) {
            if (!seatIds.contains(i) && seatIds.contains(i+1)) {
                System.out.println("Part 2 answer: " + i);
                break;
            }
        }

    }

    private static int findSeat(String seat, int index, int min, int max) {
        if (index == seat.length()) {
            char letter = seat.charAt(index-1);
            if (letter == 'F' || letter == 'L') {
                return min;
            } else if (letter == 'B' || letter == 'R') {
                return max;
            }

        }
        char letter = seat.charAt(index);
        if (letter == 'F' || letter == 'L') {
            return (findSeat(seat, index+1, min, max-((max-min)/2)-1));
        } else if (letter == 'B' || letter == 'R') {
            return(findSeat(seat, index+1, min+((max-min)/2)+1, max));
        }
        return -1;
    }

}