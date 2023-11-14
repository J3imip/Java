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
    public void sortByDuration() {
        Arrays.sort(calls);
    }

    /**
     * Sorts calls by price using separate class that implements Comparator.
     *
     * @see Arrays#sort(Object[])
     */
    public void sortByPrice() {
        Arrays.sort(calls, new CallPriceComparator());
    }
}