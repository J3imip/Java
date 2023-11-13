package lab3.task1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
     * @param calls       - list of calls.
     */
    public PhoneWithSorting(int countryCode, String number, String operator, List<Call> calls) {
        super(countryCode, number, operator, calls);
    }

    /**
     * Sorts calls by duration using Collections.sort().
     *
     * @see Collections#sort(List)
     * @see Arrays#sort(Object[])
     */
    public void sortByDuration() {
        Collections.sort(calls);
    }

    /**
     * Sorts calls by price using separate class that implements Comparator.
     *
     * @see Collections#sort(List)
     * @see Arrays#sort(Object[])
     */
    public void sortByPrice() {
        calls.sort(new CallPriceComparator());
    }
}