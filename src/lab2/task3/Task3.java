package lab2.task3;

public class Task3 {
    private static final int MAX_FIBONACCI_NUMBER = 92;
    private static final long[] fibonacciCache = new long[MAX_FIBONACCI_NUMBER + 1];

    public static long calculateFibonacci(int n) {
        if (n < 1 || n > MAX_FIBONACCI_NUMBER) {
            throw new IllegalArgumentException("Fibonacci number must be in range 1-92.");
        }

        if (fibonacciCache[n] != 0) return fibonacciCache[n];

        if (n <= 2) fibonacciCache[n] = 1;
        else fibonacciCache[n] = calculateFibonacci(n - 1) + calculateFibonacci(n - 2);

        return fibonacciCache[n];
    }

    public static void main(String[] args) {
        int[] testNumbers = {1, 5, 10, 20, 30, 45, 92};

        try {
            for (int n : testNumbers) {
                long result = calculateFibonacci(n);
                System.out.println("F(" + n + ") = " + result);
            }
        } catch(Exception error) {
            error.printStackTrace();
            System.exit(1);
        }
    }
}
