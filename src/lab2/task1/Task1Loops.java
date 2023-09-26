package lab2.task1;

import java.util.Random;

/**
 * Task1Array class implements traditional loops approach to get strings array
 */
public class Task1Loops {
    /**
     * Fills two-dimensional int array with random odd numbers in range [0 - limit]
     *
     * @param array two-dimensional int array
     */
    public static void fillArray(int[][] array) {
        if (array[0][0] != 0) return;
        Random random = new Random();

        for (int i = 0; i < Task1Main.rows; i++) {
            for (int j = 0; j < Task1Main.cols; j++) {
                array[i][j] = random.nextInt((Task1Main.limit + 1) / 2) * 2 + 1;
            }
        }
    }

    /**
     * @param colNum column number to get min value from
     * @return min value of column
     */
    public static int getColMin(int colNum) {
        if (colNum >= Task1Main.cols) throw new OutOfMemoryError("Number of column is out of range.");

        int min = Task1Main.array[0][colNum];

        for (int i = 0; i < Task1Main.rows; i++) {
            min = Math.min(Task1Main.array[i][colNum], min);
        }

        return min;
    }

    /**
     * Fills strings array with symbol*minColumnValue
     *
     * @param sarr array of strings
     */
    public static void fillStrings(String[] sarr) {
        for (int i = 0; i < Task1Main.cols; i++) {
            sarr[i] = "";
            for (int j = 0; j < getColMin(i); j++) {
                sarr[i] += "*";
            }
        }
    }

    /**
     * Sorts an array of strings using the quicksort algorithm based on string length.
     * <p>
     * This method sorts the input array of strings in ascending order of their
     * lengths using the quicksort algorithm. It recursively partitions the array
     * into smaller subarrays and sorts them until the entire array is sorted.
     *
     * @param arr   The array of strings to be sorted by length.
     * @param left  The left index of the subarray to be sorted.
     * @param right The right index of the subarray to be sorted.
     */
    public static void quickSortByLength(String[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);

            quickSortByLength(arr, left, partitionIndex - 1);
            quickSortByLength(arr, partitionIndex + 1, right);
        }
    }

    /**
     * Partitions the given array of strings based on the length of strings.
     * <p>
     * This method takes an array of strings and reorders it in such a way that
     * strings with lengths less than the pivot (last element) are placed to the
     * left, and strings with lengths greater than or equal to the pivot are placed
     * to the right.
     *
     * @param arr   The array of strings to be partitioned.
     * @param left  The left index of the partition.
     * @param right The right index of the partition.
     * @return      The index of the pivot element after partitioning.
     */
    public static int partition(String[] arr, int left, int right) {
        String pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j].length() > pivot.length()) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        return i + 1;
    }
}

