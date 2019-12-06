package quadraticequation;

import utils.Rational;

/**
 * Class that represents quadratic equation with rational coefficients
 */
public class RationalQuadraticEquation extends AbstractQuadraticEquation<Rational> {

    /**
     * Default constructor that initializes equation with only the first coefficient
     */
    public RationalQuadraticEquation() {
        super(new Rational(1, 1), new Rational(0, 1),
            new Rational(0, 1));
    }

    /**
     * Initializes RationalQuadraticEquation with the given coefficients
     * @param a
     *        First coefficient
     * @param b
     *        Second coefficient
     * @param c
     *        Third coefficient
     */
    public RationalQuadraticEquation(final Rational a, final Rational b, final Rational c) {
        super(a, b, c);
    }
}
