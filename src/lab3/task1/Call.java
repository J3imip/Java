package lab3.task1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

/**
 * Call class for PhoneWithArray. Implements Comparable interface.
 *
 * @version 1.0 10 Nov 2023
 */
public class Call implements Comparable<Call> {
    private LocalDateTime date;
    private double duration; // in minutes
    private double price;

    /**
     * Constructor for Call class.
     * @param date     - date of call.
     * @param duration - duration of call in minutes.
     * @param price    - price of call.
     */
    public Call(
            LocalDateTime date,
            double duration,
            double price
    ) {
        setDuration(duration);
        setPrice(price);
        setDate(date);
    }

    /**
     * Gets price of call.
     * @return price of call.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price of call.
     * @param elapsedMoney - price of call.
     */
    public void setPrice(double elapsedMoney) {
        this.price = elapsedMoney;
    }

    /**
     * Gets duration of call.
     * @return duration of call.
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Sets duration of call.
     * @param duration - duration of call.
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Gets date of call.
     * @return date of call.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets date of call.
     * @param date - date of call.
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return Math.abs(Objects.hash(getDate(), getDuration(), getPrice()));
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
        if (!(o instanceof Call other)) return false;

        return Double.compare(other.getDuration(), getDuration()) == 0 &&
                Double.compare(other.getPrice(), getPrice()) == 0 &&
                Objects.equals(other.getDate(), getDate());
    }

    @Override
    public int compareTo(Call other) {
        return Double.compare(other.getDuration(), getDuration());
    }

    public static void main(String[] args) {
        // Create some sample Call instances for testing
        LocalDateTime now = LocalDateTime.now();

        Call call1 = new Call(now, 10.5, 50);
        Call call2 = new Call(LocalDateTime.now().minusDays(1), 8.2, 40);
        Call call3 = new Call(LocalDateTime.now().minusHours(6), 15.0, 70);
        Call call4 = new Call(now, 10.5, 50);


        testCallEquals(call1, call4);
        testCallGettersAndSetters(call1);
        testCallToString(call2);
        testCallCompareTo(call1, call2, call3);
    }

    private static void testCallGettersAndSetters(Call call) {
        System.out.println("Getting and setting values for call:");
        System.out.println("Original call: " + call);

        // Test getters
        System.out.println("Date: " + call.getDate());
        System.out.println("Duration: " + call.getDuration());
        System.out.println("Price: " + call.getPrice());

        // Test setters
        call.setDate(LocalDateTime.now().minusDays(2));
        call.setDuration(12.3);
        call.setPrice(60);
        System.out.println("Modified call: " + call);
    }

    private static void testCallToString(Call call) {
        System.out.println("Testing toString method:");
        System.out.println(call.toString());
    }

    private static void testCallEquals(Call call1, Call call2) {
        System.out.println("Testing equals method:");
        System.out.println("Call 1: " + call1);
        System.out.println("Call 2: " + call2);
        System.out.println("Equals: " + call1.equals(call2));
    }

    private static void testCallCompareTo(Call call1, Call call2, Call call3) {
        System.out.println("Testing compareTo method:");
        Call[] calls = {call1, call2, call3};
        System.out.println("Before sorting:");
        printCalls(calls);

        Arrays.sort(calls);
        System.out.println("After sorting by duration:");
        printCalls(calls);
    }

    private static void printCalls(Call[] calls) {
        for (Call call : calls) {
            System.out.println(call);
        }
        System.out.println("-----------------------");
    }
}
