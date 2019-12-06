package vegetable;

import java.util.Objects;

/**
 * Class that represents carrots
 */
public class Carrot extends Vegetable {

    public static final double CARBOHYDRATES_PER_100_G = 7.6;
    public static final double PROTEINS_PER_100_G = 1.3;
    public static final double CALORIES_PER_100_G = 36.0;

    /** Is this carrot genetically modified */
    private boolean isGeneticallyModified;

    /** Creates and initializes a newly created not genetically modified carrot with the given values */
    public Carrot(double weightInGramms, VegetableState vegetableState) {
        this(weightInGramms, vegetableState, false);
    }

    /** Creates and initializes a newly created not genetically modified carrot with the given values */
    public Carrot(double weightInGramms, VegetableState vegetableState, boolean isGeneticallyModified) {
        super(CARBOHYDRATES_PER_100_G, PROTEINS_PER_100_G, CALORIES_PER_100_G, weightInGramms, vegetableState);
        this.isGeneticallyModified = isGeneticallyModified;
    }

    /** Getter for is genetically modified */
    public boolean isGeneticallyModified() {
        return isGeneticallyModified;
    }

    /**
     * Returns a string representation of this object
     * @return String representation of a Carrot
     */
    @Override
    public String toString() {
        return "Carrot{" +
            "isGeneticallyModified=" + isGeneticallyModified +
            ", carbohydratesPer100G=" + carbohydratesPer100G +
            ", proteinsPer100G=" + proteinsPer100G +
            ", caloriesPer100G=" + caloriesPer100G +
            ", weightInGramms=" + weightInGramms +
            ", vegetableState=" + vegetableState +
            '}';
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(isGeneticallyModified);
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
        final Carrot other = (Carrot) obj;
        return Objects.equals(this.isGeneticallyModified, other.isGeneticallyModified);
    }
}
