package vegetable;

import java.util.Objects;

/**
 * Class that represents tomatoes
 */
public class Tomato extends Vegetable {

    public static final double CARBOHYDRATES_PER_100_G = 4.0;
    public static final double PROTEINS_PER_100_G = 0.5;
    public static final double CALORIES_PER_100_G = 18.0;

    /** Tomato color */
    private TomatoColor tomatoColor;

    /** Creates and initializes a newly created red tomato with the given values */
    public Tomato(double weightInGramms, VegetableState vegetableState) {
        this(weightInGramms, vegetableState, TomatoColor.RED);
    }

    /** Creates and initializes a newly created tomato with the given values */
    public Tomato(double weightInGramms, VegetableState vegetableState, TomatoColor tomatoColor) {
        super(CARBOHYDRATES_PER_100_G, PROTEINS_PER_100_G, CALORIES_PER_100_G, weightInGramms, vegetableState);
        this.tomatoColor = tomatoColor;
    }

    /** Getter for tomato color */
    public TomatoColor getTomatoColor() {
        return tomatoColor;
    }

    /**
     * Returns a string representation of this object
     * @return String representation of a Tomato
     */
    @Override
    public String toString() {
        return "Tomato{" +
            "tomatoColor=" + tomatoColor +
            ", carbohydratesPer100G=" + carbohydratesPer100G +
            ", proteinsPer100G=" + proteinsPer100G +
            ", caloriesPer100G=" + caloriesPer100G +
            ", weightInGramms=" + weightInGramms +
            ", vegetableState=" + vegetableState +
            '}';
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(tomatoColor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final Tomato other = (Tomato) obj;
        return Objects.equals(this.tomatoColor, other.tomatoColor);
    }
}
