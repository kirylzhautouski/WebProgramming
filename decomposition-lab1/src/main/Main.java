package main;

import quadraticequation.ComplexQuadraticEquation;
import quadraticequation.DoubleQuadraticEquation;
import quadraticequation.RationalQuadraticEquation;
import quadraticequation.utils.Equations;
import utils.Complex;
import utils.Rational;

/**
 * Class that contains main method, an entry point of an application
 */
public class Main {

    /**
     * Main method, an entry point of an application
     * @param args
     *        Command-line parameters
     */
    public static void main(String[] args) {
        DoubleQuadraticEquation[] equations = new DoubleQuadraticEquation[] {
                new DoubleQuadraticEquation(1, 1, 1),
                new DoubleQuadraticEquation(4, 4, 1),
                new DoubleQuadraticEquation(1, 5, 6)
                };

        for (DoubleQuadraticEquation equation : equations) {
            System.out.println(equation.findRoots());
        }

        System.out.println();
        System.out.println();

        System.out.println(Equations.findMinAndMaxRoots(equations));

        System.out.println();
        System.out.println();

        System.out.println(equations[2].findExtremum());

        System.out.println();
        System.out.println();

        System.out.println(equations[2].findIntervals());

        DoubleQuadraticEquation doubleQuadraticEquation = equations[2];

        ComplexQuadraticEquation complexQuadraticEquation = new ComplexQuadraticEquation(new Complex(1, 2),
            new Complex(3, 4), new Complex(5, 6));

        RationalQuadraticEquation rationalQuadraticEquation =
            new RationalQuadraticEquation(new Rational(1, 1),
                new Rational(3, 4),
                new Rational(5, 6));

        System.out.println();
        System.out.println();

        System.out.println(doubleQuadraticEquation);
        System.out.println(complexQuadraticEquation);
        System.out.println(rationalQuadraticEquation);
    }
}
