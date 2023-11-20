package lab.task4;

import java.util.List;

class NumberListUtils {
    public static <T extends Number> int indexOfFirstZero(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).doubleValue() == 0.0) {
                return i;
            }
        }
        return -1;
    }

    public static <T extends Number> int countNegativeNumbers(List<T> list) {
        int count = 0;
        for (T number : list) {
            if (number.doubleValue() < 0) {
                count++;
            }
        }
        return count;
    }

    public static <T extends Number> T getLastNegativeElement(List<T> list) {
        T lastNegative = null;
        for (T number : list) {
            if (number.doubleValue() < 0) {
                lastNegative = number;
            }
        }
        return lastNegative;
    }
}

public class Main {
    public static void main(String[] args) {
        // Test with Integer list
        List<Integer> intList = List.of(1, -2, 3, 0, -5, 6, 0, 7);
        System.out.println("Index of first zero in Integer list: " + NumberListUtils.indexOfFirstZero(intList));
        System.out.println("Number of negative integers in Integer list: " + NumberListUtils.countNegativeNumbers(intList));
        System.out.println("Last negative element in Integer list: " + NumberListUtils.getLastNegativeElement(intList));

        // Test with Double list
        List<Double> doubleList = List.of(1.5, -2.5, 3.2, 0.0, -5.8, 6.4, -7.1);
        System.out.println("\nIndex of first zero in Double list: " + NumberListUtils.indexOfFirstZero(doubleList));
        System.out.println("Number of negative doubles in Double list: " + NumberListUtils.countNegativeNumbers(doubleList));
        System.out.println("Last negative element in Double list: " + NumberListUtils.getLastNegativeElement(doubleList));
    }
}
