package lab.task3;

class ArrayUtils {
    public static <T> void swapGroups(T[] array, int groupSize, int groupIndex1, int groupIndex2) {
        int startIndex1 = groupSize * groupIndex1;
        int startIndex2 = groupSize * groupIndex2;

        for (int i = 0; i < groupSize; i++) {
            T temp = array[startIndex1 + i];
            array[startIndex1 + i] = array[startIndex2 + i];
            array[startIndex2 + i] = temp;
        }
    }

    public static <T> void swapAdjacentElements(T[] array) {
        for (int i = 0; i < array.length - 1; i += 2) {
            T temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
        }
    }

    public static <T> void replaceElements(T[] array, T[] replacement, int startIndex) {
        for (int i = 0; i < replacement.length; i++) {
            array[startIndex + i] = replacement[i];
        }
    }

    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5, 6};
        Double[] doubleArray = {1.5, 2.5, 3.5, 4.5, 5.5, 6.5};
        String[] stringArray = {"one", "two", "three", "four", "five", "six"};

        System.out.println("Original Integer Array:");
        ArrayUtils.printArray(intArray);
        ArrayUtils.swapGroups(intArray, 2, 0, 1);
        System.out.println("After swapping groups:");
        ArrayUtils.printArray(intArray);
        ArrayUtils.swapAdjacentElements(intArray);
        System.out.println("After swapping adjacent elements:");
        ArrayUtils.printArray(intArray);
        Integer[] replacementIntArray = {7, 8};
        ArrayUtils.replaceElements(intArray, replacementIntArray, 2);
        System.out.println("After replacing elements:");
        ArrayUtils.printArray(intArray);

        System.out.println("\nOriginal Double Array:");
        ArrayUtils.printArray(doubleArray);
        ArrayUtils.swapGroups(doubleArray, 2, 0, 1);
        System.out.println("After swapping groups:");
        ArrayUtils.printArray(doubleArray);
        ArrayUtils.swapAdjacentElements(doubleArray);
        System.out.println("After swapping adjacent elements:");
        ArrayUtils.printArray(doubleArray);
        Double[] replacementDoubleArray = {7.5, 8.5, 9.5};
        ArrayUtils.replaceElements(doubleArray, replacementDoubleArray, 3);
        System.out.println("After replacing elements:");
        ArrayUtils.printArray(doubleArray);

        System.out.println("\nOriginal String Array:");
        ArrayUtils.printArray(stringArray);
        ArrayUtils.swapGroups(stringArray, 2, 0, 1);
        System.out.println("After swapping groups:");
        ArrayUtils.printArray(stringArray);
        ArrayUtils.swapAdjacentElements(stringArray);
        System.out.println("After swapping adjacent elements:");
        ArrayUtils.printArray(stringArray);
        String[] replacementStringArray = {"seven", "eight", "nine", "ten"};
        ArrayUtils.replaceElements(stringArray, replacementStringArray, 1);
        System.out.println("After replacing elements:");
        ArrayUtils.printArray(stringArray);
    }
}