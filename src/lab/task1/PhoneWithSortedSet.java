package lab.task1;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Phone class with SortedSet of calls. Implements Phone class.
 *
 * @version 1.0 20 Nov 2023
 * @see Phone
 */
public class PhoneWithSortedSet extends Phone {
    SortedSet<Call> calls;

    /**
     * Constructor for PhoneWithArray class.
     * @param countryCode - country code of phone number. Must be less or equals MAX_CODE_LENGTH characters.
     * @param number      - phone number. Must be less or equals MAX_PHONE_LENGTH characters.
     * @param operator    - operator name.
     * @param calls       - SortedSet of calls.
     */
    public PhoneWithSortedSet(int countryCode, String number, String operator, SortedSet<Call> calls) {
        super(countryCode, number, operator);
        this.calls = calls;
    }

    @Override
    public String toString() {
        StringBuilder callsString = new StringBuilder();

        callsString.append(
                String.format(
                        """
                                    -= Phone =-
                                Country code: %d
                                Number:       %s
                                Operator:     %s
                                Calls: 
                                """,
                        countryCode,
                        number,
                        operator
                )
        );

        for (Call call : calls) {
            callsString.append(call.toString());
        }

        callsString.append("-============-");
        return callsString.toString();
    }

    @Override
    public Call[] getCalls() {
        return calls.toArray(new Call[0]);
    }

    /**
     * Sorts calls by duration descending.
     */
    public void sortByDuration() {
        SortedSet<Call> newSet = new TreeSet<>(Comparator.comparingDouble(Call::getDuration).reversed());
        newSet.addAll(calls);
        calls = newSet;
    }

    /**
     * Sorts calls by price ascending.
     */
    public void sortByPrice() {
        SortedSet<Call> newSet = new TreeSet<>(Comparator.comparingDouble(Call::getPrice));
        newSet.addAll(calls);
        calls = newSet;
    }
}