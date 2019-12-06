package quadraticequation.utils;

import utils.Pair;

/**
 * Class that represents roots of a quadratic equation
 * @param <T>
 *        Numeric type
 */
public class Roots<T> {

    /** Defines if there are no roots, one root or two roots */
    private RootsInfo rootsInfo;

    /** Roots */
    private Pair<T> roots;

    /**
     * Initializes a newly created Roots object with the given values
     * @param firstRoot
     *        First root
     * @param secondRoot
     *        Second root
     * @param rootsInfo
     *        Root info
     */
    public Roots(final T firstRoot, final T secondRoot, final RootsInfo rootsInfo) {
        this.roots = new Pair<>(firstRoot, secondRoot);

        this.rootsInfo = rootsInfo;
    }

    /**
     * Getter for a first root
     * @return First root
     */
    public T getFirstRoot() {
        return roots.getFirst();
    }

    /**
     * Getter for a second root
     * @return Second root
     */
    public T getSecondRoot() {
        return roots.getSecond();
    }

    /**
     * Getter for a roots info
     * @return Roots info
     */
    public RootsInfo getRootsInfo() {
        return rootsInfo;
    }

    /**
     * Returns a string representation of this object
     * @return String representation of an Roots
     */
    @Override
    public String toString() {
        if (rootsInfo == RootsInfo.NO_ROOTS) {
            return "No roots";
        } else if (rootsInfo == RootsInfo.ONE_ROOT) {
            return String.format("Root is %s", roots.getFirst());
        } else {
            // rootsInfo == RootsInfo.TWO_ROOTS
            return String.format("First root is %s, second root is %s", roots.getFirst(), roots.getSecond());
        }
    }
}

