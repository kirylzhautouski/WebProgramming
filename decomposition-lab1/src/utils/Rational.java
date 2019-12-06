package utils;

/**
 * This class represents rational number
 */
public class Rational {

    /** Numerator */
    private double numerator;

    /** Denominator */
    private double denominator;

    /**
     * Initializes a newly created Rational with the given numerator and denominator
     * @param numerator
     *        Numerator
     * @param denominator
     *        Denominator
     *
     * @throws IllegalArgumentException
     *         Denominator must be different from zero
     */
    public Rational(double numerator, double denominator) {
        if (denominator == 0)
            throw new IllegalArgumentException("denominator must be different from zero");

        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Getter for a numerator
     * @return Numerator
     */
    public double getNumerator() {
        return numerator;
    }

    /**
     * Setter for a numerator
     * @param numerator
     *        Numerator
     */
    public void setNumerator(double numerator) {
        this.numerator = numerator;
    }

    /**
     * Getter for a denominator
     * @return Denominator
     */
    public double getDenominator() {
        return denominator;
    }

    /**
     * Setter for a denominator
     * @param denominator
     *        Denominator
     */
    public void setDenominator(double denominator) {
        this.denominator = denominator;
    }

    /**
     * Returns a string representation of this object
     * @return String representation of a Rational number
     */
    @Override
    public String toString() {
        return "(" + numerator + "/" + denominator + ")";
    }
}
