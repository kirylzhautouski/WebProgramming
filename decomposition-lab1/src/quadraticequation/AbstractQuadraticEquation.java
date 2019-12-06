package quadraticequation;

/**
 * Abstract class that represents quadratic equation and stores its coefficients,
 * also it has getters and setters for this coefficients and overrides toString method
 *
 * a * x^2 + b * x + c = 0
 * @param <T>
 *        Numeric type
 */
public abstract class AbstractQuadraticEquation<T> {

    /** First coefficient */
    protected T a;

    /** Second coefficient */
    protected T b;

    /** Third coefficient */
    protected T c;

    /**
     * Initializes a newly created AbstractQuasraticEquation with the given
     * coefficients
     * @param a
     *        First coefficient
     * @param b
     *        Second coefficient
     * @param c
     *        Third coefficient
     */
    public AbstractQuadraticEquation(final T a, final T b, final T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Getter for the first coefficient
     * @return First coefficient
     */
    public T getA() {
        return a;
    }

    /**
     * Setter for the first coefficient
     * @param a
     *        First coefficient
     */
    public void setA(T a) {
        this.a = a;
    }

    /**
     * Getter for the second coefficient
     * @return Second coefficient
     */
    public T getB() {
        return b;
    }

    /**
     * Setter for the second coefficient
     * @param b
     *        Second coefficient
     */
    public void setB(T b) {
        this.b = b;
    }

    /**
     * Getter for the third coefficient
     * @return Third coefficient
     */
    public T getC() {
        return c;
    }

    /**
     * Setter for the third coefficient
     * @param c
     *        Third coefficient
     */
    public void setC(T c) {
        this.c = c;
    }

    /**
     * Returns a string representation of this object
     * @return String representation of an AbstractQuadraticEquation
     */
    @Override
    public String toString() {
        return String.format("%s * x ^ 2 + %s * x + %s", a, b, c);
    }
}
