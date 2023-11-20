package lab.task1;

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
    public PhoneWithArrayList(int countryCode, String number, String operator, ArrayList<Call> calls) {
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
    public List<Call> getCalls() {
        return calls;
    }

    @Override
    public String getNumber() {
        return super.number;
    }

    @Override
    public void setNumber(String number) {
        if (number.length() > MAX_PHONE_LENGTH) {
            throw new InvalidParameterException(String.format(
                    "The country code length must less or equals %d characters, not %d.",
                    MAX_PHONE_LENGTH,
                    number.length()
            ));
        }

        super.number = number;
    }

    @Override
    public int getCountryCode() {
        return super.countryCode;
    }

    @Override
    public void setCountryCode(int countryCode) {
        int codeLength = String.valueOf(countryCode).length();

        if (codeLength > MAX_CODE_LENGTH) {
            throw new InvalidParameterException(String.format(
                    "The country code length must less or equals %d characters, not %d.",
                    MAX_CODE_LENGTH,
                    codeLength
            ));
        }
        this.countryCode = countryCode;
    }

    @Override
    public String getOperator() {
        return super.operator;
    }

    @Override
    public void setOperator(String operator) {
        this.operator = operator;
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