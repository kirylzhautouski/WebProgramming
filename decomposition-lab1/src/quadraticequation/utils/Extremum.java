package quadraticequation.utils;

/**
 * Class representing an extremum of a function
 * @param <T>
 *        Numeric type of an extremum
 */
public class Extremum<T> {

    /** Type of an extremum */
    private final ExtremumType extremumType;

    /** Extremum */
    private final T extremum;

    /**
     * Initializes a newly created Extremum with the given values
     * @param extremumType
     *        Type of an extremum
     * @param extremum
     *        Extremum
     */
    public Extremum(final ExtremumType extremumType, final T extremum) {
        this.extremumType = extremumType;
        this.extremum = extremum;
    }

    /**
     * Getter for type of an extremum
     * @return Type of an extremum
     */
    public ExtremumType getExtremumType() {
        return extremumType;
    }

    /**
     * Getter for an extremum
     * @return Extremum
     */
    public T getExtremum() {
        return extremum;
    }

    /**
     * Returns a string representation of this object
     * @return String representation of an Extremum
     */
    @Override
    public String toString() {
        return "Extremum(" +
             extremumType +
             ", "+ extremum +
            ")";
    }
}
