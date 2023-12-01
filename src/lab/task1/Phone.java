package lab.task1;

import java.io.*;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Abstract phone class. Contains country code, number and operator name.
 *
 * @version 1.0 10 Nov 2023
 * @see PhoneWithArrayList
 * @see PhoneWithSortedSet
 */
public abstract class Phone implements Serializable {
    public final int MAX_CODE_LENGTH = 4; // max length of country code and phone number
    public final int MAX_PHONE_LENGTH = 15; // max length of phone number

    protected int countryCode;
    protected String number;
    protected String operator;

    /**
     * Constructor for Phone class.
     * @param countryCode - country code of phone number. Must be less or equals MAX_CODE_LENGTH characters.
     * @param number      - phone number. Must be less or equals MAX_PHONE_LENGTH characters.
     * @param operator    - operator name.
     */
    public Phone(int countryCode, String number, String operator) {
        setCountryCode(countryCode);
        setNumber(number);
        setOperator(operator);
    }

    public Phone() { }

    /**
     * Gets calls.
     * @return calls.
     */
    public abstract Call[] getCalls();

    /**
     * Gets phone number.
     * @return phone number.
     */
    public String getNumber() {
        return number;
    };

    /**
     * Sets phone number.
     * @param number - phone number.
     */
    public void setNumber(String number) {
        if (number.length() > MAX_PHONE_LENGTH) {
            throw new IllegalArgumentException("Phone number must be less or equals " + MAX_PHONE_LENGTH + " characters");
        }

        this.number = number;
    }

    /**
     * Gets country code.
     * @return country code.
     */
    public int getCountryCode() {
        return countryCode;
    }

    /**
     * Sets country code.
     * @param countryCode - country code.
     */
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

    /**
     * Gets operator name.
     * @return operator name.
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Sets operator name.
     * @param operator - operator name.
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return String.format("+%d%s, %s", countryCode, number, operator);
    }

    /**
     * Gets average price per period.
     * @param start - start date of period.
     * @param end   - end date of period.
     * @return average price per period.
     */
    public double getAveragePricePerPeriod(LocalDateTime start, LocalDateTime end) {
        double totalPrice = 0;
        int days = 0;

        for (Call call : getCalls()) {
            LocalDateTime callDate = call.getDate();

            if (!callDate.isBefore(start) && !callDate.isAfter(end)) {
                totalPrice += call.getPrice();
                days++;
            }
        }

        return days == 0 ? 0 : Math.round((totalPrice / days) * 100.0) / 100.0;
    }

    /**
     * Gets days with even duration of conversation minute.
     * @return array of days with even duration of conversation minute.
     */
    public LocalDateTime[] getDaysWithEvenMinutes() {
        Map<LocalDateTime, Integer> totalDurationPerDay = new HashMap<>();

        for (Call call : getCalls()) {
            LocalDateTime callDate = call.getDate();
            double callDuration = call.getDuration();

            totalDurationPerDay.merge(callDate, (int) callDuration, Integer::sum);
        }

        long count = totalDurationPerDay.values().stream().filter(value -> value % 2 == 0).count();
        LocalDateTime[] oddDurationDays = new LocalDateTime[(int)count];

        for (Map.Entry<LocalDateTime, Integer> entry : totalDurationPerDay.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                oddDurationDays[(int)count - 1] = entry.getKey();
                count--;
            }
        }

        return oddDurationDays;
    }

    /**
     * Gets days when cost of minute exceeded threshold.
     * @param threshold - threshold of price per minute.
     * @return number of days when cost of minute exceeded threshold.
     */
    public int getDaysWithPricePerMinuteAbove(double threshold) {
        Map<LocalDateTime, Double> totalCostPerDay = new HashMap<>();

        for (Call call : getCalls()) {
            LocalDateTime callDate = call.getDate();
            double costPerMinute = call.getPrice() / call.getDuration();

            totalCostPerDay.merge(callDate.toLocalDate().atStartOfDay(), costPerMinute, Double::sum);
        }

        int daysAboveThreshold = 0;
        for (Map.Entry<LocalDateTime, Double> entry : totalCostPerDay.entrySet()) {
            if (entry.getValue() > threshold) {
                daysAboveThreshold++;
            }
        }

        return daysAboveThreshold;
    }

    /**
     * Sorts calls by duration using bubble sort descending.
     */
    public void sortByDuration() {
        int n = getCalls().length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (getCalls()[j].getDuration() < getCalls()[j + 1].getDuration()) {
                    Call temp = getCalls()[j];
                    getCalls()[j] = getCalls()[j + 1];
                    getCalls()[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Sorts calls by price using insertion sort ascending.
     */
    public void sortByPrice() {
        for (int i = 1; i < getCalls().length; i++) {
            Call key = getCalls()[i];
            int j = i - 1;

            while (j >= 0 && getCalls()[j].getPrice() > key.getPrice()) {
                getCalls()[j + 1] = getCalls()[j];
                j--;
            }

            getCalls()[j + 1] = key;
        }
    }

    /**
     * Override hashCode() method
     * @return hash code of object
     */
    @Override
    public int hashCode() {
        return Math.abs(Objects.hash(countryCode, number, operator));
    }

    /**
     * Override equals() method
     * @param o - object to compare
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone otherPhone = (Phone) o;
        return countryCode == otherPhone.countryCode &&
                Objects.equals(number, otherPhone.number) &&
                Objects.equals(operator, otherPhone.operator);
    }

    // TESTING

    public static void main(String[] args) {
        Phone phone = new Phone(1, "123456789", "Operator") {
            final List<Call> calls = List.of(
                    new Call(LocalDateTime.now(), 1, 1),
                    new Call(LocalDateTime.now(), 2, 2),
                    new Call(LocalDateTime.now(), 3, 3),
                    new Call(LocalDateTime.now(), 4, 4)
            );

            @Override
            public Call[] getCalls() {
                return calls.toArray(new Call[0]);
            }
        };

        testSetNumber(phone);
        testSetCountryCode(phone);
        testSetOperator(phone);
        testGetAveragePricePerPeriod(phone);
        testGetDaysWithEvenMinutes(phone);
        testGetDaysWithPricePerMinuteAbove(phone);
        testEquals(phone, phone);
        testHashCode(phone, phone);
    }

    private static void testSetNumber(Phone phone) {
        // Test setting a valid phone number
        phone.setNumber("987654321");
        System.out.println("Phone number set successfully: " + phone.getNumber());
    }

    private static void testSetCountryCode(Phone phone) {
        // Test setting a valid country code
        phone.setCountryCode(380);
        System.out.println("Country code set successfully: " + phone.getCountryCode());
    }

    private static void testSetOperator(Phone phone) {
        // Test setting the operator name
        phone.setOperator("New Operator");
        System.out.println("Operator set successfully: " + phone.getOperator());
    }

    private static void testGetAveragePricePerPeriod(Phone phone) {
        // Test the getAveragePricePerPeriod method
        LocalDateTime start = LocalDateTime.now().minusDays(7);
        LocalDateTime end = LocalDateTime.now();

        double averagePrice = phone.getAveragePricePerPeriod(start, end);
        System.out.println("Average price per period (last 7 days): " + averagePrice);
    }

    private static void testGetDaysWithEvenMinutes(Phone phone) {
        // Test the getDaysWithEvenMinutes method
        LocalDateTime[] daysWithEvenMinutes = phone.getDaysWithEvenMinutes();
        System.out.println("Days with even minutes:");
        for (LocalDateTime daysWithEvenMinute : daysWithEvenMinutes) {
            System.out.println(daysWithEvenMinute);
        }
    }

    private static void testGetDaysWithPricePerMinuteAbove(Phone phone) {
        // Test the getDaysWithPricePerMinuteAbove method
        double threshold = 0.5; // Define the threshold
        int daysAboveThreshold = phone.getDaysWithPricePerMinuteAbove(threshold);
        System.out.println("Number of days with cost per minute above " + threshold + ": " + daysAboveThreshold);
    }

    private static void testEquals(Phone phone1, Phone phone2) {
        System.out.println("Testing equals method:");
        System.out.println("Phone 1: " + phone1);
        System.out.println("Phone 2: " + phone2);
        System.out.println("Equals: " + phone1.equals(phone2));
    }

    private static void testHashCode(Phone phone1, Phone phone2) {
        System.out.println("Testing hashCode method:");
        System.out.println("Phone 1: " + phone1);
        System.out.println("Phone 2: " + phone2);
        System.out.println("Hash codes: " + phone1.hashCode() + " " + phone2.hashCode());
    }
}

