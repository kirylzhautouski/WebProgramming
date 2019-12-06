package quadraticequation.utils;

import quadraticequation.DoubleQuadraticEquation;
import utils.Pair;

import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 * This class contains method for manipulating arrays of DoubleQuadraticEquation
 * (finding minimum and maximum of roots of equations)
 */
public class Equations {

    // Suppresses default constructor, ensuring non-instantiability.
    private Equations() {}

    /**
     * Method for finding minimum and maximum roots of an array of DoubleQuadraticEquation
     * @param equations
     *        DoubleQuadraticEquation
     *
     * @return Minimum and maximum roots of all equations
     */
    public static Pair<Double> findMinAndMaxRoots(DoubleQuadraticEquation[] equations) {
        Double[] minMax = new Double[] { null, null };

        Arrays.stream(equations).map(DoubleQuadraticEquation::findRoots).flatMapToDouble((t) -> {
                if (t.getRootsInfo() == RootsInfo.NO_ROOTS)
                    return null;
                else if (t.getRootsInfo() == RootsInfo.ONE_ROOT)
                    return DoubleStream.of(t.getFirstRoot());
                else
                    return DoubleStream.of(t.getFirstRoot(), t.getSecondRoot());
            }
        ).forEach((t) -> {
            if (minMax[0] == null || Double.compare(t, minMax[0]) < 0)
                minMax[0] = t;

            if (minMax[1] == null || Double.compare(minMax[1], t) < 0)
                minMax[1] = t;
        });

        return new Pair<>(minMax[0], minMax[1]);
    }


}



