package utils;

/**
 * This class represents complex number
 */
public class Complex {

    /** Real part */
    private double re;

    /** Imaginary part */
    private double im;

    /**
     * Initializes a newly created Complex number with the given values
     * @param re
     *        Real part
     * @param im
     *        Imaginary part
     */
    public Complex(final double re, final double im) {
        this.re = re;
        this.im = im;
    }

    /**
     * Getter for a real part
     * @return Real part
     */
    public double getRe() {
        return re;
    }

    /**
     * Getter for an imaginary part
     * @return Imaginary part
     */
    public double getIm() {
        return im;
    }

    /**
     * Setter for a real part
     * @param re
     *        Real part
     */
    public void setRe(double re) {
        this.re = re;
    }

    /**
     * Setter for a imaginary part
     * @param im
     *        Imaginary part
     */
    public void setIm(double im) {
        this.im = im;
    }

    /**
     * Returns a string representation of this object
     * @return String representation of an Complex number
     */
    @Override
    public String toString() {
        return "(" +
            re +
            " + " + im +
            " * i)";
    }
}
