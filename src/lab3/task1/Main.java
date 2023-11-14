package lab3.task1;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * Main class for task1.
 * @version 1.0 10 Nov 2023
 */
public class Main {
    /**
     * Main method for task1.
     * @param args - command line arguments.
     */
    public static void main(String[] args) {
        try {
            List<Call> calls = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                calls.add(new Call(
                        generateRandomDate(),
                        new Random().nextDouble() * 100,
                        new Random().nextDouble() * 100
                ));
            }

            // PhoneWithArray
            PhoneWithArray phoneWithArray = new PhoneWithArray(380, "9922112233", "vodafone", calls);
            PrintPhone(phoneWithArray);

            // PhoneWithSorting
            PhoneWithSorting phoneWithSorting = new PhoneWithSorting(380, "6633221144", "vodafone", calls);
            PrintPhone(phoneWithSorting);
        } catch (Exception err) {
            err.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Generates random date between start of current month and end of next month.
     * @return random date.
     */
    public static LocalDateTime generateRandomDate() {
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1);
        LocalDateTime endOfMonth = LocalDateTime.now().plusMonths(1).withDayOfMonth(1).minusNanos(1);
        long startMillis = startOfMonth.toEpochSecond(ZoneOffset.UTC);
        long endMillis = endOfMonth.toEpochSecond(ZoneOffset.UTC);

        Random random = new Random();
        long randomMillis = startMillis + (long) (random.nextDouble() * (endMillis - startMillis));
        return  LocalDateTime.ofEpochSecond(randomMillis, 0, ZoneOffset.UTC);
    }

    /**
     * Prints phone info.
     * @param phoneWithArray - phone to print and apply sorting.
     */
    private static void PrintPhone(PhoneWithArray phoneWithArray) {
        System.out.println(
                phoneWithArray instanceof PhoneWithSorting ? ConsoleColors.PURPLE + "\nPhoneWithSorting: \n" :
                ConsoleColors.PURPLE + "\nPhoneWithArray: \n" + ConsoleColors.RESET
        );
        System.out.println(ConsoleColors.GREEN + phoneWithArray + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "\nAverage price per day by last 15 days: " + phoneWithArray.getAveragePricePerPeriod(
                LocalDateTime.now().minusDays(15),
                LocalDateTime.now()
        ) + "$" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "Days when cost of minute exceeded 1$ threshold: " +
                phoneWithArray.getDaysWithPricePerMinuteAbove(1) + ConsoleColors.RESET
        );
        System.out.println(ConsoleColors.YELLOW + "Days with even price of conversation minute: " + ConsoleColors.RESET);
        phoneWithArray.getDaysWithEvenMinutes().forEach((date) ->
                System.out.println(ConsoleColors.CYAN + "\t" + date.toLocalDate() + ConsoleColors.RESET)
        );
        System.out.println(ConsoleColors.BLUE + "\nSorted by Duration:" + ConsoleColors.RESET);
        phoneWithArray.sortByDuration();
        System.out.println(ConsoleColors.GREEN + phoneWithArray + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "\nSorted by Price:" + ConsoleColors.RESET);
        phoneWithArray.sortByPrice();
        System.out.println(ConsoleColors.GREEN + phoneWithArray + ConsoleColors.RESET);
    }

    /**
     * Console colors class.
     */
    public static class ConsoleColors {
        /**
         * Constructor for ConsoleColors class.
         */
        ConsoleColors() {}

        // ANSI escape codes for text colors
        public static final String RESET = "\u001B[0m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
    }

    /**
     * Constructor for Main class.
     */
    Main() {}
}