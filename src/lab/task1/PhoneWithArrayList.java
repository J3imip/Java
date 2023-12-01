package lab.task1;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Phone class with ArrayList of calls. Implements Phone class.
 *
 * @version 2.0 20 Nov 2023
 * @see Phone
 */
public class PhoneWithArrayList extends Phone {
    ArrayList<Call> calls;

    /**
     * Constructor for PhoneWithArray class.
     * @param countryCode - country code of phone number. Must be less or equals MAX_CODE_LENGTH characters.
     * @param number      - phone number. Must be less or equals MAX_PHONE_LENGTH characters.
     * @param operator    - operator name.
     * @param calls       - ArrayList of calls.
     */
    public PhoneWithArrayList(int countryCode, String number, String operator, ArrayList<Call> calls)  {
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
     * Sorts calls descending.
     */
    public void sortByDuration() {
        calls.sort((a, b) -> (int) (b.getDuration() - a.getDuration()));
    }

    /**
     * Sorts calls ascending.
     */
    public void sortByPrice() {
        calls.sort((a, b) -> (int) (a.getPrice() - b.getPrice()));
    }
}