package lab.task2;

import java.io.*;
import java.util.*;

// Клас для порівняння цілих чисел за збільшенням суми цифр
class CompareBySumOfDigits implements Comparator<Integer> {
    // Метод для обчислення суми цифр числа
    private int getSumOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    @Override
    public int compare(Integer num1, Integer num2) {
        int sum1 = getSumOfDigits(num1);
        int sum2 = getSumOfDigits(num2);
        return Integer.compare(sum1, sum2);
    }
}

// Клас для порівняння цілих чисел за зменшенням суми цифр
class CompareByReverseSumOfDigits implements Comparator<Integer> {
    // Метод для обчислення суми цифр числа
    private int getSumOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    @Override
    public int compare(Integer num1, Integer num2) {
        int sum1 = getSumOfDigits(num1);
        int sum2 = getSumOfDigits(num2);
        return Integer.compare(sum2, sum1);
    }
}

public class Main {
    // Статичний метод для читання, сортування та запису в файли
    public static void sortAndWriteToFile(String inputFileName, String outputAscFileName, String outputDescFileName) {
        List<Integer> numbers = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(inputFileName))) {
            // Читання чисел з файлу
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Сортування за збільшенням суми цифр
        Collections.sort(numbers, new CompareBySumOfDigits());

        // Запис у файл відсортованих чисел за збільшенням суми цифр
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputAscFileName))) {
            for (Integer num : numbers) {
                writer.print(num + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Сортування за зменшенням суми цифр
        Collections.sort(numbers, new CompareByReverseSumOfDigits());

        // Запис у файл відсортованих чисел за зменшенням суми цифр
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputDescFileName))) {
            for (Integer num : numbers) {
                writer.print(num + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputFileName = "./src/lab/task2/input.txt"; // Ім'я файлу з вхідними даними
        String outputAscFileName = "./src/lab/task2/output_asc.txt"; // Ім'я файлу з результатом сортування за збільшенням суми цифр
        String outputDescFileName = "./src/lab/task2/output_desc.txt"; // Ім'я файлу з результатом сортування за зменшенням суми цифр

        sortAndWriteToFile(inputFileName, outputAscFileName, outputDescFileName);
    }
}
