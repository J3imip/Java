package lab2.task1;

import java.util.Arrays;
import java.util.Random;

/**
 * Task1Array class implements Arrays lib approach to get strings array
 */
public class Task1Arrays {
    /**
     * Fills two-dimensional int array with random odd numbers in range [0 - limit]
     *
     * @param array two-dimensional int array
     */
    public static void fillArray(int[][] array) {
        if (array[0][0] != 0) return;
        Random random = new Random();

        Arrays.parallelSetAll(array, i -> {
            int[] row = new int[Task1Main.cols];
            Arrays.setAll(row, j -> random.nextInt((Task1Main.limit + 1) / 2) * 2 + 1);
            return row;
        });
    }

    /**
     * @param colNum column number to get min value from
     * @return min value of column
     */
    public static int getColMin(int colNum) {
        if (colNum >= Task1Main.cols) throw new OutOfMemoryError("Number of column is out of range.");

        return Arrays.stream(Task1Main.array)
                .mapToInt(row -> row[colNum])
                .min()
                .orElseThrow();
    }

    /**
     * Fills strings array with symbol*minColumnValue
     *
     * @param sarr array of strings
     */
    public static void fillStrings(String[] sarr) {
        Arrays.setAll(sarr, i -> {
            int colMin = getColMin(i);
            return "*".repeat(colMin);
        });
    }
}
