package vegetable;

import java.util.Objects;

/**
 * Class that represents cucumbers
 */
public class Cucumber extends Vegetable {

    public static final double CARBOHYDRATES_PER_100_G = 2.9;
    public static final double PROTEINS_PER_100_G = 0.7;
    public static final double CALORIES_PER_100_G = 15.0;

    /** Is this cucumber from grandmas village */
    private boolean isGrandmas;

    /** Creates and initializes a newly created grandmas cucumber with the given values */
    public Cucumber(double weightInGramms, VegetableState vegetableState) {
        this(weightInGramms, vegetableState, true);
    }

    /** Creates and initializes a newly created cucumber with the given values */
    public Cucumber(double weightInGramms, VegetableState vegetableState, boolean isGrandmas) {
        super(CARBOHYDRATES_PER_100_G, PROTEINS_PER_100_G, CALORIES_PER_100_G, weightInGramms, vegetableState);
        this.isGrandmas = isGrandmas;
    }

    /** Getter for cucumber showing if this cucumber is from grandmas village */
    public boolean isGrandmas() {
        return isGrandmas;
    }

    /**
     * Returns a string representation of this object
     * @return String representation of a Cucumber
     */
    @Override
    public String toString() {
        return "Cucumber{" +
            "isGrandmas=" + isGrandmas +
            ", carbohydratesPer100G=" + carbohydratesPer100G +
            ", proteinsPer100G=" + proteinsPer100G +
            ", caloriesPer100G=" + caloriesPer100G +
            ", weightInGramms=" + weightInGramms +
            ", vegetableState=" + vegetableState +
            '}';
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(isGrandmas);
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
        final Cucumber other = (Cucumber) obj;
        return Objects.equals(this.isGrandmas, other.isGrandmas);
    }
}
