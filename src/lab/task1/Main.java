package lab.task1;

import com.sun.source.tree.Tree;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * Main class for the application.
 */
public class Main {
    /**
     * Main method for the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            File file = new File("./src/lab/task1/phone.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            file = new File("./src/lab/task1/phone.bin");
            if (!file.exists()) {
                file.createNewFile();
            }

            // Create a new PhoneWithTxtIO object and read data from file.
            PhoneWithTxtIO phoneWithTxtIO = new PhoneWithTxtIO();
            phoneWithTxtIO.readFromFile("./src/lab/task1/phone.txt");

            // Create a new PhoneWithBinaryIO object and read data from file.
            PhoneWithBinaryIO phoneWithBinaryIO = new PhoneWithBinaryIO();
            phoneWithBinaryIO.readFromFile("./src/lab/task1/phone.bin");

            // If the phone data is not complete, generate random calls.
            if (phoneWithTxtIO.getCalls().length == 0 ||
                    phoneWithTxtIO.getNumber() == null ||
                    phoneWithTxtIO.getOperator() == null ||
                    phoneWithTxtIO.getCountryCode() == 0) {
                generateRandomCalls(phoneWithTxtIO);
                phoneWithTxtIO.writeToFile("./src/lab/task1/phone.txt");
            }

            // If the phone data is not complete, generate random calls.
            if (phoneWithBinaryIO.getCalls().length == 0 ||
                    phoneWithBinaryIO.getNumber() == null ||
                    phoneWithBinaryIO.getOperator() == null ||
                    phoneWithBinaryIO.getCountryCode() == 0) {
                generateRandomCalls(phoneWithBinaryIO);
                phoneWithBinaryIO.writeToFile("./src/lab/task1/phone.bin");
            }

            // Create a PhoneWithArrayList object and print its data.
            PhoneWithArrayList phoneWithArray = createPhoneWithArrayList(phoneWithTxtIO);
            printPhone(phoneWithArray);

            // Create a PhoneWithSortedSet object and print its data.
            PhoneWithSortedSet phoneWithSortedSet = createPhoneWithSortedSet(phoneWithBinaryIO);
            printPhone(phoneWithSortedSet);

            // Update the calls in the PhoneWithTxtIO object and write to file.
            phoneWithTxtIO.setCalls(phoneWithArray.getCalls());
            phoneWithTxtIO.writeToFile("./src/lab/task1/sorted.txt");

            phoneWithBinaryIO.setCalls(phoneWithSortedSet.getCalls());
            phoneWithBinaryIO.writeToFile("./src/lab/task1/sorted.bin");
        } catch (Exception err) {
            // Print the stack trace and exit if an exception occurs.
            err.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Generates random calls for a PhoneWithTxtIO object.
     * @param phoneWithTxtIO The PhoneWithTxtIO object.
     */
    public static void generateRandomCalls(PhoneWithFileIO phoneWithTxtIO) {
        ArrayList<Call> arrayListCalls = new ArrayList<>();
        SortedSet<Call> sortedSetCalls = new TreeSet<>();
        for (int i = 0; i < 10; i++) {
            // Generate a random call and add it to the list.
            Call call = new Call(
                    generateRandomDate(),
                    new Random().nextDouble() * 100,
                    new Random().nextDouble() * 100
            );

            arrayListCalls.add(call);
        }

        // Set the calls, country code, number, and operator in the PhoneWithTxtIO object.
        phoneWithTxtIO.setCalls(arrayListCalls.toArray(new Call[0]));
        phoneWithTxtIO.setCountryCode(380);
        phoneWithTxtIO.setNumber("999999999999");
        phoneWithTxtIO.setOperator("vodafone");
    }

    /**
     * Creates a PhoneWithArrayList object from a PhoneWithTxtIO object.
     * @param phoneWithTxtIO The PhoneWithTxtIO object.
     * @return The created PhoneWithArrayList object.
     */
    public static PhoneWithArrayList createPhoneWithArrayList(PhoneWithTxtIO phoneWithTxtIO) {
        return new PhoneWithArrayList(
                phoneWithTxtIO.getCountryCode(),
                phoneWithTxtIO.getNumber(),
                phoneWithTxtIO.getOperator(),
                new ArrayList<>(Arrays.asList(phoneWithTxtIO.getCalls()))
        );
    }

    /**
     * Creates a PhoneWithSortedSet object from a PhoneWithTxtIO object.
     * @param phoneWithTxtIO The PhoneWithTxtIO object.
     * @return The created PhoneWithSortedSet object.
     */
    public static PhoneWithSortedSet createPhoneWithSortedSet(PhoneWithFileIO phoneWithTxtIO) {
        return new PhoneWithSortedSet(
                phoneWithTxtIO.getCountryCode(),
                phoneWithTxtIO.getNumber(),
                phoneWithTxtIO.getOperator(),
                new TreeSet<>(Arrays.asList(phoneWithTxtIO.getCalls()))
        );
    }

    /**
     * Prints the data of a Phone object.
     * @param phone The Phone object.
     */
    public static void printPhone(Phone phone) {
        System.out.println(phone.getClass().getSimpleName());

        // Print the phone data.
        System.out.println(phone);
        System.out.println("\nAverage price per day by last 15 days: $" + phone.getAveragePricePerPeriod(
                LocalDateTime.now().minusDays(15),
                LocalDateTime.now()
        ));
        System.out.println("Days when cost of minute exceeded $1.0 threshold: " +
                phone.getDaysWithPricePerMinuteAbove(1)
        );
        System.out.println("Days with even price of conversation minute: ");
        LocalDateTime[] daysWithEvenMinutes = phone.getDaysWithEvenMinutes();
        for (LocalDateTime daysWithEvenMinute : daysWithEvenMinutes) {
            System.out.println(daysWithEvenMinute);
        }
        System.out.println("\nSorted by Duration:");
        phone.sortByDuration();
        System.out.println(phone);
        System.out.println("\nSorted by Price:");
        phone.sortByPrice();
        System.out.println(phone);
    }

    /**
     * Generates a random date within the current month.
     * @return The generated date.
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
}