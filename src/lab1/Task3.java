package lab1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task3 {
    public static boolean isError = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter x: ");
            int x = scanner.nextInt();
            System.out.print("Enter n: ");
            int n = scanner.nextInt();

            if (n < 0) {
                throw new Exception("n must be greater than 0.");
            }

            double result = getProduct(n, x);
            if (!isError) System.out.printf("\nResult: %f", result);
            else System.exit(1);
        } catch (InputMismatchException error) {
            System.err.println("\tInput values must be integers.");
            System.exit(1);
        } catch (Exception error) {
            error.printStackTrace();
            System.exit(1);
        }
    }

    private static double getProduct(int n, int x) {
        double product = 1;

        outer:
        for (int i = 1; i <= n - 1; i++) {
            double sum = 0;

            for (int j = 0; j <= n; j++) {
                if ((j + x) == i) continue outer;

                else if ((j + x) == 0) {
                    isError = true;
                    System.err.println("\tDivider is zero.");
                    break outer;
                }

                sum += (double)(i) / (double)(j + x);
            }

            product *= sum;
        }

        return product;
    }
}
