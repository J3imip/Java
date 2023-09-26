package lab2.task1;

import java.util.Arrays;
import java.util.Comparator;

public class Task1Main {
    public static final int rows = 4, cols = 6;
    public static String[] sarr = new String[cols];
    public static int[][] array = new int[rows][cols];
    public static final int limit = 35;

    public static void main(String[] args) {
        try {
            Task1Arrays.fillArray(array);
            Task1Arrays.fillStrings(sarr);
            Arrays.sort(sarr, Comparator.comparingInt(String::length).reversed());
            System.out.println("\nArrays approach:\t\t" + Arrays.toString(sarr));

            Task1Loops.fillArray(array);
            Task1Loops.fillStrings(sarr);
            Task1Loops.quickSortByLength(sarr, 0, sarr.length - 1);
            System.out.println("\nTraditional approach:\t" + Arrays.toString(sarr));
        } catch(OutOfMemoryError | Exception error) {
            error.printStackTrace();
            System.exit(1);
        }
    }
}
