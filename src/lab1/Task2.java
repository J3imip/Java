package lab1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task2 {
    public static final int k = 8;
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter integer between 0 and 10: ");
            int n = scanner.nextInt();
            scanner.close();

            if (n < 0 || n > 10) throw new Exception("Number must be in range 0-10.");

            for (int i = 0; i <= n; i++) {
                /*
                * How that ternary operators works? So, if "i" equals 0 or 1, next condition will
                * check if it 1 or 0. If it equals 1 => 8 ^ 1 = 8, if it equals 0 => 8 ^ 0 = 1.
                * Rest of code will apply remaining powers.
                * */
                System.out.printf(
                    "%sBitwise: 8 ^ %d = %d%s\n",
                    GREEN,
                    i,
                    (i == 0 || i == 1) ? (i == 1 ? 8 : 1) : k << 3 * (i-1), //a bunch of ternary operators :D
                    RESET
                );

                System.out.printf("%sArithmetic: 8 ^ %d = %d%s\n", BLUE, i, Math.round(Math.pow(k, i)), RESET);
            }
        } catch(InputMismatchException error) {
            System.err.printf("%s: input number must be an integer.", error);
            System.exit(1);
        } catch(Exception error) {
            error.printStackTrace();
            System.exit(1);
        }
    }
}
