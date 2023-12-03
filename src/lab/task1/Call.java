package lab.task1;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Call class for PhoneWithArray. Implements Comparable interface.
 *
 * @version 1.0 10 Nov 2023
 */
public class Call implements Comparable<Call>, Serializable {
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
        return Math.abs(date.hashCode());
    }

    @Override
    public String toString() {
        return String.format(
                """
                            \t-= Call №%s =-
                        \tDate:          %s
                        \tDuration:      %.1fm
                        \tElapsed money: $%.1f
                        """,
                hashCode(),
                getDate().format(DateTimeFormatter.ISO_DATE_TIME),
                getDuration(),
                getPrice()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Call)) return false;
        Call other = (Call) o;
        return Double.compare(other.duration, duration) == 0 &&
                Double.compare(other.price, price) == 0 &&
                Objects.equals(other.date, date);
    }

    @Override
    public int compareTo(Call other) {
        return Double.compare(other.getDuration(), getDuration());
    }
}