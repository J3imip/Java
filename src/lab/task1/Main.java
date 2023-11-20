package lab.task1;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * Main class for task1.
 * @version 2.0 20 Nov 2023
 */
public class Main {
    /**
     * Main method for task1.
     * @param args - command line arguments.
     */
    public static void main(String[] args) {
        try {
            ArrayList<Call> arrayListCalls = new ArrayList<>();
            SortedSet<Call> sortedSetCalls = new TreeSet<>();
            for (int i = 0; i < 10; i++) {
                arrayListCalls.add(new Call(
                        generateRandomDate(),
                        new Random().nextDouble() * 100,
                        new Random().nextDouble() * 100
                ));
                sortedSetCalls.add(new Call(
                        generateRandomDate(),
                        new Random().nextDouble() * 100,
                        new Random().nextDouble() * 100
                ));
            }

            // PhoneWithArray
            PhoneWithArrayList phoneWithArray = new PhoneWithArrayList(380, "9922112233", "vodafone", arrayListCalls);
            PrintPhone(phoneWithArray);

            // PhoneWithSorting
            PhoneWithSortedSet phoneWithSortedSet = new PhoneWithSortedSet(380, "6633221144", "vodafone", sortedSetCalls);
            PrintPhone(phoneWithSortedSet);
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
     * @param phone - phone to print and apply sorting.
     */
    private static void PrintPhone(Phone phone) {
        System.out.println(
                phone instanceof PhoneWithArrayList ? ConsoleColors.PURPLE + "\nPhoneWithArrayList: \n" :
                        ConsoleColors.PURPLE + "\nPhoneWithSortedSet: \n" + ConsoleColors.RESET
        );
        System.out.println(ConsoleColors.GREEN + phone + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "\nAverage price per day by last 15 days: $" + phone.getAveragePricePerPeriod(
                LocalDateTime.now().minusDays(15),
                LocalDateTime.now()
        ) + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "Days when cost of minute exceeded $1.0 threshold: " +
                phone.getDaysWithPricePerMinuteAbove(1) + ConsoleColors.RESET
        );
        System.out.println(ConsoleColors.YELLOW + "Days with even price of conversation minute: " + ConsoleColors.RESET);
        phone.getDaysWithEvenMinutes().forEach((date) ->
                System.out.println(ConsoleColors.CYAN + "\t" + date.toLocalDate() + ConsoleColors.RESET)
        );
        System.out.println(ConsoleColors.BLUE + "\nSorted by Duration:" + ConsoleColors.RESET);
        phone.sortByDuration();
        System.out.println(ConsoleColors.GREEN + phone + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "\nSorted by Price:" + ConsoleColors.RESET);
        phone.sortByPrice();
        System.out.println(ConsoleColors.GREEN + phone + ConsoleColors.RESET);
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