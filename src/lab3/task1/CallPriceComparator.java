package lab3.task1;

import java.util.Comparator;

/**
 * CallPriceComparator class for PhoneWithSorting. Implements Comparator interface.
 *
 * @version 1.0 10 Nov 2023
 * @see Comparator
 */
public class CallPriceComparator implements Comparator<Call> {
    /**
     * Compares two calls by price.
     * @param call1 - first call.
     * @param call2 - second call.
     * @return 0 if calls are equal, 1 if first call is greater than second, -1 otherwise.
     */
    @Override
    public int compare(Call call1, Call call2) {
        return Double.compare(call1.getPrice(), call2.getPrice());
    }

    /**
     * Constructor for CallPriceComparator class.
     */
    CallPriceComparator() {}
}
