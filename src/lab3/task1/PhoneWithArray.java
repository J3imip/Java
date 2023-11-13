package lab3.task1;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Phone class with array of calls. Implements Phone class.
 *
 * @version 1.0 10 Nov 2023
 * @see Phone
 */
sealed public class PhoneWithArray extends Phone permits PhoneWithSorting {
    List<Call> calls;

    /**
     * Constructor for PhoneWithArray class.
     * @param countryCode - country code of phone number. Must be less or equals MAX_CODE_LENGTH characters.
     * @param number      - phone number. Must be less or equals MAX_PHONE_LENGTH characters.
     * @param operator    - operator name.
     * @param calls       - list of calls.
     */
    public PhoneWithArray(int countryCode, String number, String operator, List<Call> calls) {
        super(countryCode, number, operator);
        this.calls = calls;
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

        for (Call call : calls) {
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
     * @return list of days with even duration of conversation minute.
     */
    public List<LocalDateTime> getDaysWithEvenMinutes() {
        Map<LocalDateTime, Integer> totalDurationPerDay = new HashMap<>();

        for (Call call : calls) {
            LocalDateTime callDate = call.getDate();
            double callDuration = call.getDuration();

            totalDurationPerDay.merge(callDate, (int) callDuration, Integer::sum);
        }

        List<LocalDateTime> oddDurationDays = new ArrayList<>();
        for (Map.Entry<LocalDateTime, Integer> entry : totalDurationPerDay.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                oddDurationDays.add(entry.getKey());
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

        for (Call call : calls) {
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
     * Sorts calls by duration using bubble sort descending.
     */
    public void sortByDuration() {
        for (int i = 0; i < calls.size() - 1; i++) {
            for (int j = 0; j < calls.size() - i - 1; j++) {
                if (calls.get(j).getDuration() < calls.get(j + 1).getDuration()) {
                    Call temp = calls.get(j);
                    calls.set(j, calls.get(j + 1));
                    calls.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Sorts calls by price using insertion sort ascending.
     */
    public void sortByPrice() {
        for (int i = 1; i < calls.size(); i++) {
            Call key = calls.get(i);
            int j = i - 1;

            while (j >= 0 && calls.get(j).getPrice() > key.getPrice()) {
                calls.set(j + 1, calls.get(j));
                j--;
            }

            calls.set(j + 1, key);
        }
    }
}
