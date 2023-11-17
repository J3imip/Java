package lab3.task1;

import java.util.Arrays;

/**
 * Phone class with sorting calls. Extends PhoneWithArray.
 *
 * @version 1.0 10 Nov 2023
 * @see PhoneWithArray
 */
final public class PhoneWithSorting extends PhoneWithArray {
    /**
     * Constructor for PhoneWithSorting class.
     * @param countryCode - country code of phone number. Must be less or equals MAX_CODE_LENGTH characters.
     * @param number      - phone number. Must be less or equals MAX_PHONE_LENGTH characters.
     * @param operator    - operator name.
     * @param calls       - array of calls.
     */
    public PhoneWithSorting(int countryCode, String number, String operator, Call[] calls) {
        super(countryCode, number, operator, calls);
    }

    /**
     * Sorts calls by duration using Collections.sort().
     *
     * @see Arrays#sort(Object[])
     */
    @Override
    public void sortByDuration() {
        Arrays.sort(calls);
    }

    /**
     * Sorts calls by price using separate class that implements Comparator.
     *
     * @see Arrays#sort(Object[])
     */
    @Override
    public void sortByPrice() {
        Arrays.sort(calls, new CallPriceComparator());
    }

    public static void main(String[] args) {
        // Creating some sample calls for testing
        Call[] calls = {
                new Call(Main.generateRandomDate(), 10.5, 50),
                new Call(Main.generateRandomDate(), 8.2, 40),
                new Call(Main.generateRandomDate(), 15.0, 70)
        };

        // Creating an instance of PhoneWithSorting for testing
        PhoneWithSorting phone = new PhoneWithSorting(380, "123456789", "Operator", calls);

        testSortByDuration(phone);
        testSortByPrice(phone);
    }

    private static void testSortByDuration(PhoneWithSorting phone) {
        System.out.println("Calls sorted by duration:");
        printCalls(phone.getCalls()); // Before sorting
        phone.sortByDuration();
        printCalls(phone.getCalls()); // After sorting
    }

    private static void testSortByPrice(PhoneWithSorting phone) {
        System.out.println("Calls sorted by price:");
        printCalls(phone.getCalls()); // Before sorting
        phone.sortByPrice();
        printCalls(phone.getCalls()); // After sorting
    }

    private static void printCalls(Call[] calls) {
        for (Call call : calls) {
            System.out.println(call);
        }
        System.out.println("-----------------------");
    }
}