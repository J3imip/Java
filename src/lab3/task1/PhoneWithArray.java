package lab3.task1;

import java.security.InvalidParameterException;

/**
 * Phone class with array of calls. Implements Phone class.
 *
 * @version 1.0 10 Nov 2023
 * @see Phone
 */
sealed public class PhoneWithArray extends Phone permits PhoneWithSorting {
    Call[] calls;

    /**
     * Constructor for PhoneWithArray class.
     * @param countryCode - country code of phone number. Must be less or equals MAX_CODE_LENGTH characters.
     * @param number      - phone number. Must be less or equals MAX_PHONE_LENGTH characters.
     * @param operator    - operator name.
     * @param calls       - array of calls.
     */
    public PhoneWithArray(int countryCode, String number, String operator, Call[] calls) {
        super(countryCode, number, operator);
        this.calls = calls.clone();
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

    public static void main(String[] args) {
        // Creating some sample calls for testing
        Call[] calls = {
                new Call(Main.generateRandomDate(), 10.5, 50),
                new Call(Main.generateRandomDate(), 8.2, 40),
                new Call(Main.generateRandomDate(), 15.0, 70)
        };

        // Creating an instance of PhoneWithArray for testing
        PhoneWithArray phone = new PhoneWithArray(380, "123456789", "Operator", calls);

        testSortByDuration(phone);
        testSortByPrice(phone);
        testSetCountryCode(phone);
        testSetNumber(phone);
    }

    private static void testSetNumber(Phone phone) {
        // Test setting a valid phone number
        phone.setNumber("987654321");
        System.out.println("Phone number set successfully: " + phone.getNumber());

        // Test setting an invalid phone number
        try {
            phone.setNumber("123456789123456789");
        } catch (InvalidParameterException err) {
            System.out.println("Invalid phone number: " + err.getMessage());
        }
    }

    private static void testSetCountryCode(Phone phone) {
        // Test setting a valid country code
        phone.setCountryCode(380);
        System.out.println("Country code set successfully: " + phone.getCountryCode());

        try {
            // Test setting an invalid country code
            phone.setCountryCode(380123);
        } catch (InvalidParameterException err) {
            System.out.println("Invalid country code: " + err.getMessage());
        }
    }

    private static void testSortByDuration(PhoneWithArray phone) {
        System.out.println("Calls sorted by duration:");
        printCalls(phone.getCalls()); // Before sorting
        phone.sortByDuration();
        printCalls(phone.getCalls()); // After sorting
    }

    private static void testSortByPrice(PhoneWithArray phone) {
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