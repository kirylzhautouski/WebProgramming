package vegetable;

import java.util.Objects;

/**
 * Abstract class representing a vegetable
 */
public abstract class Vegetable {

    /** Number of carbohydrates in gramms per 100 gramms */
    protected final double carbohydratesPer100G;

    /** Number of proteins in gramms per 100 gramms */
    protected final double proteinsPer100G;

    /** Number of calories in ccal per 100 gramms */
    protected final double caloriesPer100G;

    /** Weight of vegetable in gramms */
    protected double weightInGramms;

    /** State of vegetable */
    protected final VegetableState vegetableState;

    /** Creates and initializes a newly created vegetable with the given values */
    protected Vegetable(double carbohydratesPer100G, double proteinsPer100G, double caloriesPer100G,
                     double weightInGramms, VegetableState vegetableState) {
        this.carbohydratesPer100G = carbohydratesPer100G;
        this.proteinsPer100G = proteinsPer100G;
        this.caloriesPer100G = caloriesPer100G;

        this.weightInGramms = weightInGramms;

        this.vegetableState = vegetableState;
    }

    /** Getter for number of carbohydrates in gramms per 100 gramms */
    public double getCarbohydratesPer100G() {
        return carbohydratesPer100G;
    }

    /** Getter for number of proteins in gramms per 100 gramms */
    public double getProteinsPer100G() {
        return proteinsPer100G;
    }

    /** Getter for number of calories in ccal per 100 gramms */
    public double getCaloriesPer100G() {
        return caloriesPer100G;
    }

    /** Getter for number of carbohydrates in gramms */
    public double getCarbohydratesInGramms() {
        return (weightInGramms / 100) * carbohydratesPer100G;
    }

    /** Getter for number of proteins in gramms */
    public double getProteinsInGramms() {
        return (weightInGramms / 100) * proteinsPer100G;
    }

    /** Getter for number of calories in gramms */
    public double getCaloriesInGramms() {
        return (weightInGramms / 100) * caloriesPer100G;
    }

    /** Getter for vegetable state */
    public VegetableState getVegetableState() {
        return vegetableState;
    }

    /** Getter for number of weight in gramms */
    public double getWeightInGramms() {
        return weightInGramms;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carbohydratesPer100G, proteinsPer100G, caloriesPer100G, weightInGramms, vegetableState);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Vegetable other = (Vegetable) obj;
        return Objects.equals(this.carbohydratesPer100G, other.carbohydratesPer100G)
            && Objects.equals(this.proteinsPer100G, other.proteinsPer100G)
            && Objects.equals(this.caloriesPer100G, other.caloriesPer100G)
            && Objects.equals(this.weightInGramms, other.weightInGramms)
            && Objects.equals(this.vegetableState, other.vegetableState);
    }
}
