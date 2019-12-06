package quadraticequation.utils;

import utils.Pair;

/**
 * Class that represent an increasing or decreasing interval
 * @param <T>
 *        Numeric type
 */
public class Interval<T> {

    /** Interval */
    private final Pair<T> interval;

    /** Type of an interval */
    private final IntervalType intervalType;

    /**
     * Initializes a newly created Interval with the given values
     * @param interval
     *        An interval
     * @param intervalType
     *        Interval type
     */
    public Interval(Pair<T> interval, IntervalType intervalType) {
        this.interval = interval;
        this.intervalType = intervalType;
    }

    /**
     * Getter for an interval values
     * @return interval
     */
    public Pair<T> getInterval() {
        return interval;
    }

    /**
     * Getter for an interval type
     * @return interval type
     */
    public IntervalType getIntervalType() {
        return intervalType;
    }

    /**
     * Returns a string representation of this object
     * @return String representation of an Interval
     */
    @Override
    public String toString() {
        return "Interval(" +
             interval +
            ", " + intervalType +
            ")";
    }
}
