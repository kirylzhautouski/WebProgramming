package utils;

/**
 * Class that represents a pair of values
 * @param <T>
 *        Type of an object
 */
public class Pair<T> {

    /** First value of a pair */
    private final T first;

    /** Second value of a pair */
    private final T second;

    /**
     * Initializes a newly created Pair
     * @param first
     *        First value of a pair
     * @param second
     *        Second value of a pair
     */
    public Pair(final T first, final T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Getter for a first value
     * @return First value
     */
    public T getFirst() {
        return first;
    }

    /**
     * Setter for a second value
     * @return Second value
     */
    public T getSecond() {
        return second;
    }

    /**
     * Returns a string representation of this object
     * @return String representation of an Pair
     */
    @Override
    public String toString() {
        return "Pair(" +
            first +
            ", " + second +
            ")";
    }
}
