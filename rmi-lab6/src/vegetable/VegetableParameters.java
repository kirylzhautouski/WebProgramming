package vegetable;

import java.io.Serializable;

public class VegetableParameters implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Calories */
    private double calories;

    /** State of a vegetable */
    private VegetableState vegetableState;

    /** Creates and initializes a newly created VegetableParameters with the given values */
    public VegetableParameters(double calories, VegetableState vegetableState) {
        this.calories = calories;
        this.vegetableState = vegetableState;
    }

    /** Getter for calories */
    public double getCalories() {
        return calories;
    }

    /** Getter for vegetable state */
    public VegetableState getVegetableState() {
        return vegetableState;
    }
}
