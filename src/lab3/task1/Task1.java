package lab3.task1;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

abstract class Phone {
    public final int MAX_CODE_LENGTH = 4;
    public final int MAX_PHONE_LENGTH = 15;

    protected int countryCode;
    protected String number;
    protected String operator;

    public abstract void setNumber(String number);
    public abstract void setCountryCode(int countryCode);
    public abstract void setOperator(String operator);

    public abstract String getNumber();
    public abstract int getCountryCode();
    public abstract String getOperator();

    public Phone(int countryCode, String number, String operator) {
        setCountryCode(countryCode);
        setNumber(number);
        setOperator(operator);
    }

    @Override
    public String toString() {
        return String.format("+%d%s, %s", countryCode, number, operator);
    }
}

class PhoneWithArray extends Phone {
    List<Call> calls;

    public PhoneWithArray(int countryCode, String number, String operator, List<Call> calls) {
        super(countryCode, number, operator);
        this.calls = calls;
    }

    public double getAveragePricePerDay(LocalDateTime start, LocalDateTime end) {
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

    public List<LocalDateTime> getDaysWithEvenMinutes() {
        Map<LocalDateTime, Integer> totalDurationPerDay = new HashMap<>();

        for (Call call : calls) {
            LocalDateTime callDate = call.getDate();
            double callDuration = call.getDuration();

            totalDurationPerDay.merge(callDate, (int)callDuration, Integer::sum);
        }

        List<LocalDateTime> oddDurationDays = new ArrayList<>();
        for (Map.Entry<LocalDateTime, Integer> entry : totalDurationPerDay.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                oddDurationDays.add(entry.getKey());
            }
        }

        return oddDurationDays;
    }

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
    public void setOperator(String operator) { this.operator = operator; }

    @Override
    public String getNumber() { return super.number; }

    @Override
    public int getCountryCode() { return super.countryCode; }

    @Override
    public String getOperator() { return super.operator; }

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

class Call implements Comparable<Call> {
    private LocalDateTime date;
    private double duration; // in minutes
    private double price;

    public Call(
        LocalDateTime date,
        double duration,
        double price
    ) {
        setDuration(duration);
        setPrice(price);
        setDate(date);
    }

    public double getPrice() { return price; }
    public double getDuration() { return duration; }
    public LocalDateTime getDate() { return date; }

    public void setPrice(double elapsedMoney) { this.price = elapsedMoney; }
    public void setDuration(double duration) { this.duration = duration; }
    public void setDate(LocalDateTime date) { this.date = date; }

    @Override
    public int hashCode() {
        return Math.abs(date.hashCode());
    }

    @Override
    public String toString() {
        return String.format(
            """
                \t-= Call №%s =-
            \tDate:          %s
            \tDuration:      %.1fm
            \tElapsed money: %.1f$
            """,
            hashCode(),
            getDate().format(DateTimeFormatter.ISO_DATE_TIME),
            getDuration(),
            getPrice()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Call)) return false;

        return this.hashCode() == o.hashCode();
    }

    @Override
    public int compareTo(Call other) {
        return Double.compare(other.getDuration(), getDuration());
    }
}

class PhoneWithSorting extends PhoneWithArray {
    public PhoneWithSorting(int countryCode, String number, String operator, List<Call> calls) {
        super(countryCode, number, operator, calls);
    }

    public void sortByDuration() {
        Collections.sort(calls);
    }

    public void sortByPrice() {
        calls.sort(new CallPriceComparator());
    }
}

class CallPriceComparator implements Comparator<Call> {
    @Override
    public int compare(Call call1, Call call2) {
        return Double.compare(call1.getPrice(), call2.getPrice());
    }
}

public class Task1 {
    public static LocalDateTime generateRandomDate() {
        LocalDateTime startOfMonth = LocalDateTime.now().withDayOfMonth(1);
        LocalDateTime endOfMonth = LocalDateTime.now().plusMonths(1).withDayOfMonth(1).minusNanos(1);
        long startMillis = startOfMonth.toEpochSecond(ZoneOffset.UTC);
        long endMillis = endOfMonth.toEpochSecond(ZoneOffset.UTC);

        Random random = new Random();
        long randomMillis = startMillis + (long) (random.nextDouble() * (endMillis - startMillis));
        return  LocalDateTime.ofEpochSecond(randomMillis, 0, ZoneOffset.UTC);
    }

    public static void main(String[] args) {
        try {
            List<Call> calls = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                calls.add(new Call(
                        generateRandomDate(),
                        new Random().nextDouble() * 100,
                        new Random().nextDouble() * 100
                ));
            }

            // PhoneWithArray
            PhoneWithArray phoneWithArray = new PhoneWithArray(380, "9922112233", "vodafone", calls);
            PrintPhone(phoneWithArray);

            // PhoneWithSorting
            PhoneWithSorting phoneWithSorting = new PhoneWithSorting(380, "6633221144", "vodafone", calls);
            PrintPhone(phoneWithSorting);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private static void PrintPhone(PhoneWithArray phoneWithArray) {
        System.out.println(
                phoneWithArray instanceof PhoneWithSorting ? ConsoleColors.PURPLE + "\nPhoneWithSorting: \n" :
                ConsoleColors.PURPLE + "\nPhoneWithArray: \n" + ConsoleColors.RESET
        );
        System.out.println(ConsoleColors.GREEN + phoneWithArray + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "\nAverage price per day: " + phoneWithArray.getAveragePricePerDay(
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()
        ) + "$" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "Days when cost of minute exceeded threshold: " +
                phoneWithArray.getDaysWithPricePerMinuteAbove(2) + ConsoleColors.RESET
        );
        System.out.println(ConsoleColors.YELLOW + "Days with even price of conversation minute: " + ConsoleColors.RESET);
        phoneWithArray.getDaysWithEvenMinutes().forEach((date) ->
                System.out.println(ConsoleColors.CYAN + "\t" + date.toLocalDate() + ConsoleColors.RESET)
        );
        System.out.println(ConsoleColors.BLUE + "\nSorted by Duration:" + ConsoleColors.RESET);
        phoneWithArray.sortByDuration();
        System.out.println(ConsoleColors.GREEN + phoneWithArray + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "\nSorted by Price:" + ConsoleColors.RESET);
        phoneWithArray.sortByPrice();
        System.out.println(ConsoleColors.GREEN + phoneWithArray + ConsoleColors.RESET);
    }

    public static class ConsoleColors {
        // ANSI escape codes for text colors
        public static final String RESET = "\u001B[0m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
    }
}