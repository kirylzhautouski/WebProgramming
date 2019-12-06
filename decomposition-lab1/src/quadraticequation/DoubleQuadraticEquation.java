package quadraticequation;

import quadraticequation.utils.*;
import utils.*;

/**
 * Class that represents quadratic equation with complex coefficients
 */
public class DoubleQuadraticEquation extends AbstractQuadraticEquation<Double> {

    /**
     * Default constructor that initializes equation with only the first coefficient
     */
    public DoubleQuadraticEquation() {
        this(1, 0, 0);
    }

    /**
     * Initializes DoubleQuadraticEquation with the given coefficients
     * @param a
     *        First coefficient
     * @param b
     *        Second coefficient
     * @param c
     *        Third coefficient
     *
     * @throws IllegalArgumentException
     *         First coefficient can't be zero
     */
    public DoubleQuadraticEquation(final double a, final double b, final double c) {
        super(a, b, c);

        if (a == 0)
            throw new IllegalArgumentException("a must be different from zero");
    }

    /**
     * Finds roots of a quadratic equation
     * @return Roots of a DoubleQuadraticEquation
     */
    public Roots<Double> findRoots() {
        double d = b * b - 4 * a * c;

        if (d == 0) {
            return new Roots<>((-b - Math.sqrt(d)) / (2 * a), null, RootsInfo.ONE_ROOT);
        } else if (d  > 0) {
            return new Roots<>((-b - Math.sqrt(d)) / (2 * a),
                (-b + Math.sqrt(d)) / (2 * a), RootsInfo.TWO_ROOTS);
        } else {
            // d < 0

            return new Roots<>(null, null, RootsInfo.NO_ROOTS);
        }
    }

    /**
     * Finds extremum of a quadratic equation
     * @return Extremum of a quadratic equation
     */
    public Extremum<Double> findExtremum() {
        double x = findExtremumPoint();
        double y = a * x * x + b * x + c;

        if (a > 0) {
            return new Extremum<>(ExtremumType.MINIMUM, y);
        } else {
            // a < 0
            return new Extremum<>(ExtremumType.MAXIMUM, y);
        }
    }

    /**
     * Finds intervals of increase and decrease
     * @return Pair of intervals
     */
    public Pair<Interval> findIntervals() {
        double x = findExtremumPoint();

        Interval<Double> firstInterval;
        Interval<Double> secondInterval;

        if (a > 0) {
            firstInterval = new Interval<>(new Pair<>(Double.NEGATIVE_INFINITY, x), IntervalType.DECREASING);
            secondInterval = new Interval<>(new Pair<>(x, Double.POSITIVE_INFINITY), IntervalType.INCREASING);
        } else {
            // a < 0
            firstInterval = new Interval<>(new Pair<>(Double.NEGATIVE_INFINITY, x), IntervalType.INCREASING);
            secondInterval = new Interval<>(new Pair<>(x, Double.POSITIVE_INFINITY), IntervalType.DECREASING);
        }

        return new Pair<>(firstInterval, secondInterval);
    }

    /**
     * Finds a point of an extremum
     * @return Point of an extremum
     */
    private double findExtremumPoint() {
        return -b / (2 * a);
    }
}
