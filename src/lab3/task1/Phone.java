package lab3.task1;

/**
 * Abstract phone class. Contains country code, number and operator name.
 *
 * @version 1.0 10 Nov 2023
 * @see PhoneWithArray
 * @see PhoneWithSorting
 */
public sealed abstract class Phone permits PhoneWithArray {
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

    /**
     * Gets phone number.
     * @return phone number.
     */
    public abstract String getNumber();

    /**
     * Sets phone number.
     * @param number - phone number.
     */
    public abstract void setNumber(String number);

    /**
     * Gets country code.
     * @return country code.
     */
    public abstract int getCountryCode();

    /**
     * Sets country code.
     * @param countryCode - country code.
     */
    public abstract void setCountryCode(int countryCode);

    /**
     * Gets operator name.
     * @return operator name.
     */
    public abstract String getOperator();

    /**
     * Sets operator name.
     * @param operator - operator name.
     */
    public abstract void setOperator(String operator);

    @Override
    public String toString() {
        return String.format("+%d%s, %s", countryCode, number, operator);
    }
}
