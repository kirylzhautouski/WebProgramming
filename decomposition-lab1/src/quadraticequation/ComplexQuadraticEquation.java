package quadraticequation;

import utils.Complex;

/**
 * Class that represents quadratic equation with complex coefficients
 */
public class ComplexQuadraticEquation extends AbstractQuadraticEquation<Complex> {

    /**
     * Default constructor that initializes equation with only the first coefficient
     */
    public ComplexQuadraticEquation() {
        super(new Complex(1, 0), new Complex(0, 0), new Complex(0, 0));
    }

    /**
     * Initializes ComplexQuadraticEquation with the given coefficients
     * @param a
     *        First coefficient
     * @param b
     *        Second coefficient
     * @param c
     *        Third coefficient
     */
    public ComplexQuadraticEquation(final Complex a, final Complex b, final Complex c) {
        super(a, b, c);
    }

}
