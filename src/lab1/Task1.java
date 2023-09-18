package lab1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task1 {
    private static final int n = 7;
    public static double calculateY(double x) {
        double result = 0.0;

        if (x <= 6) {
            for(int k = 1; k <= n; k++) {
                result += Math.sqrt(
                        Math.pow(k, 2) + 6 - x
                );
            }
        } else if (x > 6) {
            result = 27.0 + Math.pow(Math.exp(1), 6 - x);
        }

        return result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter x1: ");
            double x1 = scanner.nextDouble();

            System.out.print("Enter x2: ");
            double x2 = scanner.nextDouble();
            if (x1 > x2) {
                throw new Exception("Invalid interval range.");
            }

            System.out.print("Enter step: ");
            double step = scanner.nextDouble();
            if (step <= 0) {
                throw new Exception("Invalid step value.");
            }

            scanner.close();

            System.out.println("x\t\t\ty");
            for (double x = x1; x <= x2; x += step) {
                System.out.printf("%.1f\t\t\t", x);
                System.out.print(calculateY(x) + "\n");
            }
        } catch(InputMismatchException error) {
            System.err.printf("\t%s: Invalid input type (must be a double).", error);
            System.exit(1);
        } catch(Exception error) {
            System.err.println("\t" + error);
            System.exit(1);
        }
    }
}
